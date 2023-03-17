package ynzmz.server.comment.free.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreePostCommentMapper {
    FreeComment FreeCommentToFreeCommentPost(FreeCommentDto.Post FreeCommentPostDto);
    FreeComment FreeCommentToFreeCommentPatch(FreeCommentDto.Patch FreeCommentPatchDto);
    FreeCommentDto.Response FreeCommentResponseToFreeComment(FreeComment freeComment);

    List<FreeCommentDto.Response> FreeCommentResponsesToFreeComments(List<FreeComment> freeComments);
}
