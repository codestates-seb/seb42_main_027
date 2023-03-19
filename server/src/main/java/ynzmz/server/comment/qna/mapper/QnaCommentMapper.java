package ynzmz.server.comment.qna.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.comment.qna.dto.QnaCommentDto;
import ynzmz.server.comment.qna.entity.QnaComment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QnaCommentMapper {
    QnaComment qnaCommentPostToQnaComment(QnaCommentDto.Post lectureReviewPostCommentPostDto);
    QnaComment qnaCommentPatchToQnaComment(QnaCommentDto.Patch lectureReviewPostCommentPatchDto);
    QnaCommentDto.Response qnaCommentToQnaCommentResponse(QnaComment lectureReviewComment);
    List<QnaCommentDto.Response> qnaCommentsToQnaCommentResponses(List<QnaComment> lectureReviewComments);
}
