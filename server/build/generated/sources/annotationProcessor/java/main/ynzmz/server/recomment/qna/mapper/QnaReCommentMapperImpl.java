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
    date = "2023-03-18T14:09:57+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QnaReCommentMapperImpl implements QnaReCommentMapper {

    @Override
    public QnaReComment qnaReCommentPostToQnaReComment(QnaReCommentDto.Post lectureReviewPostCommentPostDto) {
        if ( lectureReviewPostCommentPostDto == null ) {
            return null;
        }

        QnaReComment qnaReComment = new QnaReComment();

        qnaReComment.setContent( lectureReviewPostCommentPostDto.getContent() );
        qnaReComment.setCreatedAt( lectureReviewPostCommentPostDto.getCreatedAt() );

        return qnaReComment;
    }

    @Override
    public QnaReComment qnaReCommentPatchToQnaReComment(QnaReCommentDto.Patch lectureReviewPostCommentPatchDto) {
        if ( lectureReviewPostCommentPatchDto == null ) {
            return null;
        }

        QnaReComment qnaReComment = new QnaReComment();

        qnaReComment.setContent( lectureReviewPostCommentPatchDto.getContent() );
        qnaReComment.setModifiedAt( lectureReviewPostCommentPatchDto.getModifiedAt() );

        return qnaReComment;
    }

    @Override
    public QnaReCommentDto.Response qnaReCommentToQnaCommentReResponse(QnaReComment lectureReviewComment) {
        if ( lectureReviewComment == null ) {
            return null;
        }

        QnaReCommentDto.Response response = new QnaReCommentDto.Response();

        response.setQnaReComment( lectureReviewComment.getQnaReComment() );
        response.setContent( lectureReviewComment.getContent() );
        response.setCreatedAt( lectureReviewComment.getCreatedAt() );
        response.setModifiedAt( lectureReviewComment.getModifiedAt() );
        response.setVoteCount( lectureReviewComment.getVoteCount() );
        response.setMember( memberToSimpleInfoResponse( lectureReviewComment.getMember() ) );

        return response;
    }

    @Override
    public List<QnaReCommentDto.Response> qnaCommentsToQnaCommentResponses(List<QnaReComment> lectureReviewComments) {
        if ( lectureReviewComments == null ) {
            return null;
        }

        List<QnaReCommentDto.Response> list = new ArrayList<QnaReCommentDto.Response>( lectureReviewComments.size() );
        for ( QnaReComment qnaReComment : lectureReviewComments ) {
            list.add( qnaReCommentToQnaCommentReResponse( qnaReComment ) );
        }

        return list;
    }

    protected MemberDto.SimpleInfoResponse memberToSimpleInfoResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        Long memberId = null;
        String displayName = null;
        String state = null;

        memberId = member.getMemberId();
        displayName = member.getDisplayName();
        if ( member.getState() != null ) {
            state = member.getState().name();
        }

        String iconImageUrl = null;

        MemberDto.SimpleInfoResponse simpleInfoResponse = new MemberDto.SimpleInfoResponse( memberId, displayName, iconImageUrl, state );

        simpleInfoResponse.setIconImageUrl( member.getIconImageUrl() );

        return simpleInfoResponse;
    }
}
