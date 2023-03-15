package ynzmz.server.comment.review.lecture.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T02:00:37+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class LectureReviewPostCommentMapperImpl implements LectureReviewPostCommentMapper {

    @Override
    public LectureReviewComment lectureReviewCommentToLectureReviewCommentPost(LectureReviewCommentDto.Post lectureReviewPostCommentPostDto) {
        if ( lectureReviewPostCommentPostDto == null ) {
            return null;
        }

        LectureReviewComment lectureReviewComment = new LectureReviewComment();

        lectureReviewComment.setContent( lectureReviewPostCommentPostDto.getContent() );
        lectureReviewComment.setCreatedAt( lectureReviewPostCommentPostDto.getCreatedAt() );

        return lectureReviewComment;
    }

    @Override
    public LectureReviewComment lectureReviewCommentToLectureReviewCommentPatch(LectureReviewCommentDto.Patch lectureReviewPostCommentPatchDto) {
        if ( lectureReviewPostCommentPatchDto == null ) {
            return null;
        }

        LectureReviewComment lectureReviewComment = new LectureReviewComment();

        lectureReviewComment.setContent( lectureReviewPostCommentPatchDto.getContent() );
        lectureReviewComment.setModifiedAt( lectureReviewPostCommentPatchDto.getModifiedAt() );

        return lectureReviewComment;
    }

    @Override
    public LectureReviewCommentDto.Response lectureReviewCommentResponseToLectureReviewComment(LectureReviewComment lectureReviewComment) {
        if ( lectureReviewComment == null ) {
            return null;
        }

        LectureReviewCommentDto.Response response = new LectureReviewCommentDto.Response();

        response.setLectureReviewCommentId( lectureReviewComment.getLectureReviewCommentId() );
        response.setContent( lectureReviewComment.getContent() );
        response.setCreatedAt( lectureReviewComment.getCreatedAt() );
        response.setModifiedAt( lectureReviewComment.getModifiedAt() );
        response.setVoteCount( lectureReviewComment.getVoteCount() );
        response.setMember( lectureReviewComment.getMember() );

        return response;
    }

    @Override
    public List<LectureReviewCommentDto.Response> lectureReviewCommentResponsesToLectureReviewComments(List<LectureReviewComment> lectureReviewComments) {
        if ( lectureReviewComments == null ) {
            return null;
        }

        List<LectureReviewCommentDto.Response> list = new ArrayList<LectureReviewCommentDto.Response>( lectureReviewComments.size() );
        for ( LectureReviewComment lectureReviewComment : lectureReviewComments ) {
            list.add( lectureReviewCommentResponseToLectureReviewComment( lectureReviewComment ) );
        }

        return list;
    }
}
