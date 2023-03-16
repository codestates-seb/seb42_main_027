package ynzmz.server.free.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.VoteCount;

import javax.persistence.*;
import javax.swing.text.AbstractDocument;

@Entity
@Getter
@Setter
public class Free implements VoteCount {

    // id: 1,
    //    category: '공지',

    //    title: '게시물 제목',
    //    content: '게시물 내용',
    //    view: '3.6k',
    //    vote: '58',
    //    createdAt: '1시간 전',//스트링 저장하고 보낼때도 스트링으로....
    //    updatedAt: '게시글수정시간',
    //    member : {
    //           memberId: "회원식별번호",
    //           username: "회원번호"
    //           iconImageUrl: "이미지 링크"
    //}
    ////리스트에는 댓글 숫자만
    //게시물 디테일에는 댓글 객체가
    //댓글 대댓글 본문 전부 추천 이용 가능
    //15개씩 페이지네이션

    @Id
    @GeneratedValue
    private long Id;

    private String title;
    private String content;//추후 변경 가능
    private long viewCount;
    private long voteCount;
    private String createdAt;
    private String modifiedAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "member_id")
    private Member member;

//    private



}
