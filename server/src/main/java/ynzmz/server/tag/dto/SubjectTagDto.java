package ynzmz.server.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.tag.entity.SubjectTag;

public class SubjectTagDto {
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private SubjectTag.Subject subjectTag;
    }
}
