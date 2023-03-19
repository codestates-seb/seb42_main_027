package ynzmz.server.comment.qna.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.comment.qna.dto.QnaCommentDto;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.recomment.qna.dto.QnaReCommentDto;
import ynzmz.server.recomment.qna.entity.QnaReComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-19T19:18:30+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QnaCommentMapperImpl implements QnaCommentMapper {

    @Override
    public QnaComment qnaCommentPostToQnaComment(QnaCommentDto.Post lectureReviewPostCommentPostDto) {
        if ( lectureReviewPostCommentPostDto == null ) {
            return null;
        }

        QnaComment qnaComment = new QnaComment();

        qnaComment.setContent( lectureReviewPostCommentPostDto.getContent() );
        qnaComment.setCreatedAt( lectureReviewPostCommentPostDto.getCreatedAt() );

        return qnaComment;
    }

    @Override
    public QnaComment qnaCommentPatchToQnaComment(QnaCommentDto.Patch lectureReviewPostCommentPatchDto) {
        if ( lectureReviewPostCommentPatchDto == null ) {
            return null;
        }

        QnaComment qnaComment = new QnaComment();

        qnaComment.setContent( lectureReviewPostCommentPatchDto.getContent() );
        qnaComment.setModifiedAt( lectureReviewPostCommentPatchDto.getModifiedAt() );

        return qnaComment;
    }

    @Override
    public QnaCommentDto.Response qnaCommentToQnaCommentResponse(QnaComment lectureReviewComment) {
        if ( lectureReviewComment == null ) {
            return null;
        }

        QnaCommentDto.Response response = new QnaCommentDto.Response();

        response.setQnaCommentId( lectureReviewComment.getQnaCommentId() );
        response.setContent( lectureReviewComment.getContent() );
        response.setCreatedAt( lectureReviewComment.getCreatedAt() );
        response.setModifiedAt( lectureReviewComment.getModifiedAt() );
        response.setVoteCount( lectureReviewComment.getVoteCount() );
        response.setMember( memberToSimpleInfoResponse( lectureReviewComment.getMember() ) );
        response.setQnaReComments( qnaReCommentListToResponseList( lectureReviewComment.getQnaReComments() ) );

        return response;
    }

    @Override
    public List<QnaCommentDto.Response> qnaCommentsToQnaCommentResponses(List<QnaComment> lectureReviewComments) {
        if ( lectureReviewComments == null ) {
            return null;
        }

        List<QnaCommentDto.Response> list = new ArrayList<QnaCommentDto.Response>( lectureReviewComments.size() );
        for ( QnaComment qnaComment : lectureReviewComments ) {
            list.add( qnaCommentToQnaCommentResponse( qnaComment ) );
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

    protected QnaReCommentDto.Response qnaReCommentToResponse(QnaReComment qnaReComment) {
        if ( qnaReComment == null ) {
            return null;
        }

        QnaReCommentDto.Response response = new QnaReCommentDto.Response();

        response.setContent( qnaReComment.getContent() );
        response.setCreatedAt( qnaReComment.getCreatedAt() );
        response.setModifiedAt( qnaReComment.getModifiedAt() );
        response.setVoteCount( qnaReComment.getVoteCount() );
        response.setMember( memberToSimpleInfoResponse( qnaReComment.getMember() ) );

        return response;
    }

    protected List<QnaReCommentDto.Response> qnaReCommentListToResponseList(List<QnaReComment> list) {
        if ( list == null ) {
            return null;
        }

        List<QnaReCommentDto.Response> list1 = new ArrayList<QnaReCommentDto.Response>( list.size() );
        for ( QnaReComment qnaReComment : list ) {
            list1.add( qnaReCommentToResponse( qnaReComment ) );
        }

        return list1;
    }
}
