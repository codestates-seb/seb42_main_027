package ynzmz.server.teacher.dto;

import lombok.Builder;
import ynzmz.server.tag.TeacherTag;

import java.util.ArrayList;
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
    @Builder
    public static class infoResponse {
        private Long teacherId;
        private String name;
        private String introduction;
        private List<TeacherTag> tags;
    }
}
