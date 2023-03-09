package ynzmz.server.lecturereviewpost.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

public class LectureReviewPostDto {
    @Getter
    public static class Post{
        private String title;
        private double starPoint;
        private String content;
        private long memberId;
        private long lectureId;
        private String createdAt;
    }

    @Getter @Setter
    public static class InfoResponse{

        private long reviewPostId;
        private String title;
        private double starPoint;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private Lecture lecture;
        private Member member;
    }
}
