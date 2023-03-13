package ynzmz.server.lecturereview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.teacher.dto.TeacherDto;

import java.util.List;

public class LectureReviewDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String title;
        private long starPoint;
        private String content;
        private long memberId;
        private long lectureId;
        private String createdAt;
    }
    @Getter
    @AllArgsConstructor
    public static class Patch{
        private String title;
        private long starPoint;
        private String content;
        private String modifiedAt;
    }
    @Getter @Setter
    public static class ListPageResponse{

        private long lectureReviewId;
        private String title;
        private long starPoint;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private TeacherDto.SimpleInfoResponse teacher;
        private LectureDto.SimpleInfoResponse lecture;
        private Member member;
    }

    @Getter @Setter
    public static class DetailPageResponse{

        private long lectureReviewId;
        private String title;
        private long starPoint;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private TeacherDto.SimpleInfoResponse teacher;
        private LectureDto.SimpleInfoResponse lecture;
        private Member member;
        private List<LectureReviewComment> comments;
    }
    @Getter @Setter
    public static class InfoResponse{

        private long lectureReviewId;
        private String title;
        private long starPoint;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private LectureDto.SimpleInfoResponse lecture;
        private Member member;
    }
}
