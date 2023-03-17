package ynzmz.server.question.question.dto;

import lombok.*;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.question.answer.dto.AnswerDto;
import ynzmz.server.tag.dto.SubjectTagDto;

import java.util.List;

public class QuestionDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post {
        private Long questionId;
        private String title;
        String content;
        String createdAt;
        long memberId;
        List<String> subjectTag;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Patch {
        private Long questionId;
        private String title;
        String content;
        String createdAt;
        long memberId;
        List<String> subjectTag;

    }
    @Getter
    @Setter
    public static class ListPageResponse {
        private Long questionId;
        private String title;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private long answerCount; //수동추가
        private long adoptAnswerId;
        private Member member;
        private List<SubjectTagDto.Response> subjectTags;
    }

    @Getter
    @Setter
    public static class InfoResponse {
        private Long questionId;
        private Member member;
        private String title;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private List<SubjectTagDto.Response> subjectTags;
    }

    @Getter
    @Setter
    public static class DetailPageResponse {
        private Long questionId;
        private Member member;
        private String title;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private long answerCount;
        private List<SubjectTagDto.Response> subjectTags;
        private List<AnswerDto.InfoResponse> answers;
        private MemberDto.VoteInfo loginUserInfo;
    }
}