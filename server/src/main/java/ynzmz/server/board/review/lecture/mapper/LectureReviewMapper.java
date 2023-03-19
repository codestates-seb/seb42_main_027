package ynzmz.server.board.review.lecture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.board.review.lecture.dto.LectureReviewDto;
import ynzmz.server.board.review.lecture.entity.LectureReview;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LectureReviewMapper {
    LectureReview lectureReviewPostToLectureReview(LectureReviewDto.Post reviewPostPost);
    LectureReview lectureReviewPatchToLectureReview(LectureReviewDto.Patch reviewPostPatch);
    LectureReviewDto.ListPageResponse lectureReviewToLectureReviewListPageResponse(LectureReview lectureReview);
    List<LectureReviewDto.ListPageResponse> lectureReviewsToLectureReviewListPageResponses(List<LectureReview> lectureReviews);
    LectureReviewDto.DetailPageResponse lectureReviewToLectureReviewDetailPageResponse(LectureReview lectureReview);
    LectureReviewDto.InfoResponse lectureReviewToLectureReviewInfoResponse(LectureReview lectureReview);
    List<LectureReviewDto.InfoResponse> lectureReviewToLectureReviewInfoResponses(List<LectureReview> lectureReviews);
}
