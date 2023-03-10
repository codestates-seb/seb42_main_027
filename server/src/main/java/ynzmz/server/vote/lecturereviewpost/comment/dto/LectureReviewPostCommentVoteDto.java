package ynzmz.server.vote.lecturereviewpost.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
import ynzmz.server.vote.lecturereviewpost.comment.entity.LectureReviewPostCommentVote;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity.LectureReviewPostVote;

public class LectureReviewPostCommentVoteDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class Response{
        private LectureReviewPostCommentVote.VoteStatus voteStatus;
        private long lectureReviewPostCommentId;
        private long memberId;
        private long lectureReviewPostTotalCount;
    }
}
