package ynzmz.server.comment.review.lecture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LectureReviewCommentMapper {
    LectureReviewComment lectureReviewCommentPostToLectureReviewComment(LectureReviewCommentDto.Post lectureReviewPostCommentPostDto);
    LectureReviewComment lectureReviewCommentPatchToLectureReviewComment(LectureReviewCommentDto.Patch lectureReviewPostCommentPatchDto);
    LectureReviewCommentDto.Response lectureReviewCommentToLectureReviewCommentResponse(LectureReviewComment lectureReviewComment);
    List<LectureReviewCommentDto.Response> lectureReviewCommentsToLectureReviewCommentResponses(List<LectureReviewComment> lectureReviewComments);
}
