package ynzmz.server.events.outerevent.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.events.outerevent.dto.EventDto;
import ynzmz.server.events.outerevent.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto.Post eventToEventPost(Event event);

    EventDto.Response eventToEventResponse(Event event);

    List<EventDto.Response> eventToEventResponses(List<Event> events);

}
