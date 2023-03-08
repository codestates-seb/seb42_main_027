package ynzmz.server.lecturereviewpost.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.lecturereviewpost.dto.LectureReviewPostDto;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;

@Mapper(componentModel = "spring")
public interface LectureReviewPostMapper {
    LectureReviewPost reviewPostToPost(LectureReviewPostDto.Post reviewPostPostDto);
}
