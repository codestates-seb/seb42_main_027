package ynzmz.server.comment.free.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreeCommentMapper {
    FreeComment freeCommentPostToFreeComment(FreeCommentDto.Post FreeCommentPostDto);
    FreeComment freeCommentPatchToFreeComment(FreeCommentDto.Patch FreeCommentPatchDto);
    FreeCommentDto.Response freeCommentToFreeCommentResponse(FreeComment freeComment);

    List<FreeCommentDto.SimpleResponse> freeCommentToFreeCommentsResponses(List<FreeComment> freeComments);
}
