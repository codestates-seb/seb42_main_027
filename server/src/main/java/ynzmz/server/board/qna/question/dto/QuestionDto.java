package ynzmz.server.board.qna.question.dto;

import lombok.*;
import ynzmz.server.comment.qna.dto.QnaCommentDto;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.board.qna.answer.dto.AnswerDto;

import java.util.List;

public class QuestionDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post {
        private String title;
        private String content;
        private String category;
        private String createdAt;
        private List<String> uploadImages;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Patch {
        private String title;
        private String content;
        private String category;
        private String modifiedAt;
        private List<String> uploadImages;
    }
    @Getter
    @Setter
    public static class ListPageResponse {
        private long questionId;
        private String title;
        private String content;
        private String category;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private long answerCount; //수동추가
        private long adoptAnswerId;
        private MemberDto.SimpleInfoResponse member;
    }

    @Getter
    @Setter
    public static class InfoResponse {
        private long questionId;
        private MemberDto.SimpleInfoResponse member;
        private String title;
        private String content;
        private String category;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
    }

    @Getter
    @Setter
    public static class DetailPageResponse {
        private long questionId;
        private MemberDto.SimpleInfoResponse member;
        private String title;
        private String content;
        private String category;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private long adoptAnswerId;
        private long commentCount;
        private long answerCount;
        private List<AnswerDto.Response> answers;
        private List<QnaCommentDto.Response> comments;
        private MemberDto.VoteInfo loginUserVoteInfo;
    }

    @Getter
    @Setter
    public static class SimpleInfoResponse {
        private long questionId;
        private String title;
        private String category;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private Long adoptAnswerId;
        private MemberDto.SimpleInfoResponse member;
    }

    @Getter @Setter
    public static class SimpleResponse{
        private Long questionId;
        private String title;
    }
}