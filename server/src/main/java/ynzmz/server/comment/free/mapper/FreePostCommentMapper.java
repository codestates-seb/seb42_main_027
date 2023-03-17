package ynzmz.server.comment.free.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreePostCommentMapper {
    FreeComment lectureReviewCommentToLectureReviewCommentPost(FreeCommentDto.Post lectureReviewPostCommentPostDto);
    FreeComment lectureReviewCommentToLectureReviewCommentPatch(FreeCommentDto.Patch lectureReviewPostCommentPatchDto);
    FreeCommentDto.Response lectureReviewCommentResponseToLectureReviewComment(FreeComment freeComment);

    List<FreeCommentDto.Response> lectureReviewCommentResponsesToLectureReviewComments(List<FreeComment> freeComments);
}
