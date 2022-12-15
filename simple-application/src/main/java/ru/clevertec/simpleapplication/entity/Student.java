package ru.clevertec.simpleapplication.entity;

import lombok.Data;

@Data
public class Student {

    private Long id;

    private String nickname;

    private String firstname;

    private String lastname;

    private int age;
}
