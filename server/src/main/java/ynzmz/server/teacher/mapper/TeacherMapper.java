package ynzmz.server.teacher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {
    Teacher teacherToTeacherPost(TeacherDto.Post teacherPost);
    Teacher teacherToTeacherPatch(TeacherDto.Patch teacherPatch);
    TeacherDto.SimpleInfoResponse teacherInfoResponseToTeacher(Teacher teacher);
    List<TeacherDto.SimpleInfoResponse> teacherInfoResponsesToTeachers(List<Teacher> teachers);
    TeacherDto.ListPageResponse teacherListPageResponseToTeacher(Teacher teacher);
    List<TeacherDto.ListPageResponse> teacherListPageResponsesToTeachers(List<Teacher> teachers);
    TeacherDto.DetailPageResponse teacherDetailPageResponseToTeacher(Teacher teacher);
    List<TeacherDto.DetailPageResponse> teacherDetailPageResponsesToTeachers(List<Teacher> teachers);
    TeacherDto.ReviewDetailPageResponse teacherReviewDetailPageResponseToTeacher(Teacher teacher);
    List<TeacherDto.ReviewDetailPageResponse> teacherReviewDetailPageResponsesToTeachers(List<Teacher> teachers);
}
