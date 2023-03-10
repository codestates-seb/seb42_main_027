package ynzmz.server.vote.lecturereviewpost.lecturereviewpost.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity.LectureReviewPostVote;

import javax.persistence.GeneratedValue;

public class LectureReviewPostVoteDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class Response{
        private LectureReviewPostVote.VoteStatus voteStatus;
        private long lectureReviewPostId;
        private long memberId;
        private long lectureReviewPostTotalCount;
    }
}
