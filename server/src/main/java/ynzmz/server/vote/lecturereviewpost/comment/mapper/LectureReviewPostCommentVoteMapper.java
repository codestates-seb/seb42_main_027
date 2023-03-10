package ynzmz.server.vote.lecturereviewpost.comment.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
import ynzmz.server.vote.lecturereviewpost.comment.dto.LectureReviewPostCommentVoteDto;
import ynzmz.server.vote.lecturereviewpost.comment.entity.LectureReviewPostCommentVote;

@Mapper(componentModel = "spring")
public interface LectureReviewPostCommentVoteMapper {
    default LectureReviewPostCommentVoteDto.Response lectureReviewPostVoteToLectureReviewPostResponse(LectureReviewPostCommentVote lectureReviewPostCommentVote, LectureReviewPostComment lectureReviewPostComment) {

        LectureReviewPostCommentVoteDto.Response lectureReviewPostCommnetVoteResponse = new LectureReviewPostCommentVoteDto.Response();

        lectureReviewPostCommnetVoteResponse.setVoteStatus(lectureReviewPostCommentVote.getVoteStatus());
        lectureReviewPostCommnetVoteResponse.setLectureReviewPostCommentId(lectureReviewPostCommentVote.getLectureReviewPostComment().getLectureReviewPostCommentId());
        lectureReviewPostCommnetVoteResponse.setMemberId(lectureReviewPostCommentVote.getMember().getMemberId());
        lectureReviewPostCommnetVoteResponse.setLectureReviewPostTotalCount(lectureReviewPostComment.getVoteCount());
        return lectureReviewPostCommnetVoteResponse;
    }
}
