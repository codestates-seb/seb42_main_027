package ynzmz.server.lecturereview.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.lecturereview.dto.LectureReviewDto;
import ynzmz.server.lecturereview.entity.LectureReview;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LectureReviewMapper {
    LectureReview lectureReviewToLectureReviewPost(LectureReviewDto.Post reviewPostPost);
    LectureReview lectureReviewToLectureReviewPatch(LectureReviewDto.Patch reviewPostPatch);
    LectureReviewDto.InfoResponse lectureReviewInfoResponseToLectureReview(LectureReview lectureReview);
    List<LectureReviewDto.InfoResponse> lectureReviewInfoResponsesToLectureReviews(List<LectureReview> lectureReviews);
}
