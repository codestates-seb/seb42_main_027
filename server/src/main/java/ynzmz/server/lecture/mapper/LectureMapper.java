package ynzmz.server.lecture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LectureMapper {
    Lecture lectureToLecturePost(LectureDto.Post lecturePost);
    Lecture lectureToLecturePatch(LectureDto.Patch lecurePatch);
    LectureDto.SimpleInfoResponse lectureInfoResponseToLecture(Lecture lecture);
    List<LectureDto.SimpleInfoResponse> lectureInfoResponsesToLectures(List<Lecture> lectures);
    LectureDto.ListPageResponse lectureListPageResponseToLecture(Lecture lecture);
    List<LectureDto.ListPageResponse> lectureListPageResponsesToLectures(List<Lecture> lectures);
    LectureDto.DetailPageResponse lectureDetailPageResponseToLecture(Lecture lecture);
    List<LectureDto.DetailPageResponse> lectureDetailPageResponsesToLectures(List<Lecture> lectures);
    LectureDto.TeacherReviewDetailPageResponse lectureTeacherReviewDetailPageResponseToLecture(Lecture lecture);
    List<LectureDto.TeacherReviewDetailPageResponse> lectureTeacherReviewDetailPageResponsesToLectures(List<Lecture> lectures);
}
