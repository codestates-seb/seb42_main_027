package ynzmz.server.recomment.free.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.recomment.free.dto.ReCommentDto;
import ynzmz.server.recomment.free.entity.FreeReComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T03:22:24+0900",
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
    public List<ReCommentDto.Response> freeCommentToFreeCommentsResponses(List<FreeReComment> freeReComments) {
        if ( freeReComments == null ) {
            return null;
        }

        List<ReCommentDto.Response> list = new ArrayList<ReCommentDto.Response>( freeReComments.size() );
        for ( FreeReComment freeReComment : freeReComments ) {
            list.add( recommentToRecommentResponse( freeReComment ) );
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
}
