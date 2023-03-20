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
    date = "2023-03-20T15:43:50+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QnaReCommentMapperImpl implements QnaReCommentMapper {

    @Override
    public QnaReComment qnaReCommentPostToQnaReComment(QnaReCommentDto.Post lectureReviewCommentPostDto) {
        if ( lectureReviewCommentPostDto == null ) {
            return null;
        }

        QnaReComment qnaReComment = new QnaReComment();

        qnaReComment.setContent( lectureReviewCommentPostDto.getContent() );
        qnaReComment.setCreatedAt( lectureReviewCommentPostDto.getCreatedAt() );

        return qnaReComment;
    }

    @Override
    public QnaReComment qnaReCommentPatchToQnaReComment(QnaReCommentDto.Patch lectureReviewCommentPatchDto) {
        if ( lectureReviewCommentPatchDto == null ) {
            return null;
        }

        QnaReComment qnaReComment = new QnaReComment();

        qnaReComment.setContent( lectureReviewCommentPatchDto.getContent() );
        qnaReComment.setModifiedAt( lectureReviewCommentPatchDto.getModifiedAt() );

        return qnaReComment;
    }

    @Override
    public QnaReCommentDto.Response qnaReCommentToQnaReCommentResponse(QnaReComment lectureReviewReComment) {
        if ( lectureReviewReComment == null ) {
            return null;
        }

        QnaReCommentDto.Response response = new QnaReCommentDto.Response();

        response.setContent( lectureReviewReComment.getContent() );
        response.setCreatedAt( lectureReviewReComment.getCreatedAt() );
        response.setModifiedAt( lectureReviewReComment.getModifiedAt() );
        response.setVoteCount( lectureReviewReComment.getVoteCount() );
        response.setMember( memberToSimpleInfoResponse( lectureReviewReComment.getMember() ) );

        return response;
    }

    @Override
    public List<QnaReCommentDto.Response> qnaCommentsToQnaCommentResponses(List<QnaReComment> lectureReviewComments) {
        if ( lectureReviewComments == null ) {
            return null;
        }

        List<QnaReCommentDto.Response> list = new ArrayList<QnaReCommentDto.Response>( lectureReviewComments.size() );
        for ( QnaReComment qnaReComment : lectureReviewComments ) {
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
