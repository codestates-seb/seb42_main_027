package ynzmz.server.board.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.board.lecture.dto.LectureDto;
import ynzmz.server.tag.dto.GradeTagDto;
import ynzmz.server.tag.dto.PlatformTagDto;
import ynzmz.server.tag.dto.SubjectTagDto;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class TeacherDto {
    //강사등록
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post {
        private String name;
        private String introduction;
        private List<String> profile;
        private List<String> analects;
        private String profileImageUrl;
        private String realImageUrl;
        private List<String> gradeTag;
        private List<String> subjectTag;
        private List<String> platformTag;
    }
    //강사수정
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Patch {
        private String name;
        private String introduction;
        private List<String> profile;
        private List<String> analects;
        private String profileImageUrl;
        private String RealImageUrl;
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
        private String profileImageUrl;
        private String realImageUrl;
        private double starPointAverage;
        private long totalReviewCount;
        private List<GradeTagDto.Response> gradeTags;
        private List<SubjectTagDto.Response> subjectTags;
        private List<PlatformTagDto.Response> platformTags;
    }

    //강사디테일조회
    @Getter @Setter
    public static class DetailPageResponse {
        private Long teacherId;
        private String name;
        private String introduction;
        private String profileImageUrl;
        private String realImageUrl;
        private List<String> profile;
        private List<String> analects;
        private double starPointAverage;
        private long totalReviewCount;
        private List<GradeTagDto.Response> gradeTags;
        private List<SubjectTagDto.Response> subjectTags;
        private List<PlatformTagDto.Response> platformTags;
        private List<LectureDto.ListPageResponse> lectures;
    }

    @Getter @Setter
    public static class ReviewDetailPageResponse {
        private Long teacherId;
        private String name;
        private String introduction;
        private String profileImageUrl;
        private String realImageUrl;
        private List<String> profile;
        private List<String> analects;
        private double starPointAverage;
        private long totalReviewCount;
        private Map<String,Long> starPointCount;
        private List<GradeTagDto.Response> gradeTags;
        private List<SubjectTagDto.Response> subjectTags;
        private List<PlatformTagDto.Response> platformTags;
        private List<LectureDto.TeacherReviewDetailPageResponse> lectures;
    }
    @Getter @Setter
    public static class SimpleInfoResponse {
        private Long teacherId;
        private String name;
        private double starPointAverage;
    }
}
