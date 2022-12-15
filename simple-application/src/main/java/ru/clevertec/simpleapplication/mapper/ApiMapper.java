package ru.clevertec.simpleapplication.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.simpleapplication.entity.Student;
import ru.clevertec.simpleapplication.entity.User;

@Mapper(componentModel = "spring")
public interface ApiMapper {

    Student toStudent(User user);

    User toUser(Student student);
}
