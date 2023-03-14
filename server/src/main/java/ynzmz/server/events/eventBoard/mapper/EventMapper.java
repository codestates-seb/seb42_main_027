package ynzmz.server.events.eventBoard.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.events.eventBoard.dto.EventDto;
import ynzmz.server.events.eventBoard.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto.Post eventToEventPost(Event event);

    EventDto.Response eventToEventResponse(Event event);

    List<EventDto.Response> eventToEventResponses(List<Event> events);

}
