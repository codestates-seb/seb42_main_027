package ynzmz.server.lecture.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.tag.entity.Tag;

import java.util.List;

public class LectureDto {
    //postDto
    @Getter
    public static class Post{
        private String name;
        private String introduction;
        private List<String> tags;
        private long teacherId;
    }
    //pathDto
    @Getter
    public static class Patch{
        private String name;
        private String introduction;
        private List<String> tags;
        private long teacherId;
    }
    //강의 전체조회
    //강의 디테일조회
    @Getter @Setter
    public static class InfoResponse{
        private long lectureId;
        private String name;
        private String introduction;
        private long starPointAverage;
        private List<Tag.Type> tags;
        private long teacherId;
    }
}
