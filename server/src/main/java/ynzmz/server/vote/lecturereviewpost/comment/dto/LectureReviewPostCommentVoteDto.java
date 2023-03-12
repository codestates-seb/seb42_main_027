package ynzmz.server.vote.lecturereviewpost.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.lecturereviewpost.comment.entity.LectureReviewPostCommentVote;

public class LectureReviewPostCommentVoteDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class Response{
        private LectureReviewPostCommentVote.VoteStatus voteStatus;
        private long lectureReviewPostCommentId;
        private long memberId;
        private long lectureReviewPostCommentTotalCount;
    }
}
