package ynzmz.server.comment.free.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;

import java.util.List;

public class FreeCommentDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String content;
        private String createdAt;
        private String freeEmoticonUrl;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private String title;
        private String content;
        private String category;
        private String modifiedAt;
        private String freeEmoticonUrl;

    }
    @Setter @Getter
    @AllArgsConstructor
    public static class Response{
        private long freeCommentId;
//        private long freeDisplayId;
//        private Free free;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private String freeEmoticonUrl;
        private long voteCount;
        private MemberDto.SimpleInfoResponse member;
        private boolean memberSim;
    }



    @Setter @Getter
    @AllArgsConstructor
    public static class ResponseForFreeDetail{
        private long freeCommentId;
        //        private long freeDisplayId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private String freeEmoticonUrl;
        private long voteCount;
        private MemberDto.SimpleInfoResponse member;
        private boolean memberSim;
    }
    @Getter @Setter
    @NoArgsConstructor
    public static class SimpleResponse{
        private long freeCommentId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private FreeDto.SimpleResponse free;

    }

    @Getter @Setter
    @AllArgsConstructor
    public static class SimpleInfoResponse {
        private long freeCommentId;
        private String content;
    }


}

