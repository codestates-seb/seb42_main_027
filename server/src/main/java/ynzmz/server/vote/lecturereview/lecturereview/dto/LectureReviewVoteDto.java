package ynzmz.server.vote.lecturereview.lecturereview.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.lecturereview.lecturereview.entity.LectureReviewVote;

public class LectureReviewVoteDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class Response{
        private LectureReviewVote.VoteStatus voteStatus;
        private long lectureReviewId;
        private long memberId;
        private long lectureReviewTotalCount;
    }
}
