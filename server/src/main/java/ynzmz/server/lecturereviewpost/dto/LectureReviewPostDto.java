package ynzmz.server.lecturereviewpost.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.member.entity.Member;

public class LectureReviewPostDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String title;
        private double starPoint;
        private String content;
        private long memberId;
        private long lectureId;
        private String createdAt;
    }
    @Getter
    @AllArgsConstructor
    public static class Patch{
        private String title;
        private double starPoint;
        private String content;
        private String modifiedAt;
    }

    @Getter @Setter
    public static class InfoResponse{

        private long lectureReviewPostId;
        private String title;
        private double starPoint;
        private String content;
        private String createdAt;
        private String modifiedAt;
        private long viewCount;
        private long voteCount;
        private LectureDto.InfoResponse lecture;
        private Member member;
    }
}
