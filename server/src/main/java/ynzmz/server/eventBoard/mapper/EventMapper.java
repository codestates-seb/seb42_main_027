package ynzmz.server.eventBoard.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.eventBoard.dto.EventDto;
import ynzmz.server.eventBoard.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event eventToEventPost(EventDto.Post post);

    Event eventToEventResponse(EventDto.Response response);

    List<Event> eventToEventResponses(List<EventDto.Response> response);

}
