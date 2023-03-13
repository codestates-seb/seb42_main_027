package ynzmz.server.vote.lecturereview.comment.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.vote.lecturereview.comment.dto.LectureReviewCommentVoteDto;
import ynzmz.server.vote.lecturereview.comment.entity.LectureReviewCommentVote;

@Mapper(componentModel = "spring")
public interface LectureReviewCommentVoteMapper {
    default LectureReviewCommentVoteDto.Response lectureReviewCommentVoteToLectureReviewCommentVoteResponse(LectureReviewCommentVote lectureReviewCommentVote, LectureReviewComment lectureReviewComment) {

        LectureReviewCommentVoteDto.Response lectureReviewCommnetVoteResponse = new LectureReviewCommentVoteDto.Response();

        lectureReviewCommnetVoteResponse.setVoteStatus(lectureReviewCommentVote.getVoteStatus());
        lectureReviewCommnetVoteResponse.setLectureReviewCommentId(lectureReviewCommentVote.getLectureReviewComment().getLectureReviewCommentId());
        lectureReviewCommnetVoteResponse.setMemberId(lectureReviewCommentVote.getMember().getMemberId());
        lectureReviewCommnetVoteResponse.setLectureReviewCommentTotalCount(lectureReviewComment.getVoteCount());
        return lectureReviewCommnetVoteResponse;
    }
}
