package ynzmz.server.comment.review.lecture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LectureReviewPostCommentMapper {
    LectureReviewComment lectureReviewCommentToLectureReviewCommentPost(LectureReviewCommentDto.Post lectureReviewPostCommentPostDto);
    LectureReviewComment lectureReviewCommentToLectureReviewCommentPatch(LectureReviewCommentDto.Patch lectureReviewPostCommentPatchDto);
    LectureReviewCommentDto.Response lectureReviewCommentResponseToLectureReviewComment(LectureReviewComment lectureReviewComment);

    List<LectureReviewCommentDto.Response> lectureReviewCommentResponsesToLectureReviewComments(List<LectureReviewComment> lectureReviewComments);
}
