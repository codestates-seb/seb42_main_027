package ynzmz.server.tag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.tag.entity.SubjectTag;

public class SubjectTagDto {
    @Getter @Setter
    public static class Response {
        private SubjectTag.Subject subjectTag;
    }
}
