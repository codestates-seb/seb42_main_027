package ynzmz.server.board.review.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.board.lecture.dto.LectureDto;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.board.teacher.dto.TeacherDto;

import java.util.List;

public class LectureReviewDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post{
        private String title;
        private long starPoint;
        private String content;
        private long lectureId;
        private String createdAt;
    }
    @Getter
    @AllArgsConstructor
    @Builder
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
        private long totalCommentCount;
        private LectureDto.SimpleInfoResponse lecture;
        private MemberDto.SimpleInfoResponse member;
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
        private long totalCommentCount;
        private TeacherDto.SimpleInfoResponse teacher;
        private LectureDto.SimpleInfoResponse lecture;
        private MemberDto.SimpleInfoResponse member;
        private List<LectureReviewCommentDto.Response> comments;
        private MemberDto.LoginUserLectureReviewVoteInfo loginUserLectureReviewVoteInfo;
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
        private MemberDto.SimpleInfoResponse member;
    }

    @Getter @Setter
    public static class SimpleResponse {
        private Long lectureReviewId;
        private String title;
    }
}
