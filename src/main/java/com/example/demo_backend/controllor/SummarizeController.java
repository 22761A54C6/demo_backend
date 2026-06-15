package com.example.demo_backend.controllor;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SummarizeController {

    @Value("${groq.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.groq.com")
            .build();

    @PostMapping(
            value = "/summarize",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Map<String, String>> summarize(
            @RequestPart("file") MultipartFile file,
            @RequestPart("length") String length
    ) {

        String extractedText;

        try {

            System.out.println("File Name : " + file.getOriginalFilename());
            System.out.println("Length : " + length);

            if (file.getOriginalFilename() != null &&
                    file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {

                try (PDDocument document =
                             Loader.loadPDF(file.getBytes())) {

                    PDFTextStripper stripper =
                            new PDFTextStripper();

                    extractedText =
                            stripper.getText(document);
                }

            } else {

                extractedText =
                        new String(file.getBytes());
            }

        } catch (IOException e) {

            return Mono.just(
                    Map.of(
                            "summary",
                            "Error reading file : "
                                    + e.getMessage()
                    )
            );
        }

        if (extractedText == null ||
                extractedText.trim().isEmpty()) {

            return Mono.just(
                    Map.of(
                            "summary",
                            "No readable text found in document."
                    )
            );
        }

        String prompt =
                buildPrompt(extractedText, length);

        Map<String, Object> requestBody =
                Map.of(
                        "model",
                        "llama-3.3-70b-versatile",

                        "messages",
                        List.of(
                                Map.of(
                                        "role",
                                        "user",

                                        "content",
                                        prompt
                                )
                        ),

                        "max_tokens",
                        1000
                );

        return webClient.post()
                .uri("/openai/v1/chat/completions")
                .header(
                        "Authorization",
                        "Bearer " + apiKey
                )
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)

                .map(response -> {

                    try {

                        List<Map<String, Object>> choices =
                                (List<Map<String, Object>>)
                                        response.get("choices");

                        Map<String, Object> message =
                                (Map<String, Object>)
                                        choices.get(0)
                                                .get("message");

                        String summary =
                                (String)
                                        message.get("content");

                        return Map.of(
                                "summary",
                                summary
                        );

                    } catch (Exception e) {

                        e.printStackTrace();

                        return Map.of(
                                "summary",
                                "Unable to generate summary."
                        );
                    }
                })

                .onErrorResume(error -> {

                    error.printStackTrace();

                    return Mono.just(
                            Map.of(
                                    "summary",
                                    "Groq Error : "
                                            + error.getMessage()
                            )
                    );
                });
    }

    private String buildPrompt(
            String text,
            String length
    ) {

        String lengthPrompt;

        switch (length) {

            case "short":
                lengthPrompt =
                        "in 3-4 concise sentences";
                break;

            case "detailed":
                lengthPrompt =
                        "as detailed bullet points covering all important topics";
                break;

            default:
                lengthPrompt =
                        "in a well structured paragraph around 150 words";
        }

        if (text.length() > 15000) {

            text =
                    text.substring(
                            0,
                            15000
                    );
        }

        return "Summarize the following document "
                + lengthPrompt
                + ":\n\n"
                + text;
    }
}

