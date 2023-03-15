package ynzmz.server.review.lecture.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.review.lecture.dto.LectureReviewDto;
import ynzmz.server.review.lecture.entity.LectureReview;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LectureReviewMapper {
    LectureReview lectureReviewToLectureReviewPost(LectureReviewDto.Post reviewPostPost);
    LectureReview lectureReviewToLectureReviewPatch(LectureReviewDto.Patch reviewPostPatch);
    LectureReviewDto.InfoResponse lectureReviewInfoResponseToLectureReview(LectureReview lectureReview);
    List<LectureReviewDto.InfoResponse> lectureReviewInfoResponsesToLectureReviews(List<LectureReview> lectureReviews);
}
