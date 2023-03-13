package ynzmz.server.comment.lecturereview.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.comment.lecturereview.dto.LectureReviewCommentDto;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LectureReviewPostCommentMapper {
    LectureReviewComment lectureReviewCommentToLectureReviewCommentPost(LectureReviewCommentDto.Post lectureReviewPostCommentPostDto);
    LectureReviewComment lectureReviewCommentToLectureReviewCommentPatch(LectureReviewCommentDto.Patch lectureReviewPostCommentPatchDto);
    LectureReviewCommentDto.Response lectureReviewCommentResponseToLectureReviewComment(LectureReviewComment lectureReviewComment);

    List<LectureReviewCommentDto.Response> lectureReviewCommentResponsesToLectureReviewComments(List<LectureReviewComment> lectureReviewComments);
}
