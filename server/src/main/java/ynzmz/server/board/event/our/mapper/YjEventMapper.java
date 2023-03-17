package ynzmz.server.board.event.our.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.board.event.our.entity.YjEvent;
import ynzmz.server.board.event.our.dto.YjEventDto;

@Mapper(componentModel = "spring")
public interface YjEventMapper {
    YjEvent yjEventPostToYjEvent(YjEventDto.Post p);


    YjEvent yjEventPatchToYjEvent(YjEventDto.Patch p);

    YjEvent YjEventResponseToyjEvent(YjEventDto.Response r);



}
