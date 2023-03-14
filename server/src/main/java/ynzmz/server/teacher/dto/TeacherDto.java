package ynzmz.server.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.dto.TeacherGradeTagDto;
import ynzmz.server.tag.dto.TeacherPlatformTagDto;
import ynzmz.server.tag.dto.TeacherSubjectTagDto;
import ynzmz.server.tag.mappingtable.teacher.TeacherGradeTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherPlatformTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherSubjectTag;

import java.util.List;

@Getter @Setter
public class TeacherDto {
    //강사등록
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String name;
        private String introduction;
        private List<String> profile;
        private List<String> analects;
        private String imageUrl;
        private List<String> gradeTag;
        private List<String> subjectTag;
        private List<String> platformTag;
    }
    //강사수정
    @Getter @Setter
    @AllArgsConstructor
    public static class Patch {
        private String name;
        private String introduction;
        private List<String> profile;
        private List<String> analects;
        private String imageUrl;
        private List<String> gradeTag;
        private List<String> subjectTag;
        private List<String> platformTag;
    }
    //강사전체조회
    @Getter @Setter
    public static class ListPageResponse {
        private Long teacherId;
        private String name;
        private String introduction;
        private String imageUrl;
        private double starPointAverage;
        private long totalReviewCount;
        private List<TeacherGradeTagDto.Response> gradeTags;
        private List<TeacherSubjectTagDto.Response> subjectTags;
        private List<TeacherPlatformTagDto.Response> platformTags;
    }

    //강사디테일조회
    @Getter @Setter
    public static class DetailPageResponse {
        private Long teacherId;
        private String name;
        private String introduction;
        private String imageUrl;
        private List<String> profile;
        private List<String> analects;
        private double starPointAverage;
        private long totalReviewCount;
        private List<TeacherGradeTag> gradeTags;
        private List<TeacherSubjectTag> subjectTags;
        private List<TeacherPlatformTag> platformTags;
        private List<Lecture> lectures;
    }

    @Getter @Setter
    public static class ReviewDetailPageResponse {
        private Long teacherId;
        private String name;
        private String introduction;
        private String imageUrl;
        private List<String> profile;
        private List<String> analects;
        private double starPointAverage;
        private long starPoint5Count;
        private long starPoint4Count;
        private long starPoint3Count;
        private long starPoint2Count;
        private long starPoint1Count;
        private List<TeacherGradeTag> gradeTags;
        private List<TeacherSubjectTag> subjectTags;
        private List<TeacherPlatformTag> platformTags;
        private List<LectureDto.TeacherReviewDetailPageResponse> lectures;
    }
    @Getter @Setter
    public static class SimpleInfoResponse {
        private Long teacherId;
        private String name;
        private double starPointAverage;
    }
}
