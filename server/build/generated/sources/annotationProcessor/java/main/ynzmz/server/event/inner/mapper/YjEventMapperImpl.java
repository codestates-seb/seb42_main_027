package ynzmz.server.event.inner.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.event.inner.dto.YjEventDto;
import ynzmz.server.event.inner.entity.YjEvent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T12:03:23+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
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
