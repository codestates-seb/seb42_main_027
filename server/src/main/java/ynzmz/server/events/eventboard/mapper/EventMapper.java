package ynzmz.server.events.eventboard.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.events.eventboard.dto.EventDto;
import ynzmz.server.events.eventboard.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto.Post eventToEventPost(Event event);

    EventDto.Response eventToEventResponse(Event event);

    List<EventDto.Response> eventToEventResponses(List<Event> events);

}
