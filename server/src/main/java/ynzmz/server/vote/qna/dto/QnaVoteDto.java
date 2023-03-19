package ynzmz.server.vote.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.Vote;

public class QnaVoteDto {
    @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class QuestionResponse {
        private Vote.Status status;
        private Vote.Target target;
        private long memberId;
        private long questionId;
        private long questionVoteTotalCount;
    }
    @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class AnswerResponse {
        private Vote.Status status;
        private Vote.Target target;
        private long memberId;
        private long answerId;
        private long answerVoteTotalCount;
    }
    @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class CommentResponse {
        private Vote.Status status;
        private Vote.Target target;
        private long memberId;
        private long commentId;
        private long commentVoteTotalCount;
    }
    @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class ReCommentResponse {
        private Vote.Status status;
        private Vote.Target target;
        private long memberId;
        private long recommentId;
        private long recommentVoteTotalCount;
    }

}
