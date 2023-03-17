package ynzmz.server.review.lecture.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.review.lecture.dto.LectureReviewDto;
import ynzmz.server.review.lecture.entity.LectureReview;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T14:08:15+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class LectureReviewMapperImpl implements LectureReviewMapper {

    @Override
    public LectureReview lectureReviewPostToLectureReview(LectureReviewDto.Post reviewPostPost) {
        if ( reviewPostPost == null ) {
            return null;
        }

        LectureReview lectureReview = new LectureReview();

        lectureReview.setTitle( reviewPostPost.getTitle() );
        lectureReview.setStarPoint( (int) reviewPostPost.getStarPoint() );
        lectureReview.setContent( reviewPostPost.getContent() );
        lectureReview.setCreatedAt( reviewPostPost.getCreatedAt() );

        return lectureReview;
    }

    @Override
    public LectureReview lectureReviewPatchToLectureReview(LectureReviewDto.Patch reviewPostPatch) {
        if ( reviewPostPatch == null ) {
            return null;
        }

        LectureReview lectureReview = new LectureReview();

        lectureReview.setTitle( reviewPostPatch.getTitle() );
        lectureReview.setStarPoint( (int) reviewPostPatch.getStarPoint() );
        lectureReview.setContent( reviewPostPatch.getContent() );
        lectureReview.setModifiedAt( reviewPostPatch.getModifiedAt() );

        return lectureReview;
    }

    @Override
    public LectureReviewDto.ListPageResponse lectureReviewToLectureReviewListPageResponse(LectureReview lectureReview) {
        if ( lectureReview == null ) {
            return null;
        }

        LectureReviewDto.ListPageResponse listPageResponse = new LectureReviewDto.ListPageResponse();

        if ( lectureReview.getLectureReviewId() != null ) {
            listPageResponse.setLectureReviewId( lectureReview.getLectureReviewId() );
        }
        listPageResponse.setTitle( lectureReview.getTitle() );
        listPageResponse.setStarPoint( lectureReview.getStarPoint() );
        listPageResponse.setContent( lectureReview.getContent() );
        listPageResponse.setCreatedAt( lectureReview.getCreatedAt() );
        listPageResponse.setModifiedAt( lectureReview.getModifiedAt() );
        listPageResponse.setViewCount( lectureReview.getViewCount() );
        listPageResponse.setVoteCount( lectureReview.getVoteCount() );
        listPageResponse.setLecture( lectureToSimpleInfoResponse( lectureReview.getLecture() ) );
        listPageResponse.setMember( lectureReview.getMember() );

        return listPageResponse;
    }

    @Override
    public List<LectureReviewDto.ListPageResponse> lectureReviewsToLectureReviewListPageResponses(List<LectureReview> lectureReviews) {
        if ( lectureReviews == null ) {
            return null;
        }

        List<LectureReviewDto.ListPageResponse> list = new ArrayList<LectureReviewDto.ListPageResponse>( lectureReviews.size() );
        for ( LectureReview lectureReview : lectureReviews ) {
            list.add( lectureReviewToLectureReviewListPageResponse( lectureReview ) );
        }

        return list;
    }

    @Override
    public LectureReviewDto.DetailPageResponse lectureReviewToLectureReviewDetailPageResponse(LectureReview lectureReview) {
        if ( lectureReview == null ) {
            return null;
        }

        LectureReviewDto.DetailPageResponse detailPageResponse = new LectureReviewDto.DetailPageResponse();

        if ( lectureReview.getLectureReviewId() != null ) {
            detailPageResponse.setLectureReviewId( lectureReview.getLectureReviewId() );
        }
        detailPageResponse.setTitle( lectureReview.getTitle() );
        detailPageResponse.setStarPoint( lectureReview.getStarPoint() );
        detailPageResponse.setContent( lectureReview.getContent() );
        detailPageResponse.setCreatedAt( lectureReview.getCreatedAt() );
        detailPageResponse.setModifiedAt( lectureReview.getModifiedAt() );
        detailPageResponse.setViewCount( lectureReview.getViewCount() );
        detailPageResponse.setVoteCount( lectureReview.getVoteCount() );
        detailPageResponse.setLecture( lectureToSimpleInfoResponse( lectureReview.getLecture() ) );
        detailPageResponse.setMember( lectureReview.getMember() );
        detailPageResponse.setComments( lectureReviewCommentListToResponseList( lectureReview.getComments() ) );

        return detailPageResponse;
    }

    @Override
    public LectureReviewDto.InfoResponse lectureReviewToLectureReviewInfoResponse(LectureReview lectureReview) {
        if ( lectureReview == null ) {
            return null;
        }

        LectureReviewDto.InfoResponse infoResponse = new LectureReviewDto.InfoResponse();

        if ( lectureReview.getLectureReviewId() != null ) {
            infoResponse.setLectureReviewId( lectureReview.getLectureReviewId() );
        }
        infoResponse.setTitle( lectureReview.getTitle() );
        infoResponse.setStarPoint( lectureReview.getStarPoint() );
        infoResponse.setContent( lectureReview.getContent() );
        infoResponse.setCreatedAt( lectureReview.getCreatedAt() );
        infoResponse.setModifiedAt( lectureReview.getModifiedAt() );
        infoResponse.setViewCount( lectureReview.getViewCount() );
        infoResponse.setVoteCount( lectureReview.getVoteCount() );
        infoResponse.setLecture( lectureToSimpleInfoResponse( lectureReview.getLecture() ) );
        infoResponse.setMember( lectureReview.getMember() );

        return infoResponse;
    }

    protected LectureDto.SimpleInfoResponse lectureToSimpleInfoResponse(Lecture lecture) {
        if ( lecture == null ) {
            return null;
        }

        LectureDto.SimpleInfoResponse simpleInfoResponse = new LectureDto.SimpleInfoResponse();

        if ( lecture.getLectureId() != null ) {
            simpleInfoResponse.setLectureId( lecture.getLectureId() );
        }
        simpleInfoResponse.setTitle( lecture.getTitle() );
        simpleInfoResponse.setStarPointAverage( lecture.getStarPointAverage() );

        return simpleInfoResponse;
    }

    protected LectureReviewCommentDto.Response lectureReviewCommentToResponse(LectureReviewComment lectureReviewComment) {
        if ( lectureReviewComment == null ) {
            return null;
        }

        LectureReviewCommentDto.Response response = new LectureReviewCommentDto.Response();

        response.setLectureReviewCommentId( lectureReviewComment.getLectureReviewCommentId() );
        response.setContent( lectureReviewComment.getContent() );
        response.setCreatedAt( lectureReviewComment.getCreatedAt() );
        response.setModifiedAt( lectureReviewComment.getModifiedAt() );
        response.setVoteCount( lectureReviewComment.getVoteCount() );
        response.setMember( lectureReviewComment.getMember() );

        return response;
    }

    protected List<LectureReviewCommentDto.Response> lectureReviewCommentListToResponseList(List<LectureReviewComment> list) {
        if ( list == null ) {
            return null;
        }

        List<LectureReviewCommentDto.Response> list1 = new ArrayList<LectureReviewCommentDto.Response>( list.size() );
        for ( LectureReviewComment lectureReviewComment : list ) {
            list1.add( lectureReviewCommentToResponse( lectureReviewComment ) );
        }

        return list1;
    }
}
