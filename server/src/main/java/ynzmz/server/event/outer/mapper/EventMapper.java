package ynzmz.server.event.outer.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.event.outer.dto.EventDto;
import ynzmz.server.event.outer.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto.Post eventToEventPost(Event event);

    EventDto.Response eventToEventResponse(Event event);

    List<EventDto.Response> eventToEventResponses(List<Event> events);

}
