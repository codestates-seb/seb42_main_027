package ynzmz.server.vote.review.lecture.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.vote.review.lecture.dto.LectureReviewVoteDto;
import ynzmz.server.vote.review.lecture.entity.LectureReviewVote;

@Mapper(componentModel = "spring")
public interface LectureReviewVoteMapper {
    default LectureReviewVoteDto.Response lectureReviewVoteToLectureReviewResponse(LectureReviewVote lectureReviewVote) {

        LectureReviewVoteDto.Response lectureReviewVoteResponse = new LectureReviewVoteDto.Response();

        lectureReviewVoteResponse.setTarget(lectureReviewVote.getTarget());
        lectureReviewVoteResponse.setVoteStatus(lectureReviewVote.getVoteStatus());
        lectureReviewVoteResponse.setLectureReviewId(lectureReviewVote.getLectureReview().getLectureReviewId());
        lectureReviewVoteResponse.setLectureReviewCommentId(lectureReviewVote.getLectureReviewComment().getLectureReviewCommentId());
        lectureReviewVoteResponse.setMemberId(lectureReviewVote.getMember().getMemberId());
        lectureReviewVoteResponse.setLectureReviewTotalCount(lectureReviewVote.getLectureReview().getVoteCount());
        lectureReviewVoteResponse.setLectureReviewCommentTotalCount(lectureReviewVote.getLectureReviewComment().getVoteCount());
        return lectureReviewVoteResponse;
    }
}
