package ynzmz.server.reviewpost.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.reviewpost.dto.ReviewPostDto;
import ynzmz.server.reviewpost.entity.ReviewPost;

@Mapper(componentModel = "spring")
public interface ReviewPostMapper {
    ReviewPost reviewPostToPost(ReviewPostDto.Post reviewPostPostDto);
}
