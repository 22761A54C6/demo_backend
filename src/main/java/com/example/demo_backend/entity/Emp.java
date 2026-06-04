package com.example.demo_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="Students")
public class Emp {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int rolno;

    public String name;
    public int backlogs;

    public int getRolno() {
        return rolno;
    }

    public void setRolno(int rolno) {
        this.rolno = rolno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(int backlogs) {
        this.backlogs = backlogs;
    }
}
