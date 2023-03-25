package ynzmz.server.vote.review.lecture.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.vote.review.lecture.dto.ReviewVoteDto;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

@Mapper(componentModel = "spring")
public interface ReviewVoteMapper {
    ReviewVoteDto.ReviewResponse lectureReviewVoteToReviewVoteReviewResponse(ReviewVote reviewVote);
    ReviewVoteDto.CommentResponse lectureReviewVoteToReviewVoteCommentResponse(ReviewVote reviewVote);
}
