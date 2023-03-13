package ynzmz.server.vote.lecturereview.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.lecturereview.entity.LectureReviewVote;

public class LectureReviewVoteDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class Response{
        private LectureReviewVote.VoteStatus voteStatus;
        private LectureReviewVote.Target target;
        private long lectureReviewId;
        private long lectureReviewCommentId;
        private long memberId;
        private long lectureReviewTotalCount;
        private long lectureReviewCommentTotalCount;
    }
}
