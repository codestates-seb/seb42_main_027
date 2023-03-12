package ynzmz.server.comment.lecturereviewpost.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.member.entity.Member;

public class LectureReviewPostCommentDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String content;
        private String createdAt;
        private long lectureReviewPostId;
        private long memberId;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private String content;
        private String modifiedAt;
    }
    @Setter @Getter
    @NoArgsConstructor
    public static class Response{

        private Long lectureReviewPostCommentId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private long lectureReviewPostId;
        private Member member;
    }
}
