package ynzmz.server.recomment.qna.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.recomment.qna.dto.QnaReCommentDto;
import ynzmz.server.recomment.qna.entity.QnaReComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-20T16:46:50+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QnaReCommentMapperImpl implements QnaReCommentMapper {

    @Override
    public QnaReComment qnaReCommentPostToQnaReComment(QnaReCommentDto.Post qnaReCommentPostDto) {
        if ( qnaReCommentPostDto == null ) {
            return null;
        }

        QnaReComment qnaReComment = new QnaReComment();

        qnaReComment.setContent( qnaReCommentPostDto.getContent() );
        qnaReComment.setCreatedAt( qnaReCommentPostDto.getCreatedAt() );

        return qnaReComment;
    }

    @Override
    public QnaReComment qnaReCommentPatchToQnaReComment(QnaReCommentDto.Patch qnaReCommentPatchDto) {
        if ( qnaReCommentPatchDto == null ) {
            return null;
        }

        QnaReComment qnaReComment = new QnaReComment();

        qnaReComment.setContent( qnaReCommentPatchDto.getContent() );
        qnaReComment.setModifiedAt( qnaReCommentPatchDto.getModifiedAt() );

        return qnaReComment;
    }

    @Override
    public QnaReCommentDto.Response qnaReCommentToQnaReCommentResponse(QnaReComment qnaReComment) {
        if ( qnaReComment == null ) {
            return null;
        }

        QnaReCommentDto.Response response = new QnaReCommentDto.Response();

        response.setQnaReCommentId( qnaReComment.getQnaReCommentId() );
        response.setContent( qnaReComment.getContent() );
        response.setCreatedAt( qnaReComment.getCreatedAt() );
        response.setModifiedAt( qnaReComment.getModifiedAt() );
        response.setVoteCount( qnaReComment.getVoteCount() );
        response.setMember( memberToSimpleInfoResponse( qnaReComment.getMember() ) );

        return response;
    }

    @Override
    public List<QnaReCommentDto.Response> qnaCommentsToQnaCommentResponses(List<QnaReComment> qnaReComments) {
        if ( qnaReComments == null ) {
            return null;
        }

        List<QnaReCommentDto.Response> list = new ArrayList<QnaReCommentDto.Response>( qnaReComments.size() );
        for ( QnaReComment qnaReComment : qnaReComments ) {
            list.add( qnaReCommentToQnaReCommentResponse( qnaReComment ) );
        }

        return list;
    }

    @Override
    public List<QnaReCommentDto.Response> qnaReCommentToQnaReCommentResponses(List<QnaReComment> qnaReComments) {
        if ( qnaReComments == null ) {
            return null;
        }

        List<QnaReCommentDto.Response> list = new ArrayList<QnaReCommentDto.Response>( qnaReComments.size() );
        for ( QnaReComment qnaReComment : qnaReComments ) {
            list.add( qnaReCommentToQnaReCommentResponse( qnaReComment ) );
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
