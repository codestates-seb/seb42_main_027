package ynzmz.server.recomment.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.comment.qna.dto.QnaCommentDto;
import ynzmz.server.member.dto.MemberDto;

public class QnaReCommentDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String content;
        private String createdAt;
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

        private long qnaReCommentId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private MemberDto.SimpleInfoResponse member;
//        private QuestionDto.SimpleInfoResponse question;
//        private AnswerDto.SimpleInfoResponse answer;
    }

    @Getter @Setter
    public static class SimpleResponse{
        private long qnaReCommentId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private QnaCommentDto.SimpleResponse qnaComment;
    }
}
