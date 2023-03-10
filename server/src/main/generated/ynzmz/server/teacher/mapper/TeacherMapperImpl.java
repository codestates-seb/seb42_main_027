package ynzmz.server.teacher.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-10T11:00:20+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher teacherToTeacherPost(TeacherDto.Post teacherPostDto) {
        if ( teacherPostDto == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        return teacher;
    }
}
