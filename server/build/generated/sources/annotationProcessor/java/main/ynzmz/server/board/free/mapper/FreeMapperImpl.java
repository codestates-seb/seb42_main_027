package ynzmz.server.board.free.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.free.entity.Free;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-22T16:48:46+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class FreeMapperImpl implements FreeMapper {

    @Override
    public Free freePostToFree(FreeDto.post p) {
        if ( p == null ) {
            return null;
        }

        Free free = new Free();

        free.setTitle( p.getTitle() );
        free.setContent( p.getContent() );
        free.setCategory( p.getCategory() );
        free.setCreatedAt( p.getCreatedAt() );

        return free;
    }

    @Override
    public Free freePatchToFree(FreeDto.patch p) {
        if ( p == null ) {
            return null;
        }

        Free free = new Free();

        free.setTitle( p.getTitle() );
        free.setContent( p.getContent() );
        free.setCategory( p.getCategory() );
        free.setModifiedAt( p.getModifiedAt() );

        return free;
    }

    @Override
    public List<FreeDto.ListResponse> freesToFreeListResponses(List<Free> frees) {
        if ( frees == null ) {
            return null;
        }

        List<FreeDto.ListResponse> list = new ArrayList<FreeDto.ListResponse>( frees.size() );
        for ( Free free : frees ) {
            list.add( freeToFreeListResponse( free ) );
        }

        return list;
    }
}
