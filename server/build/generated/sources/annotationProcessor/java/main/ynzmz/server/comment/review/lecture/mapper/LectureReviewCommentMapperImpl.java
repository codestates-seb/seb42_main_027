package ynzmz.server.comment.review.lecture.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.review.lecture.dto.LectureReviewDto;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-29T11:11:04+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class LectureReviewCommentMapperImpl implements LectureReviewCommentMapper {

    @Override
    public LectureReviewComment lectureReviewCommentPostToLectureReviewComment(LectureReviewCommentDto.Post lectureReviewCommentPostDto) {
        if ( lectureReviewCommentPostDto == null ) {
            return null;
        }

        LectureReviewComment lectureReviewComment = new LectureReviewComment();

        lectureReviewComment.setContent( lectureReviewCommentPostDto.getContent() );
        lectureReviewComment.setCreatedAt( lectureReviewCommentPostDto.getCreatedAt() );

        return lectureReviewComment;
    }

    @Override
    public LectureReviewComment lectureReviewCommentPatchToLectureReviewComment(LectureReviewCommentDto.Patch lectureReviewCommentPatchDto) {
        if ( lectureReviewCommentPatchDto == null ) {
            return null;
        }

        LectureReviewComment lectureReviewComment = new LectureReviewComment();

        lectureReviewComment.setContent( lectureReviewCommentPatchDto.getContent() );
        lectureReviewComment.setModifiedAt( lectureReviewCommentPatchDto.getModifiedAt() );

        return lectureReviewComment;
    }

    @Override
    public LectureReviewCommentDto.Response lectureReviewCommentToLectureReviewCommentResponse(LectureReviewComment lectureReviewComment) {
        if ( lectureReviewComment == null ) {
            return null;
        }

        LectureReviewCommentDto.Response response = new LectureReviewCommentDto.Response();

        response.setLectureReviewCommentId( lectureReviewComment.getLectureReviewCommentId() );
        response.setContent( lectureReviewComment.getContent() );
        response.setCreatedAt( lectureReviewComment.getCreatedAt() );
        response.setModifiedAt( lectureReviewComment.getModifiedAt() );
        response.setVoteCount( lectureReviewComment.getVoteCount() );
        response.setMember( memberToSimpleInfoResponse( lectureReviewComment.getMember() ) );

        return response;
    }

    @Override
    public List<LectureReviewCommentDto.SimpleResponse> lectureReviewCommentsToLectureReviewCommentSimpleResponses(List<LectureReviewComment> lectureReviewComments) {
        if ( lectureReviewComments == null ) {
            return null;
        }

        List<LectureReviewCommentDto.SimpleResponse> list = new ArrayList<LectureReviewCommentDto.SimpleResponse>( lectureReviewComments.size() );
        for ( LectureReviewComment lectureReviewComment : lectureReviewComments ) {
            list.add( lectureReviewCommentToSimpleResponse( lectureReviewComment ) );
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

    protected LectureReviewDto.SimpleResponse lectureReviewToSimpleResponse(LectureReview lectureReview) {
        if ( lectureReview == null ) {
            return null;
        }

        LectureReviewDto.SimpleResponse simpleResponse = new LectureReviewDto.SimpleResponse();

        simpleResponse.setLectureReviewId( lectureReview.getLectureReviewId() );
        simpleResponse.setTitle( lectureReview.getTitle() );

        return simpleResponse;
    }

    protected LectureReviewCommentDto.SimpleResponse lectureReviewCommentToSimpleResponse(LectureReviewComment lectureReviewComment) {
        if ( lectureReviewComment == null ) {
            return null;
        }

        LectureReviewCommentDto.SimpleResponse simpleResponse = new LectureReviewCommentDto.SimpleResponse();

        simpleResponse.setLectureReviewCommentId( lectureReviewComment.getLectureReviewCommentId() );
        simpleResponse.setContent( lectureReviewComment.getContent() );
        simpleResponse.setCreatedAt( lectureReviewComment.getCreatedAt() );
        simpleResponse.setModifiedAt( lectureReviewComment.getModifiedAt() );
        simpleResponse.setVoteCount( lectureReviewComment.getVoteCount() );
        simpleResponse.setLectureReview( lectureReviewToSimpleResponse( lectureReviewComment.getLectureReview() ) );

        return simpleResponse;
    }
}
