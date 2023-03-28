package ynzmz.server.vote.review.lecture.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

public class ReviewVoteDto {
    @Getter @Setter
    @NoArgsConstructor
    public static class ReviewResponse{
        private Vote.Status Status;
        private Vote.Target target;
        private long lectureReviewId;
        private long lectureReviewVoteTotalCount;
        private MemberDto.SimpleInfoResponse member;
    }
    @Getter @Setter
    @NoArgsConstructor
    public static class CommentResponse{
        private Vote.Status Status;
        private Vote.Target target;
        private long lectureReviewCommentId;
        private long lectureReviewCommentVoteTotalCount;
        private MemberDto.SimpleInfoResponse member;
    }
}
