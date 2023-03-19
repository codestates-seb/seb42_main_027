package ynzmz.server.board.event.our.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.event.our.dto.OurDto;
import ynzmz.server.board.event.our.entity.Our;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-19T19:13:27+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class OurMapperImpl implements OurMapper {

    @Override
    public Our ourPostToOur(OurDto.Post p) {
        if ( p == null ) {
            return null;
        }

        Our our = new Our();

        our.setEventId( p.getEventId() );
        our.setImageUrl( p.getImageUrl() );
        our.setTitle( p.getTitle() );
        our.setContent( p.getContent() );
        our.setDate( p.getDate() );

        return our;
    }

    @Override
    public Our ourPatchToOur(OurDto.Patch p) {
        if ( p == null ) {
            return null;
        }

        Our our = new Our();

        our.setEventId( p.getEventId() );
        our.setImageUrl( p.getImageUrl() );
        our.setTitle( p.getTitle() );
        our.setContent( p.getContent() );
        our.setDate( p.getDate() );

        return our;
    }

    @Override
    public OurDto.Response ourToOurResponse(Our event) {
        if ( event == null ) {
            return null;
        }

        long eventId = 0L;
        String imageUrl = null;
        String title = null;
        String content = null;
        String date = null;

        eventId = event.getEventId();
        imageUrl = event.getImageUrl();
        title = event.getTitle();
        content = event.getContent();
        date = event.getDate();

        OurDto.Response response = new OurDto.Response( eventId, imageUrl, title, content, date );

        return response;
    }
}
