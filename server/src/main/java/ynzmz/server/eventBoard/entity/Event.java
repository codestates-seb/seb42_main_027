package ynzmz.server.eventBoard.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter
@Setter
public class Event {
@Id @AutoConfigureOrder
    long eventId;

    String imageUrl;
    String title;
    String hyperLink;
    String date; //나중에 datetime으로 파싱
    String source; //enum 사용?




    /*
    이벤트 아이디
    --------------------------------------
이미지
제목
하이퍼링크
날짜
출처(메가/이투스/..../오리지널)
일단 페이지네이션 --> 인피니티 스크롤
     */






}
