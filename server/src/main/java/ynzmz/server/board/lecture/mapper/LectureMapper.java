package ynzmz.server.board.lecture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.board.lecture.dto.LectureDto;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.tag.dto.GradeTagDto;
import ynzmz.server.tag.dto.PlatformTagDto;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.entity.lecture.LectureGradeTag;
import ynzmz.server.tag.entity.lecture.LecturePlatformTag;
import ynzmz.server.tag.entity.lecture.LectureSubjectTag;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LectureMapper {
    Lecture lecturePostToLecture(LectureDto.Post lecturePost);
    Lecture lecturePatchToLecture(LectureDto.Patch lecurePatch);
    LectureDto.SimpleInfoResponse lectureToLectureInfoResponse(Lecture lecture);
    LectureDto.ListPageResponse lectureToLectureListPageResponse(Lecture lecture);
    List<LectureDto.ListPageResponse> lecturesToLectureListPageResponses(List<Lecture> lectures);
    LectureDto.DetailPageResponse lectureToLectureDetailPageResponse(Lecture lecture);

    default GradeTag.Grade map(GradeTag gradeTag) {
        return gradeTag.getGrade();
    }
    default SubjectTag.Subject map(SubjectTag subjectTag) {
        return subjectTag.getSubject();
    }
    default PlatformTag.Platform map(PlatformTag platformTag) {
        return platformTag.getPlatform();
    }

    default GradeTagDto.Response lectureGradeTagToGradeTagResponse(LectureGradeTag lectureGradeTag) {
        if ( lectureGradeTag == null ) return null;

        GradeTagDto.Response response = new GradeTagDto.Response();
        response.setGradeTag( lectureGradeTag.getGradeTag().getGrade() );

        return response;
    }

    default PlatformTagDto.Response lecturePlatformTagToPlatformTagResponse(LecturePlatformTag lecturePlatformTag) {
        if ( lecturePlatformTag == null ) return null;

        PlatformTagDto.Response response = new PlatformTagDto.Response();

        response.setPlatformTag( lecturePlatformTag.getPlatformTag().getPlatform() );

        return response;
    }

    default SubjectTagDto.Response lectureSubjectTagToSubjectTagResponse(LectureSubjectTag lectureSubjectTag) {
        if ( lectureSubjectTag == null ) return null;

        SubjectTagDto.Response response = new SubjectTagDto.Response();

        response.setSubjectTag( lectureSubjectTag.getSubjectTag().getSubject() );

        return response;
    }
}
