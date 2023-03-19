package ynzmz.server.board.event.their.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.event.their.dto.EventDto;
import ynzmz.server.board.event.their.entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-18T10:44:00+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDto.Post eventToEventPost(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDto.Post.PostBuilder post = EventDto.Post.builder();

        post.imageUrl( event.getImageUrl() );
        post.title( event.getTitle() );
        post.hyperLink( event.getHyperLink() );
        post.date( event.getDate() );
        post.source( event.getSource() );

        return post.build();
    }

    @Override
    public EventDto.Response eventToEventResponse(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDto.Response.ResponseBuilder response = EventDto.Response.builder();

        response.imageUrl( event.getImageUrl() );
        response.title( event.getTitle() );
        response.hyperLink( event.getHyperLink() );
        response.date( event.getDate() );
        response.source( event.getSource() );

        return response.build();
    }

    @Override
    public List<EventDto.Response> eventToEventResponses(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventDto.Response> list = new ArrayList<EventDto.Response>( events.size() );
        for ( Event event : events ) {
            list.add( eventToEventResponse( event ) );
        }

        return list;
    }
}
