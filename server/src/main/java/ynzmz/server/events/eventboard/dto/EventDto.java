package ynzmz.server.events.eventboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class EventDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {

        String imageUrl;
        String title;
        String hyperLink;
        String date;
        String source;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {

        String imageUrl;
        String title;
        String hyperLink;
        String date;
        String source;
    }

}
