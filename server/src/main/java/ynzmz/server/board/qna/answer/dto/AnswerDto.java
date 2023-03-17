package ynzmz.server.board.qna.answer.dto;

import lombok.*;
import ynzmz.server.board.qna.question.dto.QuestionDto;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.board.qna.answer.entity.Answer;

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
        private MemberDto.SimpleInfoResponse member;
        private QuestionDto.ListPageResponse question;
    }
}
