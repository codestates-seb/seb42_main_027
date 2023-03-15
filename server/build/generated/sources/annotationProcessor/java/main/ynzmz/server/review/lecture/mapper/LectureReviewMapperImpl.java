package ynzmz.server.review.lecture.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.review.lecture.dto.LectureReviewDto;
import ynzmz.server.review.lecture.entity.LectureReview;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-15T16:45:56+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class LectureReviewMapperImpl implements LectureReviewMapper {

    @Override
    public LectureReview lectureReviewToLectureReviewPost(LectureReviewDto.Post reviewPostPost) {
        if ( reviewPostPost == null ) {
            return null;
        }

        LectureReview lectureReview = new LectureReview();

        lectureReview.setTitle( reviewPostPost.getTitle() );
        lectureReview.setStarPoint( reviewPostPost.getStarPoint() );
        lectureReview.setContent( reviewPostPost.getContent() );
        lectureReview.setCreatedAt( reviewPostPost.getCreatedAt() );

        return lectureReview;
    }

    @Override
    public LectureReview lectureReviewToLectureReviewPatch(LectureReviewDto.Patch reviewPostPatch) {
        if ( reviewPostPatch == null ) {
            return null;
        }

        LectureReview lectureReview = new LectureReview();

        lectureReview.setTitle( reviewPostPatch.getTitle() );
        lectureReview.setStarPoint( reviewPostPatch.getStarPoint() );
        lectureReview.setContent( reviewPostPatch.getContent() );
        lectureReview.setModifiedAt( reviewPostPatch.getModifiedAt() );

        return lectureReview;
    }

    @Override
    public LectureReviewDto.InfoResponse lectureReviewInfoResponseToLectureReview(LectureReview lectureReview) {
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

    @Override
    public List<LectureReviewDto.InfoResponse> lectureReviewInfoResponsesToLectureReviews(List<LectureReview> lectureReviews) {
        if ( lectureReviews == null ) {
            return null;
        }

        List<LectureReviewDto.InfoResponse> list = new ArrayList<LectureReviewDto.InfoResponse>( lectureReviews.size() );
        for ( LectureReview lectureReview : lectureReviews ) {
            list.add( lectureReviewInfoResponseToLectureReview( lectureReview ) );
        }

        return list;
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
}
