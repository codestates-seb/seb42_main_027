package ynzmz.server.board.event.our.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Our {
    @Id
    @AutoConfigureOrder
    @GeneratedValue(strategy = GenerationType.AUTO)
    long eventId;

    private String imageUrl;
    private String title;
    private String content;
    private String date; //나중에 datetime으로 파싱???

    private int viewCount;

}
