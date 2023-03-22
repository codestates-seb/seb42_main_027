package ynzmz.server.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.tag.entity.GradeTag;

public class GradeTagDto {
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private GradeTag.Grade gradeTag;
    }
}
