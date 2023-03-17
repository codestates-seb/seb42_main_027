package ynzmz.server.event.outer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.event.outer.dto.EventDto;
import ynzmz.server.event.outer.entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T14:52:05+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.16.1 (Oracle Corporation)"
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
