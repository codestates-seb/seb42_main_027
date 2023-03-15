package ynzmz.server.event.inner.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.event.inner.dto.YjEventDto;
import ynzmz.server.event.inner.entity.YjEvent;

@Mapper(componentModel = "spring")
public interface YjEventMapper {
    YjEventDto.Post yjEventToYjEventPost(YjEvent e);
    YjEventDto.Patch yjEventToYjEventPatch(YjEvent e);

    YjEventDto.Response yjEventToYjEventResponse(YjEvent e);

    YjEvent yjEventResponseToYjEvent(YjEventDto.Response r);

}
