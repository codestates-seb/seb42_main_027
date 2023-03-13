package ynzmz.server.vote.lecturereview.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.lecturereview.comment.entity.LectureReviewCommentVote;

public class LectureReviewCommentVoteDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class Response{
        private LectureReviewCommentVote.VoteStatus voteStatus;
        private long lectureReviewCommentId;
        private long memberId;
        private long lectureReviewCommentTotalCount;
    }
}
