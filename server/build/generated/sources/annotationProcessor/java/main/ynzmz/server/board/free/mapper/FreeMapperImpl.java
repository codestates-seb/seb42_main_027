package ynzmz.server.board.free.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.free.entity.Free;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-19T01:16:27+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class FreeMapperImpl implements FreeMapper {

    @Override
    public Free freeToFreePost(FreeDto.post p) {
        if ( p == null ) {
            return null;
        }

        Free free = new Free();

        free.setId( p.getId() );
        free.setTitle( p.getTitle() );
        free.setContent( p.getContent() );
        free.setViewCount( p.getViewCount() );
        free.setVoteCount( p.getVoteCount() );
        free.setCreatedAt( p.getCreatedAt() );
        free.setModifiedAt( p.getModifiedAt() );

        return free;
    }
}
