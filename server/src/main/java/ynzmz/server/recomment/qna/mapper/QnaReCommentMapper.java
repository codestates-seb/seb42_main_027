package ynzmz.server.recomment.qna.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.recomment.qna.dto.QnaReCommentDto;
import ynzmz.server.recomment.qna.entity.QnaReComment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QnaReCommentMapper {
    QnaReComment qnaReCommentPostToQnaReComment(QnaReCommentDto.Post qnaReCommentPostDto);
    QnaReComment qnaReCommentPatchToQnaReComment(QnaReCommentDto.Patch qnaReCommentPatchDto);
    QnaReCommentDto.Response qnaReCommentToQnaReCommentResponse(QnaReComment qnaReComment);
    List<QnaReCommentDto.Response> qnaReCommentToQnaReCommentResponses(List<QnaReComment> qnaReComments);
}
