package ynzmz.server.board.free.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;

import java.util.List;

public class FreeDto {
@Getter
@Setter
@Builder @AllArgsConstructor
    public static class post{
        long Id;
        String title;
        String content;//추후 변경 가능
        long viewCount;
        long voteCount;
        String createdAt;
        String modifiedAt;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class patch
    {
        private long Id;
        private String title;
        private String content;//추후 변경 가능
    }

    @Getter
    @Setter
    @Builder @AllArgsConstructor
    public static class ListResponse{
        long Id;
        String title;
        String content;//추후 변경 가능
        long viewCount;
        long voteCount;
        String createdAt;
        String modifiedAt;
        Member member;
        List<FreeCommentDto.Response> commentsList;
//댓글 개수 필요함? 필요함
    }

    @Getter
    @Setter
    @Builder @AllArgsConstructor
    public static class DetailResponse{
        long Id;
        String title;
        String content;//추후 변경 가능
        long viewCount;
        long voteCount;
        String createdAt;
        String modifiedAt;
        Member member;
        List<FreeCommentDto.Response> commentsList;
    }
}
