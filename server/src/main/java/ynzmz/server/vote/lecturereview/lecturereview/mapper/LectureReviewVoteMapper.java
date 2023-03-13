package ynzmz.server.vote.lecturereview.lecturereview.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.vote.lecturereview.lecturereview.dto.LectureReviewVoteDto;
import ynzmz.server.vote.lecturereview.lecturereview.entity.LectureReviewVote;

@Mapper(componentModel = "spring")
public interface LectureReviewVoteMapper {
    default LectureReviewVoteDto.Response lectureReviewPostVoteToLectureReviewPostResponse(LectureReviewVote lectureReviewVote, LectureReview lectureReview) {

        LectureReviewVoteDto.Response lectureReviewVoteResponse = new LectureReviewVoteDto.Response();

        lectureReviewVoteResponse.setVoteStatus(lectureReviewVote.getVoteStatus());
        lectureReviewVoteResponse.setLectureReviewId(lectureReviewVote.getLectureReview().getLectureReviewId());
        lectureReviewVoteResponse.setMemberId(lectureReviewVote.getMember().getMemberId());
        lectureReviewVoteResponse.setLectureReviewTotalCount(lectureReview.getVoteCount());
        return lectureReviewVoteResponse;
    }
}
