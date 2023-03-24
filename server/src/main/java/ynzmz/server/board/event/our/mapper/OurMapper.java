package ynzmz.server.board.event.our.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.board.event.our.entity.Our;
import ynzmz.server.board.event.our.dto.OurDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OurMapper {
    Our ourPostToOur(OurDto.Post p);


    Our ourPatchToOur(OurDto.Patch p);

    OurDto.Response ourToOurResponse(Our event);

    List<OurDto.ListResponse> ourToOurListResponse(List<Our> event);


}
