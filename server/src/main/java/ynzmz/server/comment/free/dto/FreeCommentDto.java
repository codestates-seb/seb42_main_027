package ynzmz.server.comment.free.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;

import java.util.List;

public class FreeCommentDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String content;
        private String createdAt;
        private long freeId;
        private long memberId;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        private String content;
        private String category;
        private String modifiedAt;

    }
    @Setter @Getter
    @AllArgsConstructor
    public static class Response{
        private long freeCommentId;
//        private long freeDisplayId;
        private Free free;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long voteCount;
        private Member member;
        private boolean memberSim;
    }
}

