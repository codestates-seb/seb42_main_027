package ynzmz.server.event.outer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.event.outer.dto.EventDto;
import ynzmz.server.event.outer.entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-15T23:18:12+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDto.Post eventToEventPost(Event event) {
        if ( event == null ) {
            return null;
        }

        String imageUrl = null;
        String title = null;
        String hyperLink = null;
        String date = null;
        String source = null;

        imageUrl = event.getImageUrl();
        title = event.getTitle();
        hyperLink = event.getHyperLink();
        date = event.getDate();
        source = event.getSource();

        EventDto.Post post = new EventDto.Post( imageUrl, title, hyperLink, date, source );

        return post;
    }

    @Override
    public EventDto.Response eventToEventResponse(Event event) {
        if ( event == null ) {
            return null;
        }

        String imageUrl = null;
        String title = null;
        String hyperLink = null;
        String date = null;
        String source = null;

        imageUrl = event.getImageUrl();
        title = event.getTitle();
        hyperLink = event.getHyperLink();
        date = event.getDate();
        source = event.getSource();

        EventDto.Response response = new EventDto.Response( imageUrl, title, hyperLink, date, source );

        return response;
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
