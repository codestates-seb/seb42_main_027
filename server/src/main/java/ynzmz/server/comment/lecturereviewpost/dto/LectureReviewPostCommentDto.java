package ynzmz.server.comment.lecturereviewpost.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.member.entity.Member;

import javax.persistence.*;

public class LectureReviewPostCommentDto {
    @Getter
    public static class Post{
        private String content;
        private String createdAt;
        private long voteCount;
        private long memberId;
    }

    @Getter
    public static class Patch{
        private String content;
        private String modifiedAt;
        private long voteCount;
    }
    @Setter @Getter
    @NoArgsConstructor
    public static class Response{

        private Long lectureReviewPostCommentId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private Member member;
    }
}
