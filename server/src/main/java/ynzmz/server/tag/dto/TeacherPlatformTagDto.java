package ynzmz.server.tag.dto;

import lombok.Getter;
import lombok.Setter;
import ynzmz.server.tag.entity.PlatformTag;

public class TeacherPlatformTagDto {
    @Getter @Setter
    public static class Response {
        private PlatformTag.Platform platformTag;
    }
}
