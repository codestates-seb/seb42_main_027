package ynzmz.server.teacher.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher teacherToTeacherPost(TeacherDto.Post teacherPostDto);
    TeacherDto.infoResponse teacherInfoResponseToTeacher(Teacher teacher);
}
