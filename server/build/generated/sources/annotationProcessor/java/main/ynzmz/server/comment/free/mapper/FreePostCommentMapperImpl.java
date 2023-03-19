package ynzmz.server.comment.free.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-18T10:44:00+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class FreePostCommentMapperImpl implements FreePostCommentMapper {

    @Override
    public FreeComment FreeCommentToFreeCommentPost(FreeCommentDto.Post FreeCommentPostDto) {
        if ( FreeCommentPostDto == null ) {
            return null;
        }

        FreeComment freeComment = new FreeComment();

        freeComment.setFreeCommentId( FreeCommentPostDto.getFreeCommentId() );
        freeComment.setContent( FreeCommentPostDto.getContent() );
        freeComment.setCreatedAt( FreeCommentPostDto.getCreatedAt() );

        return freeComment;
    }

    @Override
    public FreeComment FreeCommentToFreeCommentPatch(FreeCommentDto.Patch FreeCommentPatchDto) {
        if ( FreeCommentPatchDto == null ) {
            return null;
        }

        FreeComment freeComment = new FreeComment();

        freeComment.setContent( FreeCommentPatchDto.getContent() );
        freeComment.setModifiedAt( FreeCommentPatchDto.getModifiedAt() );

        return freeComment;
    }

    @Override
    public FreeCommentDto.Response FreeCommentResponseToFreeComment(FreeComment freeComment) {
        if ( freeComment == null ) {
            return null;
        }

        FreeCommentDto.Response response = new FreeCommentDto.Response();

        response.setFreeCommentId( freeComment.getFreeCommentId() );
        response.setContent( freeComment.getContent() );
        response.setCreatedAt( freeComment.getCreatedAt() );
        response.setModifiedAt( freeComment.getModifiedAt() );
        response.setVoteCount( freeComment.getVoteCount() );
        response.setMember( freeComment.getMember() );

        return response;
    }

    @Override
    public List<FreeCommentDto.Response> FreeCommentResponsesToFreeComments(List<FreeComment> freeComments) {
        if ( freeComments == null ) {
            return null;
        }

        List<FreeCommentDto.Response> list = new ArrayList<FreeCommentDto.Response>( freeComments.size() );
        for ( FreeComment freeComment : freeComments ) {
            list.add( FreeCommentResponseToFreeComment( freeComment ) );
        }

        return list;
    }
}
