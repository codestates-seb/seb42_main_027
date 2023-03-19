package ynzmz.server.board.free.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.dto.FreeDto;


import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreeMapper {
    Free freePostToFree(FreeDto.post p);
    Free freePatchToFree(FreeDto.patch p);

    FreeDto.DetailResponse freeToFreeDetailResponse(Free free);
    FreeDto.ListResponse freeToFreeListResponse(Free free);

    List<FreeDto.ListResponse> freesToFreeListResponses(List<Free> frees);
}
