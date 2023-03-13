package ynzmz.server.eventBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class EventDto {
    @Getter
    @AllArgsConstructor
    public static class Post {

        String imageUrl;
        String title;
        String hyperLink;
        String date; //나중에 datetime으로 파싱
        String source; //enum 사용?
    }

}
