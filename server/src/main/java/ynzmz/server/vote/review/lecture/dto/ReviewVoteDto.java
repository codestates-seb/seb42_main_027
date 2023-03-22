package ynzmz.server.vote.review.lecture.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

public class ReviewVoteDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class Response{
        private Vote.Status Status;
        private Vote.Target target;
        private long lectureReviewId;
        private long lectureReviewCommentId;
        private long memberId;
        private long lectureReviewTotalCount;
        private long lectureReviewCommentTotalCount;
    }
}
