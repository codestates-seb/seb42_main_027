package ynzmz.server.reviewpost.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.reviewpost.dto.ReviewPostDto;
import ynzmz.server.reviewpost.entity.ReviewPost;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-09T22:55:16+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class ReviewPostMapperImpl implements ReviewPostMapper {

    @Override
    public ReviewPost reviewPostToPost(ReviewPostDto.Post reviewPostPostDto) {
        if ( reviewPostPostDto == null ) {
            return null;
        }

        ReviewPost reviewPost = new ReviewPost();

        return reviewPost;
    }
}