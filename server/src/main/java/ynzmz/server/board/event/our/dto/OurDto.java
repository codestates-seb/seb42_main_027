package ynzmz.server.board.event.our.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class OurDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post{
        private long eventId;

        private String imageUrl;
        private String title;
        private String content;
        private String date;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch{
        private long eventId;

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

        private String imageUrl;
        private String title;
        private String content;
        private String date;
    }
}
