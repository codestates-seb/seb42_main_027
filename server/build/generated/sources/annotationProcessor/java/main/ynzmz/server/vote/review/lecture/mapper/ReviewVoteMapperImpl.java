package ynzmz.server.vote.review.lecture.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.review.lecture.dto.ReviewVoteDto;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-29T00:12:00+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class ReviewVoteMapperImpl implements ReviewVoteMapper {

    @Override
    public ReviewVoteDto.ReviewResponse lectureReviewVoteToReviewVoteReviewResponse(ReviewVote reviewVote) {
        if ( reviewVote == null ) {
            return null;
        }

        ReviewVoteDto.ReviewResponse reviewResponse = new ReviewVoteDto.ReviewResponse();

        reviewResponse.setStatus( reviewVote.getStatus() );
        reviewResponse.setTarget( reviewVote.getTarget() );
        reviewResponse.setMember( memberToSimpleInfoResponse( reviewVote.getMember() ) );

        return reviewResponse;
    }

    @Override
    public ReviewVoteDto.CommentResponse lectureReviewVoteToReviewVoteCommentResponse(ReviewVote reviewVote) {
        if ( reviewVote == null ) {
            return null;
        }

        ReviewVoteDto.CommentResponse commentResponse = new ReviewVoteDto.CommentResponse();

        commentResponse.setStatus( reviewVote.getStatus() );
        commentResponse.setTarget( reviewVote.getTarget() );
        commentResponse.setMember( memberToSimpleInfoResponse( reviewVote.getMember() ) );

        return commentResponse;
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
