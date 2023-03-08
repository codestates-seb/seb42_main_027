package ynzmz.server.teacher.dto;

import lombok.*;
import ynzmz.server.tag.TeacherTag;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TeacherDto {
    //강사등록
    @Getter
    public static class Post {
        private String name;
        private String introduction;
        private List<String> tags;
    }
    //강사수정
    //강사전체조회
    //강사디테일조회

    @Getter @Setter
    public static class infoResponse {
        private Long teacherId;
        private String name;
        private String introduction;
        private List<TeacherTag> tags;
    }
}
