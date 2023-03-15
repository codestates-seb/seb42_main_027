package ynzmz.server.review.lecture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.review.lecture.dto.LectureReviewDto;
import ynzmz.server.review.lecture.entity.LectureReview;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LectureReviewMapper {
    LectureReview lectureReviewToLectureReviewPost(LectureReviewDto.Post reviewPostPost);
    LectureReview lectureReviewToLectureReviewPatch(LectureReviewDto.Patch reviewPostPatch);
    LectureReviewDto.ListPageResponse lectureReviewListPageResponseToLectureReview(LectureReview lectureReview);
    List<LectureReviewDto.ListPageResponse> lectureReviewListPageResponsesToLectureReviews(List<LectureReview> lectureReviews);
    LectureReviewDto.DetailPageResponse lectureReviewDetailPageResponseToLectureReview(LectureReview lectureReview);
    List<LectureReviewDto.DetailPageResponse> lectureReviewDetailPageResponsesToLectureReviews(List<LectureReview> lectureReviews);
    LectureReviewDto.InfoResponse lectureReviewInfoResponseToLectureReview(LectureReview lectureReview);
    List<LectureReviewDto.InfoResponse> lectureReviewInfoResponsesToLectureReviews(List<LectureReview> lectureReviews);
}
