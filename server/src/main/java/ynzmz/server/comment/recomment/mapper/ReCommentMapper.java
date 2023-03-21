package ynzmz.server.comment.recomment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.comment.recomment.dto.ReCommentDto;
import ynzmz.server.comment.recomment.entity.ReComment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReCommentMapper {
    ReComment recommentPostToRecomment(ReCommentDto.Post RecommentPostDto);
    ReComment recommentPatchToRecomment(ReCommentDto.Patch RecommentPatchDto);
    ReCommentDto.Response recommentToRecommentResponse(ReComment recomment);

    List<ReCommentDto.Response> freeCommentToFreeCommentsResponses(List<ReComment> reComments);
}
