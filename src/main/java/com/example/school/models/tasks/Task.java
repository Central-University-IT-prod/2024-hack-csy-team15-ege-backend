package com.example.school.models.tasks;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    private String subject;

    private String type;

    @Column(length = 512)
    private String task;

    @Column(length = 1024)
    private String text;

    private String level;

    private Integer exp;

    private String answer;
}
