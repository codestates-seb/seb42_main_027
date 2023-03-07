package ynzmz.server.lecture.dto;

import lombok.Builder;
import ynzmz.server.global.SubjectType;

import java.util.List;

public class LectureDto {
    //postDto
    @Builder
    public static class Post{
        private String name;
        private String introduction;
        private List<SubjectType> subjectTypes;
        private long teacherId;
    }
    //pathDto
    //강사 전체조회
    //강사 디테일조회
}
