package ynzmz.server.vote.review.lecture.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.vote.review.lecture.dto.ReviewVoteDto;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

@Mapper(componentModel = "spring")
public interface ReviewVoteMapper {
    default ReviewVoteDto.Response lectureReviewVoteToLectureReviewResponse(ReviewVote reviewVote) {

        ReviewVoteDto.Response lectureReviewVoteResponse = new ReviewVoteDto.Response();

        lectureReviewVoteResponse.setTarget(reviewVote.getTarget());
        lectureReviewVoteResponse.setStatus(reviewVote.getStatus());
        lectureReviewVoteResponse.setLectureReviewId(reviewVote.getLectureReview().getLectureReviewId());
        lectureReviewVoteResponse.setLectureReviewCommentId(reviewVote.getLectureReviewComment().getLectureReviewCommentId());
        lectureReviewVoteResponse.setMemberId(reviewVote.getMember().getMemberId());
        lectureReviewVoteResponse.setLectureReviewTotalCount(reviewVote.getLectureReview().getVoteCount());
        lectureReviewVoteResponse.setLectureReviewCommentTotalCount(reviewVote.getLectureReviewComment().getVoteCount());
        return lectureReviewVoteResponse;
    }
}
