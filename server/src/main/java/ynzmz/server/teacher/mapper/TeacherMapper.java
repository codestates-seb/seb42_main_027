package ynzmz.server.teacher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.tag.dto.TeacherGradeTagDto;
import ynzmz.server.tag.dto.TeacherPlatformTagDto;
import ynzmz.server.tag.dto.TeacherSubjectTagDto;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherGradeTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherPlatformTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherSubjectTag;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {
    Teacher teacherToTeacherPost(TeacherDto.Post teacherPost);
    Teacher teacherToTeacherPatch(TeacherDto.Patch teacherPatch);
    TeacherDto.SimpleInfoResponse teacherInfoResponseToTeacher(Teacher teacher);
    List<TeacherDto.SimpleInfoResponse> teacherInfoResponsesToTeachers(List<Teacher> teachers);

//    @Mapping(source = "gradeTags.gradeTag", target = "gradeTags.gradeTag", qualifiedByName = "setGradeTagResponse")
    TeacherDto.ListPageResponse teacherListPageResponseToTeacher(Teacher teacher);


    @Named("setGradeTagResponse")
    default GradeTag.Grade setGradeTagResponse(GradeTag gradeTag) { return gradeTag.getGrade(); }
    List<TeacherDto.ListPageResponse> teacherListPageResponsesToTeachers(List<Teacher> teachers);
    TeacherDto.DetailPageResponse teacherDetailPageResponseToTeacher(Teacher teacher);
    List<TeacherDto.DetailPageResponse> teacherDetailPageResponsesToTeachers(List<Teacher> teachers);
    TeacherDto.ReviewDetailPageResponse teacherReviewDetailPageResponseToTeacher(Teacher teacher);
    List<TeacherDto.ReviewDetailPageResponse> teacherReviewDetailPageResponsesToTeachers(List<Teacher> teachers);

    default TeacherGradeTagDto.Response TeacherGradeTagResponseToTeacherGradeTag(TeacherGradeTag teacherGradeTag) {
        if ( teacherGradeTag == null ) {
            return null;
        }

        TeacherGradeTagDto.Response response = new TeacherGradeTagDto.Response();
        response.setGradeTag( teacherGradeTag.getGradeTag().getGrade() );

        return response;
    }

    default TeacherPlatformTagDto.Response TeacherPlatformTagResponseToTeacherPlatformTag(TeacherPlatformTag teacherPlatformTag) {
        if ( teacherPlatformTag == null ) {
            return null;
        }

        TeacherPlatformTagDto.Response response = new TeacherPlatformTagDto.Response();

        response.setPlatformTag( teacherPlatformTag.getPlatformTag().getPlatform() );

        return response;
    }

    default TeacherSubjectTagDto.Response TeacherSubjectTagResponseToTeacherSubjectTag(TeacherSubjectTag teacherSubjectTag) {
        if ( teacherSubjectTag == null ) {
            return null;
        }

        TeacherSubjectTagDto.Response response = new TeacherSubjectTagDto.Response();

        response.setSubjectTag( teacherSubjectTag.getSubjectTag().getSubject() );

        return response;
    }
}
