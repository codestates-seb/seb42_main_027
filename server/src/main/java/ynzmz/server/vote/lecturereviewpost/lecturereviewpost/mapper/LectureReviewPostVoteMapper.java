package ynzmz.server.vote.lecturereviewpost.lecturereviewpost.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.dto.LectureReviewPostVoteDto;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity.LectureReviewPostVote;

@Mapper(componentModel = "spring")
public interface LectureReviewPostVoteMapper {
    default LectureReviewPostVoteDto.Response lectureReviewPostVoteToLectureReviewPostResponse(LectureReviewPostVote lectureReviewPostVote, LectureReviewPost lectureReviewPost) {

        LectureReviewPostVoteDto.Response lectureReviewPostVoteResponse = new LectureReviewPostVoteDto.Response();

        lectureReviewPostVoteResponse.setVoteStatus(lectureReviewPostVote.getVoteStatus());
        lectureReviewPostVoteResponse.setLectureReviewPostId(lectureReviewPostVote.getLectureReviewPost().getLectureReviewPostId());
        lectureReviewPostVoteResponse.setMemberId(lectureReviewPostVote.getMember().getMemberId());
        lectureReviewPostVoteResponse.setLectureReviewPostTotalCount(lectureReviewPost.getVoteCount());
        return lectureReviewPostVoteResponse;
    }
}
