package ynzmz.server.teacher.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.tag.entity.Tag;
import ynzmz.server.tag.mappingtable.teacher.TeacherTag;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher teacherToTeacherPost(TeacherDto.Post teacherPost);
    Teacher teacherToTeacherPatch(TeacherDto.Patch teacherPatch);
    default TeacherDto.InfoResponse teacherInfoResponseToTeacher(Teacher teacher){
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.InfoResponse infoResponse = new TeacherDto.InfoResponse();

        infoResponse.setTeacherId( teacher.getTeacherId() );
        infoResponse.setName( teacher.getName() );
        infoResponse.setIntroduction( teacher.getIntroduction() );
        List<TeacherTag> list = teacher.getTeacherTags();

        ArrayList<Tag.Type> responses = new ArrayList<>();

        for(TeacherTag teacherTag: list){
            responses.add(teacherTag.getTag().getType());
        }
        infoResponse.setTags(responses);

        return infoResponse;
    }

    List<TeacherDto.InfoResponse> teacherInfoResponsesToTeachers(List<Teacher> teachers);
}
