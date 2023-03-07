package ynzmz.server.teacher.dto;

import lombok.Builder;

import java.util.List;

public class TeacherDto {
    //강사등록
    @Builder
    public static class Post {
        private String name;
        private String introduction;
        private List<String> teacherTypes;
    }
    //강사수정
    //강사전체조회
    //강사디테일조회
}
