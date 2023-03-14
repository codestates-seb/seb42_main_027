package ynzmz.server.tag.dto;

import lombok.Getter;
import lombok.Setter;
import ynzmz.server.tag.entity.SubjectTag;

public class TeacherSubjectTagDto {
    @Getter @Setter
    public static class Response {
        private SubjectTag.Subject subjectTag;
    }
}
