package ynzmz.server.recomment.free.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.recomment.free.dto.ReCommentDto;
import ynzmz.server.recomment.free.entity.FreeReComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-29T00:12:00+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class ReCommentMapperImpl implements ReCommentMapper {

    @Override
    public FreeReComment recommentPostToRecomment(ReCommentDto.Post RecommentPostDto) {
        if ( RecommentPostDto == null ) {
            return null;
        }

        FreeReComment freeReComment = new FreeReComment();

        freeReComment.setContent( RecommentPostDto.getContent() );
        freeReComment.setCreatedAt( RecommentPostDto.getCreatedAt() );

        return freeReComment;
    }

    @Override
    public FreeReComment recommentPatchToRecomment(ReCommentDto.Patch RecommentPatchDto) {
        if ( RecommentPatchDto == null ) {
            return null;
        }

        FreeReComment freeReComment = new FreeReComment();

        freeReComment.setContent( RecommentPatchDto.getContent() );
        freeReComment.setModifiedAt( RecommentPatchDto.getModifiedAt() );

        return freeReComment;
    }

    @Override
    public ReCommentDto.Response recommentToRecommentResponse(FreeReComment recomment) {
        if ( recomment == null ) {
            return null;
        }

        long freeReCommentId = 0L;
        String content = null;
        String createdAt = null;
        String modifiedAt = null;
        long voteCount = 0L;
        MemberDto.SimpleInfoResponse member = null;
        boolean memberSim = false;

        if ( recomment.getFreeReCommentId() != null ) {
            freeReCommentId = recomment.getFreeReCommentId();
        }
        content = recomment.getContent();
        createdAt = recomment.getCreatedAt();
        modifiedAt = recomment.getModifiedAt();
        voteCount = recomment.getVoteCount();
        member = memberToSimpleInfoResponse( recomment.getMember() );
        memberSim = recomment.isMemberSim();

        ReCommentDto.Response response = new ReCommentDto.Response( freeReCommentId, content, createdAt, modifiedAt, voteCount, member, memberSim );

        return response;
    }

    @Override
    public List<ReCommentDto.SimpleResponse> freeReCommentToFreeReCommentsSimpleResponses(List<FreeReComment> ReComments) {
        if ( ReComments == null ) {
            return null;
        }

        List<ReCommentDto.SimpleResponse> list = new ArrayList<ReCommentDto.SimpleResponse>( ReComments.size() );
        for ( FreeReComment freeReComment : ReComments ) {
            list.add( freeReCommentToSimpleResponse( freeReComment ) );
        }

        return list;
    }

    protected MemberDto.SimpleInfoResponse memberToSimpleInfoResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        Long memberId = null;
        String displayName = null;
        String iconImageUrl = null;
        String state = null;

        memberId = member.getMemberId();
        displayName = member.getDisplayName();
        iconImageUrl = member.getIconImageUrl();
        if ( member.getState() != null ) {
            state = member.getState().name();
        }

        MemberDto.SimpleInfoResponse simpleInfoResponse = new MemberDto.SimpleInfoResponse( memberId, displayName, iconImageUrl, state );

        return simpleInfoResponse;
    }

    protected FreeDto.SimpleResponse freeToSimpleResponse(Free free) {
        if ( free == null ) {
            return null;
        }

        long freeId = 0L;
        String title = null;

        freeId = free.getFreeId();
        title = free.getTitle();

        FreeDto.SimpleResponse simpleResponse = new FreeDto.SimpleResponse( freeId, title );

        return simpleResponse;
    }

    protected FreeCommentDto.SimpleResponse freeCommentToSimpleResponse(FreeComment freeComment) {
        if ( freeComment == null ) {
            return null;
        }

        FreeCommentDto.SimpleResponse simpleResponse = new FreeCommentDto.SimpleResponse();

        if ( freeComment.getFreeCommentId() != null ) {
            simpleResponse.setFreeCommentId( freeComment.getFreeCommentId() );
        }
        simpleResponse.setContent( freeComment.getContent() );
        simpleResponse.setCreatedAt( freeComment.getCreatedAt() );
        simpleResponse.setModifiedAt( freeComment.getModifiedAt() );
        simpleResponse.setVoteCount( freeComment.getVoteCount() );
        simpleResponse.setFree( freeToSimpleResponse( freeComment.getFree() ) );

        return simpleResponse;
    }

    protected ReCommentDto.SimpleResponse freeReCommentToSimpleResponse(FreeReComment freeReComment) {
        if ( freeReComment == null ) {
            return null;
        }

        ReCommentDto.SimpleResponse simpleResponse = new ReCommentDto.SimpleResponse();

        if ( freeReComment.getFreeReCommentId() != null ) {
            simpleResponse.setFreeReCommentId( freeReComment.getFreeReCommentId() );
        }
        simpleResponse.setContent( freeReComment.getContent() );
        simpleResponse.setCreatedAt( freeReComment.getCreatedAt() );
        simpleResponse.setModifiedAt( freeReComment.getModifiedAt() );
        simpleResponse.setVoteCount( freeReComment.getVoteCount() );
        simpleResponse.setFreeComment( freeCommentToSimpleResponse( freeReComment.getFreeComment() ) );

        return simpleResponse;
    }
}
