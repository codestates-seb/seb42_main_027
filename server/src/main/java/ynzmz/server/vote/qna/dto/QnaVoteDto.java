package ynzmz.server.vote.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.Vote;

public class QnaVoteDto {
    @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor
    public static class Response {
        private Vote.Status status;
        private Vote.Target target;
        private long questionId;
        private long answerId;
        private long memberId;
        private long questionVoteTotalCount;
        private long answerVoteTotalCount;
    }

}
