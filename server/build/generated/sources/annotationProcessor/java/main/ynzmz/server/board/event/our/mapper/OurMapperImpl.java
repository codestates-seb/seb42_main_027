package ynzmz.server.board.event.our.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.event.our.dto.OurDto;
import ynzmz.server.board.event.our.entity.Our;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-29T11:11:04+0900",
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

        our.setImageUrl( p.getImageUrl() );
        our.setTitle( p.getTitle() );
        our.setContent( p.getContent() );
        our.setDate( p.getDate() );
        our.setViewCount( p.getViewCount() );

        return our;
    }

    @Override
    public Our ourPatchToOur(OurDto.Patch p) {
        if ( p == null ) {
            return null;
        }

        Our our = new Our();

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
        String title = null;
        String content = null;
        String date = null;
        int viewCount = 0;

        if ( event.getEventId() != null ) {
            eventId = event.getEventId();
        }
        title = event.getTitle();
        content = event.getContent();
        date = event.getDate();
        viewCount = event.getViewCount();

        OurDto.Response response = new OurDto.Response( eventId, title, content, date, viewCount );

        return response;
    }

    @Override
    public List<OurDto.ListResponse> ourToOurListResponse(List<Our> event) {
        if ( event == null ) {
            return null;
        }

        List<OurDto.ListResponse> list = new ArrayList<OurDto.ListResponse>( event.size() );
        for ( Our our : event ) {
            list.add( ourToListResponse( our ) );
        }

        return list;
    }

    protected OurDto.ListResponse ourToListResponse(Our our) {
        if ( our == null ) {
            return null;
        }

        long eventId = 0L;
        String imageUrl = null;
        String title = null;
        String date = null;
        int viewCount = 0;

        if ( our.getEventId() != null ) {
            eventId = our.getEventId();
        }
        imageUrl = our.getImageUrl();
        title = our.getTitle();
        date = our.getDate();
        viewCount = our.getViewCount();

        OurDto.ListResponse listResponse = new OurDto.ListResponse( eventId, imageUrl, title, date, viewCount );

        return listResponse;
    }
}
