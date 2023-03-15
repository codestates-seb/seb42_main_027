package ynzmz.server.event.inner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class YjEventDto {

    @Getter @Setter
    @AllArgsConstructor
    public static class Post{
        long eventId;

        private String imageUrl;
        private String title;
        private String hyperLink;
        private String date;
    }
    @Getter @Setter
    @AllArgsConstructor
    public static class Patch{
        long eventId;

        private String imageUrl;
        private String title;
        private String hyperLink;
        private String date;
    }

    @Getter @Setter
    @AllArgsConstructor
    public static class Response{
        long eventId;

        private String imageUrl;
        private String title;
        private String hyperLink;
        private String date;
    }
}
