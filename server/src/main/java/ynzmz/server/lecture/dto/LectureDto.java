package ynzmz.server.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.tag.mappingtable.lecture.LectureGradeTag;
import ynzmz.server.tag.mappingtable.lecture.LecturePlatformTag;
import ynzmz.server.tag.mappingtable.lecture.LectureSubjectTag;
import ynzmz.server.teacher.dto.TeacherDto;

import java.util.List;

public class LectureDto {
    //postDto
    @Getter
    @AllArgsConstructor
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
        private List<LectureGradeTag> gradeTags;
        private List<LectureSubjectTag> subjectTags;
        private List<LecturePlatformTag> platformTags;
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
        private long starPoint5Count;
        private long starPoint4Count;
        private long starPoint3Count;
        private long starPoint2Count;
        private long starPoint1Count;
        private List<LectureGradeTag> gradeTags;
        private List<LectureSubjectTag> subjectTags;
        private List<LecturePlatformTag> platformTags;
        private List<LectureReview> lectureReviews;
    }
    @Getter @Setter
    public static class TeacherReviewDetailPageResponse{
        private long lectureId;
        private String title;
        private Lecture.Status status;
        private List<LectureReview> lectureReviews;
    }
    //강의 디테일조회
    @Getter @Setter
    public static class SimpleInfoResponse {
        private long lectureId;
        private String title;
        private double starPointAverage;
    }
}
