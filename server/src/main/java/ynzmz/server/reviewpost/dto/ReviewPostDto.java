package ynzmz.server.reviewpost.dto;

import lombok.Builder;

public class ReviewPostDto {
    @Builder
    public static class Post{
        private String title;
        private int starPoint;
        private String content;
        private long memberId;
        private long teacherId;
    }
}
