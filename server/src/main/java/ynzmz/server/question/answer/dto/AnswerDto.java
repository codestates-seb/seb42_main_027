package ynzmz.server.question.answer.dto;

import lombok.*;
import ynzmz.server.member.entity.Member;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.question.dto.QuestionDto;

public class AnswerDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post {
        private long questionId;
        private String content;
        private String createdAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private Long answerId;
        private String content;

    }
    @Getter @Setter
    public static class InfoResponse {
        private Long answerId;
        private QuestionDto.InfoResponse questionId;
        private Member member; // 질문자 회원정보
        private String content;
        private int voteCount;
        private String createdAt;
        private String modifiedAt;
        private Answer.AdoptStatus adoptStatus;
    }
    @Getter @Setter
    public static class Response {
        private Long answerId;
        private String content;
        private int voteCount;
        private String createdAt;
        private String modifiedAt;
        private Answer.AdoptStatus adoptStatus;
        private Member member;
        private QuestionDto.ListPageResponse question;
    }
}
