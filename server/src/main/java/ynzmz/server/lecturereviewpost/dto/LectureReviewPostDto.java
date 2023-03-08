package ynzmz.server.lecturereviewpost.dto;

import lombok.Builder;

public class LectureReviewPostDto {
    @Builder
    public static class Post{
        private String title;
        private int starPoint;
        private String content;
        private long memberId;
        private long teacherId;
    }
}
