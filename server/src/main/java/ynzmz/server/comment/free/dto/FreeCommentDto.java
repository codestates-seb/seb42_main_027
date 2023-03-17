package ynzmz.server.comment.free.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;

import java.util.List;

public class FreeCommentDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String content;
        private String createdAt;
        private long freeCommentId;
        private long memberId;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private String content;
        private String modifiedAt;
    }
    @Setter @Getter
    @NoArgsConstructor
    public static class Response{

        private Long freeCommentId;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private Member member;
    }
}

