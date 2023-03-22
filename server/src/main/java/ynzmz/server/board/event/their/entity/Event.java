package ynzmz.server.board.event.their.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Event {
    @Id
//    @AutoConfigureOrder
    @GeneratedValue(strategy = GenerationType.AUTO)
    long eventId;

    private String imageUrl;
    private String title;
    private String hyperLink;
    private String date; //나중에 datetime으로 파싱
//    @Column
//    @Enumerated(EnumType.STRING)
//    private Source source;
//
//    public enum Source {
//        메가스터디("메가스터디"),
//        대성마이맥("대성마이맥"),
//        이투스("이투스");
//
//        private String sourceName;
//        Source(String sourceName){
//            this.sourceName = sourceName;
//        }
//        public String getSourceType(){
//            return sourceName;
//        }
//    }
    //enum 사용?
    private String source;



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
