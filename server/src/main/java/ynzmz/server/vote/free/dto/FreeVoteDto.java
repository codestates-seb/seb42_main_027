package ynzmz.server.vote.free.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.Vote;

public class FreeVoteDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class Response{
        private Vote.Status Status;
        private Vote.Target target;
        private long freeId;
        private long freeCommentId;
        private long memberId;
        private long freeTotalCount;
        private long freeCommentTotalCount;
    }
}
