package ynzmz.server.teacher.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.tag.Tag;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher teacherToTeacherPost(TeacherDto.Post teacherPost);
    Teacher teacherToTeacherPatch(TeacherDto.Patch teacherPatch);
    default TeacherDto.SimpleInfoResponse teacherInfoResponseToTeacher(Teacher teacher){
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.SimpleInfoResponse simpleInfoResponse = new TeacherDto.SimpleInfoResponse();

        simpleInfoResponse.setTeacherId( teacher.getTeacherId() );
        simpleInfoResponse.setName( teacher.getName() );
        simpleInfoResponse.setIntroduction( teacher.getIntroduction() );
        List<TeacherTag> list = teacher.getTeacherTags();

        ArrayList<Tag.Type> responses = new ArrayList<>();

        for(TeacherTag teacherTag: list){
            responses.add(teacherTag.getTag().getType());
        }
        simpleInfoResponse.setTags(responses);

        return simpleInfoResponse;
    }

    List<TeacherDto.SimpleInfoResponse> teacherInfoResponsesToTeachers(List<Teacher> teachers);
}
