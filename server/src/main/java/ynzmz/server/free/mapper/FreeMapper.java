package ynzmz.server.free.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.free.dto.FreeDto;
import ynzmz.server.free.entity.Free;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreeMapper {
    Free freeToFreePost(FreeDto.post p);

}
