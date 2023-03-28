package ynzmz.server.board.qna.answer.dto;

import lombok.*;
import ynzmz.server.board.qna.question.dto.QuestionDto;
import ynzmz.server.comment.qna.dto.QnaCommentDto;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.board.qna.answer.entity.Answer;

import java.util.List;

public class AnswerDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post {
        private String content;
        private String createdAt;
        private long questionId;
        private List<String> uploadImages;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private String content;
        private String modifiedAt;
        private List<String> uploadImages;

    }
    @Getter @Setter
    public static class SimpleInfoResponse {
        private long answerId;
        private QuestionDto.SimpleInfoResponse question;
        private MemberDto.SimpleInfoResponse member; // 질문자 회원정보
        private String content;
        private long voteCount;
        private String createdAt;
        private String modifiedAt;
        private Answer.AdoptStatus adoptStatus;
    }
    @Getter @Setter
    public static class Response {
        private Long answerId;
        private String content;
        private long voteCount;
        private long commentCount;
        private String createdAt;
        private String modifiedAt;
        private Answer.AdoptStatus adoptStatus;
        private MemberDto.SimpleInfoResponse member;
        private List<QnaCommentDto.Response> comments;

    }

    @Getter @Setter
    public static class SimpleResponse{
        private Long answerId;
        private String content;
    }
}
