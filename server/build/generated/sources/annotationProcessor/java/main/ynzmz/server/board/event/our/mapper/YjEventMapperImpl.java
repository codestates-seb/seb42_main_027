package ynzmz.server.board.event.our.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.event.our.dto.YjEventDto;
import ynzmz.server.board.event.our.entity.YjEvent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-18T10:44:00+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class YjEventMapperImpl implements YjEventMapper {

    @Override
    public YjEvent yjEventPostToYjEvent(YjEventDto.Post p) {
        if ( p == null ) {
            return null;
        }

        YjEvent yjEvent = new YjEvent();

        return yjEvent;
    }

    @Override
    public YjEvent yjEventPatchToYjEvent(YjEventDto.Patch p) {
        if ( p == null ) {
            return null;
        }

        YjEvent yjEvent = new YjEvent();

        return yjEvent;
    }

    @Override
    public YjEvent YjEventResponseToyjEvent(YjEventDto.Response r) {
        if ( r == null ) {
            return null;
        }

        YjEvent yjEvent = new YjEvent();

        return yjEvent;
    }
}
