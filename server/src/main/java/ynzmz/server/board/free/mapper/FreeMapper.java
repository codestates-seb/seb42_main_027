package ynzmz.server.board.free.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.dto.FreeDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreeMapper {
    Free freeToFreePost(FreeDto.post p);

}
