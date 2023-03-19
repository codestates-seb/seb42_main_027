package ynzmz.server.board.event.their.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.board.event.their.entity.Event;
import ynzmz.server.board.event.their.dto.EventDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto.Post eventToEventPost(Event event);

    Event eventPostToevent(EventDto.Post p);

    EventDto.Response eventToEventResponse(Event event);

    List<EventDto.Response> eventToEventResponses(List<Event> events);

}
