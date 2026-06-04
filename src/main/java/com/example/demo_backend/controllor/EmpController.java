package com.example.demo_backend.controllor;

import com.example.demo_backend.entity.Emp;
import com.example.demo_backend.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/Emp")
    public class EmpController {

        @Autowired
        private EmpRepo repo;

        @PostMapping("/stds")
        public Emp createEmp(@RequestBody Emp emp) {
            return repo.save(emp);
        }

        @GetMapping("/getstd")
        public List<Emp> getEmp() {
            return repo.findAll();
        }

        @GetMapping("/{id}")
        public Emp getEmpId(@PathVariable long id){
            return repo.findById(id).orElse(null);
        }
        @DeleteMapping("/delete/{id}")
        public String deleteById(@PathVariable long id){
            repo.deleteById(id);
            return "successfully deleted";
        }

        @PutMapping("/update/{id}")
        public Emp updateData(@PathVariable long id, @RequestBody Emp emp){
            Emp existId=repo.findById(id).orElse(null);
            if(existId !=null){
                existId.setName(emp.getName());
                existId.setBacklogs(emp.getBacklogs());
                return repo.save(existId);
            }
            return null;

        }
    }
