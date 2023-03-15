package ynzmz.server.lecture.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.review.lecture.dto.LectureReviewDto;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.tag.dto.GradeTagDto;
import ynzmz.server.tag.dto.PlatformTagDto;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.mappingtable.lecture.LectureGradeTag;
import ynzmz.server.tag.mappingtable.lecture.LecturePlatformTag;
import ynzmz.server.tag.mappingtable.lecture.LectureSubjectTag;
import ynzmz.server.teacher.dto.TeacherDto;

import java.util.List;
import java.util.Map;

public class LectureDto {
    //postDto
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post{
        private String title;
        private String introduction;
        private String status;
        private List<String> gradeTag;
        private List<String> subjectTag;
        private List<String> platformTag;
        private long teacherId;
    }
    //pathDto
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Patch{
        private String title;
        private String introduction;
        private String status;
        private List<String> gradeTag;
        private List<String> subjectTag;
        private List<String> platformTag;
        private long teacherId;
    }
    //강의 전체조회
    @Getter @Setter
    public static class ListPageResponse{
        private long lectureId;
        private String title;
        private String introduction;
        private Lecture.Status status;
        private double starPointAverage;
        private long totalReviewCount;
        private List<GradeTagDto.Response> gradeTags;
        private List<SubjectTagDto.Response> subjectTags;
        private List<PlatformTagDto.Response> platformTags;
        private TeacherDto.SimpleInfoResponse teacher;
    }

    @Getter @Setter
    public static class DetailPageResponse{
        private long lectureId;
        private String title;
        private String introduction;
        private Lecture.Status status;
        private TeacherDto.SimpleInfoResponse teacher;
        private double starPointAverage;
        private long totalReviewCount;
        private Map<String,Long> starPointCount;
        private List<GradeTagDto.Response> gradeTags;
        private List<SubjectTagDto.Response> subjectTags;
        private List<PlatformTagDto.Response> platformTags;
        private List<LectureReviewDto.ListPageResponse> lectureReviews;
    }
    @Getter @Setter
    public static class TeacherReviewDetailPageResponse{
        private long lectureId;
        private String title;
        private Lecture.Status status;
        private List<LectureReviewDto.ListPageResponse> lectureReviews;
    }
    @Getter @Setter
    public static class SimpleInfoResponse {
        private long lectureId;
        private String title;
        private double starPointAverage;

    }
}
