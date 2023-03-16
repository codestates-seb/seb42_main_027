package ynzmz.server.event.inner.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.event.inner.dto.YjEventDto;
import ynzmz.server.event.inner.entity.YjEvent;

@Mapper(componentModel = "spring")
public interface YjEventMapper {
    YjEvent yjEventPostToYjEvent(YjEventDto.Post p);


    YjEvent yjEventPatchToYjEvent(YjEventDto.Patch p);

    YjEvent YjEventResponseToyjEvent(YjEventDto.Response r);



}
