package ynzmz.server.board.free.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.review.lecture.dto.LectureReviewDto;
import ynzmz.server.board.review.lecture.entity.LectureReview;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreeMapper {
    Free freeToFreePost(FreeDto.post p);
    List<FreeDto.ListResponse> freeTofreeListResponse(List<Free> frees);
}
