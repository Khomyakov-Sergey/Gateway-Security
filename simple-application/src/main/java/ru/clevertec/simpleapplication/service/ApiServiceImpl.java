package ru.clevertec.simpleapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.simpleapplication.entity.Student;
import ru.clevertec.simpleapplication.entity.User;
import ru.clevertec.simpleapplication.mapper.ApiMapper;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService  {

    private final ApiMapper mapper;

    @Override
    public User convertToUser(Student student) {
        return mapper.toUser(student);
    }

    @Override
    public Student convertToStudent(User user) {
        return mapper.toStudent(user);
    }
}
