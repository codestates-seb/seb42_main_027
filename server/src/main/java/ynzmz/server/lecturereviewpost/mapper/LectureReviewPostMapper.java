package ynzmz.server.lecturereviewpost.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.lecturereviewpost.dto.LectureReviewPostDto;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LectureReviewPostMapper {
    LectureReviewPost lectureReviewPostToLectureReviewPostPost(LectureReviewPostDto.Post reviewPostPost);
    LectureReviewPost lectureReviewPostToLectureReviewPostPatch(LectureReviewPostDto.Patch reviewPostPatch);
    LectureReviewPostDto.InfoResponse lectureReviewPostInfoResponseToLectureReviewPost(LectureReviewPost lectureReviewPost);

    List<LectureReviewPostDto.InfoResponse> lectureReviewPostInfoResponsesToLectureReviewPosts(List<LectureReviewPost> lectureReviewPosts);
}
