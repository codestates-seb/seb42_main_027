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
        lectureReviewVoteResponse.setMemberId(reviewVote.getMember().getMemberId());
        if( reviewVote.getLectureReview() != null ) {
            lectureReviewVoteResponse.setLectureReviewId(reviewVote.getLectureReview().getLectureReviewId());
            lectureReviewVoteResponse.setLectureReviewTotalCount(reviewVote.getLectureReview().getVoteCount());
        }
        if( reviewVote.getLectureReviewComment() != null ) {
            lectureReviewVoteResponse.setLectureReviewCommentId(reviewVote.getLectureReviewComment().getLectureReviewCommentId());
            lectureReviewVoteResponse.setLectureReviewCommentTotalCount(reviewVote.getLectureReviewComment().getVoteCount());
        }
        return lectureReviewVoteResponse;
    }
}
