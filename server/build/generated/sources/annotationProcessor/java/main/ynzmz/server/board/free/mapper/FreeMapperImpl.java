package ynzmz.server.board.free.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.free.entity.Free;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-18T03:05:10+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class FreeMapperImpl implements FreeMapper {

    @Override
    public Free freePostToFree(FreeDto.post p) {
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

    @Override
    public Free freePatchToFree(FreeDto.patch p) {
        if ( p == null ) {
            return null;
        }

        Free free = new Free();

        free.setId( p.getId() );
        free.setTitle( p.getTitle() );
        free.setContent( p.getContent() );

        return free;
    }

    @Override
    public FreeDto.DetailResponse freeToFreeDetailResponse(Free free) {
        if ( free == null ) {
            return null;
        }

        FreeDto.DetailResponse.DetailResponseBuilder detailResponse = FreeDto.DetailResponse.builder();

        detailResponse.title( free.getTitle() );
        detailResponse.content( free.getContent() );
        detailResponse.viewCount( free.getViewCount() );
        detailResponse.voteCount( free.getVoteCount() );
        detailResponse.createdAt( free.getCreatedAt() );
        detailResponse.modifiedAt( free.getModifiedAt() );
        detailResponse.member( free.getMember() );

        return detailResponse.build();
    }

    @Override
    public FreeDto.ListResponse freeToFreeListResponse(Free free) {
        if ( free == null ) {
            return null;
        }

        FreeDto.ListResponse.ListResponseBuilder listResponse = FreeDto.ListResponse.builder();

        listResponse.title( free.getTitle() );
        listResponse.content( free.getContent() );
        listResponse.viewCount( free.getViewCount() );
        listResponse.voteCount( free.getVoteCount() );
        listResponse.createdAt( free.getCreatedAt() );
        listResponse.modifiedAt( free.getModifiedAt() );
        listResponse.member( free.getMember() );

        return listResponse.build();
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
