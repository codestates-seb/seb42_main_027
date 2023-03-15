package ynzmz.server.tag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.tag.entity.PlatformTag;

public class PlatformTagDto {
    @Getter @Setter
    public static class Response {
        private PlatformTag.Platform platformTag;
    }
}
