package ynzmz.server.teacher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.tag.dto.GradeTagDto;
import ynzmz.server.tag.dto.PlatformTagDto;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
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
    TeacherDto.ListPageResponse teacherListPageResponseToTeacher(Teacher teacher);
    List<TeacherDto.ListPageResponse> teacherListPageResponsesToTeachers(List<Teacher> teachers);
    TeacherDto.DetailPageResponse teacherDetailPageResponseToTeacher(Teacher teacher);
    TeacherDto.ReviewDetailPageResponse teacherReviewDetailPageResponseToTeacher(Teacher teacher);
    default GradeTag.Grade map(GradeTag gradeTag) {
        return gradeTag.getGrade();
    }
    default SubjectTag.Subject map(SubjectTag subjectTag) {
        return subjectTag.getSubject();
    }
    default PlatformTag.Platform map(PlatformTag platformTag) {
        return platformTag.getPlatform();
    }

    default GradeTagDto.Response gradeTagResponseToTeacherGradeTag(TeacherGradeTag teacherGradeTag) {
        if ( teacherGradeTag == null ) return null;

        GradeTagDto.Response response = new GradeTagDto.Response();
        response.setGradeTag( teacherGradeTag.getGradeTag().getGrade() );

        return response;
    }

    default PlatformTagDto.Response platformTagResponseToTeacherPlatformTag(TeacherPlatformTag teacherPlatformTag) {
        if ( teacherPlatformTag == null ) return null;

        PlatformTagDto.Response response = new PlatformTagDto.Response();

        response.setPlatformTag( teacherPlatformTag.getPlatformTag().getPlatform() );

        return response;
    }

    default SubjectTagDto.Response subjectTagResponseToTeacherSubjectTag(TeacherSubjectTag teacherSubjectTag) {
        if ( teacherSubjectTag == null ) return null;

        SubjectTagDto.Response response = new SubjectTagDto.Response();

        response.setSubjectTag( teacherSubjectTag.getSubjectTag().getSubject() );

        return response;
    }
}
