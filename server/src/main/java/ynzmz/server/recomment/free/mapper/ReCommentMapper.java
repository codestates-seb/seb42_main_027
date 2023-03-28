package ynzmz.server.recomment.free.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.recomment.free.dto.ReCommentDto;
import ynzmz.server.recomment.free.entity.FreeReComment;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReCommentMapper {
    FreeReComment recommentPostToRecomment(ReCommentDto.Post RecommentPostDto);

    FreeReComment recommentPatchToRecomment(ReCommentDto.Patch RecommentPatchDto);

    ReCommentDto.Response recommentToRecommentResponse(FreeReComment recomment);

    List<ReCommentDto.SimpleResponse> freeReCommentToFreeReCommentsSimpleResponses(List<FreeReComment> ReComments);


}