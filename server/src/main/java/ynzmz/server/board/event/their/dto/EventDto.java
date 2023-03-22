package ynzmz.server.board.event.their.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class EventDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
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
    @Builder
    public static class Response {

        String imageUrl;
        String title;
        String hyperLink;
        String date;
        String source;
    }

}
