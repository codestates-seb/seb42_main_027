package ynzmz.server.lecturereviewpost.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.lecturereviewpost.dto.LectureReviewPostDto;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;

@Mapper(componentModel = "spring")
public interface LectureReviewPostMapper {
    LectureReviewPost LectureReviewPostToLectureReviewPostPost(LectureReviewPostDto.Post reviewPostPost);
    LectureReviewPost LectureReviewPostToLectureReviewPostPatch(LectureReviewPostDto.Patch reviewPostPatch);
    LectureReviewPostDto.InfoResponse LectureReviewPostInfoResponseToLectureReviewPost(LectureReviewPost lectureReviewPost);
}
