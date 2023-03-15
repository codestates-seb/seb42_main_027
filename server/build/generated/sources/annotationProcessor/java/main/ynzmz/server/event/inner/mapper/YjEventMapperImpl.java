package ynzmz.server.event.inner.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.event.inner.dto.YjEventDto;
import ynzmz.server.event.inner.entity.YjEvent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T04:16:25+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class YjEventMapperImpl implements YjEventMapper {

    @Override
    public YjEventDto.Post yjEventToYjEventPost(YjEvent e) {
        if ( e == null ) {
            return null;
        }

        long eventId = 0L;
        String imageUrl = null;
        String title = null;
        String date = null;

        eventId = e.getEventId();
        imageUrl = e.getImageUrl();
        title = e.getTitle();
        date = e.getDate();

        String hyperLink = null;

        YjEventDto.Post post = new YjEventDto.Post( eventId, imageUrl, title, hyperLink, date );

        return post;
    }

    @Override
    public YjEventDto.Patch yjEventToYjEventPatch(YjEvent e) {
        if ( e == null ) {
            return null;
        }

        long eventId = 0L;
        String imageUrl = null;
        String title = null;
        String date = null;

        eventId = e.getEventId();
        imageUrl = e.getImageUrl();
        title = e.getTitle();
        date = e.getDate();

        String hyperLink = null;

        YjEventDto.Patch patch = new YjEventDto.Patch( eventId, imageUrl, title, hyperLink, date );

        return patch;
    }

    @Override
    public YjEventDto.Response yjEventToYjEventResponse(YjEvent e) {
        if ( e == null ) {
            return null;
        }

        long eventId = 0L;
        String imageUrl = null;
        String title = null;
        String date = null;

        eventId = e.getEventId();
        imageUrl = e.getImageUrl();
        title = e.getTitle();
        date = e.getDate();

        String hyperLink = null;

        YjEventDto.Response response = new YjEventDto.Response( eventId, imageUrl, title, hyperLink, date );

        return response;
    }

    @Override
    public YjEvent yjEventResponseToYjEvent(YjEventDto.Response r) {
        if ( r == null ) {
            return null;
        }

        YjEvent yjEvent = new YjEvent();

        yjEvent.setEventId( r.getEventId() );
        yjEvent.setImageUrl( r.getImageUrl() );
        yjEvent.setTitle( r.getTitle() );
        yjEvent.setDate( r.getDate() );

        return yjEvent;
    }
}
