package ynzmz.server.board.free.dto;

import lombok.*;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;

import java.util.List;

public class FreeDto {
@Getter
@Setter
@Builder @AllArgsConstructor @ToString
    public static class post{
        String title;
        String content;//추후 변경 가능
        String category;
        String createdAt;


    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class patch
    {
        private String title;
        private String content;
        private String category;
        private String modifiedAt;
    }

    @Getter
    @Setter
    @Builder @AllArgsConstructor
    public static class ListResponse{
        long freeId;
        String title;
        String content;//추후 변경 가능
        String category;

        long viewCount;
        long voteCount;
        String createdAt;
        String modifiedAt;
        MemberDto.SimpleInfoResponse member;
        int commentsListNum;
//댓글 개수 필요함? 필요함
    }

    @Getter
    @Setter
    @Builder @AllArgsConstructor
    public static class DetailResponse{
        long freeId;
        String title;
        String content;//추후 변경 가능
        String category;
        long viewCount;
        long voteCount;
        String createdAt;
        String modifiedAt;
        MemberDto.SimpleInfoResponse member;
        List<FreeCommentDto.ResponseForFreeDetail> comments;
        int commentsListNum;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class SimpleResponse {
        private long freeId;
        private String title;
    }
}
