package ynzmz.server.comment.review.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;

public class LectureReviewCommentDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String content;
        private String createdAt;
        private long lectureReviewId;
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

        private Long lectureReviewCommentId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private MemberDto.SimpleInfoResponse member;
    }
}
