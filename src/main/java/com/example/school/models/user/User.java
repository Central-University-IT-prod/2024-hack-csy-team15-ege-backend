package com.example.school.models.user;


import com.example.school.models.StringListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String firstName;

    private String secondName;

    private Integer exp;

    private Integer level;

    @Convert(converter = StringListConverter.class)
    private List<String> solvedTasks = new ArrayList<>();
}
