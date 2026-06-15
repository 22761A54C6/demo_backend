package com.example.demo_backend.dto;

public class SummarizeRequest {
    private String text;
    private String length;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getLength() { return length; }
    public void setLength(String length) { this.length = length; }
}