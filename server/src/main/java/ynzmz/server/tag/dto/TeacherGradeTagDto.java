package ynzmz.server.tag.dto;

import lombok.Getter;
import lombok.Setter;
import ynzmz.server.tag.entity.GradeTag;

public class TeacherGradeTagDto {
    @Getter @Setter
    public static class Response {
        private GradeTag.Grade gradeTag;
    }
}
