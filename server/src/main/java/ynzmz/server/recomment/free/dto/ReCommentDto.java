package ynzmz.server.recomment.free.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.member.dto.MemberDto;

public class ReCommentDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String content;
        private String createdAt;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private String title;
        private String content;
        private String category;
        private String modifiedAt;

    }
    @Setter @Getter
    @AllArgsConstructor
    public static class Response{
        private long freeReCommentId;
//        private long freeDisplayId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private MemberDto.SimpleInfoResponse member;
        private boolean memberSim;
    }
    @Setter @Getter
    @AllArgsConstructor
    public static class ResponseForFreeDetail{
        private long freeReCommentId;
        //        private long freeDisplayId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private MemberDto.SimpleInfoResponse member;
        private boolean memberSim;
    }

    @Setter @Getter
    @NoArgsConstructor
    public static class SimpleResponse {
        private long freeReCommentId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private FreeCommentDto.SimpleResponse freeComment;
    }
}

