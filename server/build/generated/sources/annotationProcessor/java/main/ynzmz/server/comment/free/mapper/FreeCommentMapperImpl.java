package ynzmz.server.comment.free.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-18T17:36:40+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class FreeCommentMapperImpl implements FreeCommentMapper {

    @Override
    public FreeComment freeCommentPostToFreeComment(FreeCommentDto.Post FreeCommentPostDto) {
        if ( FreeCommentPostDto == null ) {
            return null;
        }

        FreeComment freeComment = new FreeComment();

        freeComment.setContent( FreeCommentPostDto.getContent() );
        freeComment.setCreatedAt( FreeCommentPostDto.getCreatedAt() );

        return freeComment;
    }

    @Override
    public FreeComment freeCommentPatchToFreeComment(FreeCommentDto.Patch FreeCommentPatchDto) {
        if ( FreeCommentPatchDto == null ) {
            return null;
        }

        FreeComment freeComment = new FreeComment();

        freeComment.setContent( FreeCommentPatchDto.getContent() );
        freeComment.setModifiedAt( FreeCommentPatchDto.getModifiedAt() );

        return freeComment;
    }

    @Override
    public FreeCommentDto.Response freeCommentToFreeCommentResponse(FreeComment freeComment) {
        if ( freeComment == null ) {
            return null;
        }

        String content = null;
        String createdAt = null;
        String modifiedAt = null;
        long voteCount = 0L;
        Member member = null;

        content = freeComment.getContent();
        createdAt = freeComment.getCreatedAt();
        modifiedAt = freeComment.getModifiedAt();
        voteCount = freeComment.getVoteCount();
        member = freeComment.getMember();

        Long freeId = null;

        FreeCommentDto.Response response = new FreeCommentDto.Response( freeId, content, createdAt, modifiedAt, voteCount, member );

        return response;
    }

    @Override
    public List<FreeCommentDto.Response> freeCommentToFreeCommentsResponses(List<FreeComment> freeComments) {
        if ( freeComments == null ) {
            return null;
        }

        List<FreeCommentDto.Response> list = new ArrayList<FreeCommentDto.Response>( freeComments.size() );
        for ( FreeComment freeComment : freeComments ) {
            list.add( freeCommentToFreeCommentResponse( freeComment ) );
        }

        return list;
    }
}
