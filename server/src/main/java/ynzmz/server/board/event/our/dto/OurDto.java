package ynzmz.server.board.event.our.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class OurDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post{
        private String title;
        private String content;
        private String date;
        private String imageUrl;
        private int viewCount;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch{

        private String imageUrl;
        private String title;
        private String content;
        private String date;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{
        private long eventId;
        private String title;
        private String content;
        private String date;
        private int viewCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ListResponse{
        private long eventId;
        private String imageUrl;
        private String title;
        private String date;
        private int viewCount;
    }
}
