package ynzmz.server.board.teacher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.tag.dto.GradeTagDto;
import ynzmz.server.tag.dto.PlatformTagDto;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.entity.teacher.TeacherGradeTag;
import ynzmz.server.tag.entity.teacher.TeacherPlatformTag;
import ynzmz.server.tag.entity.teacher.TeacherSubjectTag;
import ynzmz.server.board.teacher.dto.TeacherDto;
import ynzmz.server.board.teacher.entity.Teacher;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {
    Teacher teacherPostToTeacher(TeacherDto.Post teacherPost);
    Teacher teacherPatchToTeacher(TeacherDto.Patch teacherPatch);
    TeacherDto.SimpleInfoResponse teacherToTeacherSimpleInfoResponse(Teacher teacher);
    TeacherDto.ListPageResponse teacherToTeacherListPageResponse(Teacher teacher);
    List<TeacherDto.ListPageResponse> teachersToTeacherListPageResponses(List<Teacher> teachers);
    TeacherDto.DetailPageResponse teacherToTeacherDetailPageResponse(Teacher teacher);
    TeacherDto.ReviewDetailPageResponse teacherToTeacherReviewDetailPageResponse(Teacher teacher);
    default GradeTag.Grade map(GradeTag gradeTag) {
        return gradeTag.getGrade();
    }
    default SubjectTag.Subject map(SubjectTag subjectTag) {
        return subjectTag.getSubject();
    }
    default PlatformTag.Platform map(PlatformTag platformTag) {
        return platformTag.getPlatform();
    }

    default GradeTagDto.Response teacherGradeTagToGradeTagResponse(TeacherGradeTag teacherGradeTag) {
        if ( teacherGradeTag == null ) return null;

        GradeTagDto.Response response = new GradeTagDto.Response();
        response.setGradeTag( teacherGradeTag.getGradeTag().getGrade() );

        return response;
    }

    default PlatformTagDto.Response teacherPlatformTagToPlatformTagResponse(TeacherPlatformTag teacherPlatformTag) {
        if ( teacherPlatformTag == null ) return null;

        PlatformTagDto.Response response = new PlatformTagDto.Response();

        response.setPlatformTag( teacherPlatformTag.getPlatformTag().getPlatform() );

        return response;
    }

    default SubjectTagDto.Response teacherSubjectTagToSubjectTagResponse(TeacherSubjectTag teacherSubjectTag) {
        if ( teacherSubjectTag == null ) return null;

        SubjectTagDto.Response response = new SubjectTagDto.Response();

        response.setSubjectTag( teacherSubjectTag.getSubjectTag().getSubject() );

        return response;
    }
}
