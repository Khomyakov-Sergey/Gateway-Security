package ru.clevertec.simpleapplication.service;

import ru.clevertec.simpleapplication.entity.Student;
import ru.clevertec.simpleapplication.entity.User;

public interface ApiService {

    User convertToUser(Student student);

    Student convertToStudent(User user);
}
