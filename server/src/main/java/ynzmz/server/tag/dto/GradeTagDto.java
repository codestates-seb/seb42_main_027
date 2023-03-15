package ynzmz.server.tag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.tag.entity.GradeTag;

public class GradeTagDto {
    @Getter @Setter
    public static class Response {
        private GradeTag.Grade gradeTag;
    }
}
