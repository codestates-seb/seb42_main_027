package ynzmz.server.recomment.qna.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.recomment.qna.dto.QnaReCommentDto;
import ynzmz.server.recomment.qna.entity.QnaReComment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QnaReCommentMapper {
    QnaReComment qnaReCommentPostToQnaReComment(QnaReCommentDto.Post lectureReviewCommentPostDto);
    QnaReComment qnaReCommentPatchToQnaReComment(QnaReCommentDto.Patch lectureReviewCommentPatchDto);
    QnaReCommentDto.Response qnaReCommentToQnaReCommentResponse(QnaReComment lectureReviewReComment);
    List<QnaReCommentDto.Response> qnaCommentsToQnaCommentResponses(List<QnaReComment> lectureReviewComments);
    List<QnaReCommentDto.Response> qnaReCommentToQnaReCommentResponses(List<QnaReComment> qnaReComments);
}
