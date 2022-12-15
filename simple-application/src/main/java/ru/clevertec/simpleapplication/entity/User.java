package ru.clevertec.simpleapplication.entity;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String nickname;

    private String firstname;

    private String lastname;

    private int age;

}
