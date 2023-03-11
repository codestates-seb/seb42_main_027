package ynzmz.server.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.tag.entity.Tag;
import ynzmz.server.teacher.dto.TeacherDto;

import java.util.List;

public class LectureDto {
    //postDto
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String name;
        private String introduction;
        private List<String> tags;
        private long teacherId;
    }
    //pathDto
    @Getter
    @AllArgsConstructor
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
        private double starPointAverage;
        private List<Tag.Type> tags;
        private TeacherDto.InfoResponse teacher;
    }
}
