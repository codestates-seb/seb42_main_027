package ynzmz.server.board.event.our.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.board.event.our.entity.Our;
import ynzmz.server.board.event.our.dto.OurDto;

@Mapper(componentModel = "spring")
public interface OurMapper {
    Our ourPostToOur(OurDto.Post p);


    Our ourPatchToOur(OurDto.Patch p);

    OurDto.Response ourToOurResponse(Our event);


}
