package ynzmz.server.lecture.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.entity.LectureTag;
import ynzmz.server.tag.entity.Tag;
import ynzmz.server.tag.entity.TeacherTag;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LectureMapper {
    Lecture lectureToLecturePost(LectureDto.Post lecturePost);
    Lecture lectureToLecturePatch(LectureDto.Patch lecurePatch);

    default LectureDto.InfoResponse lectureInfoResponseToLecture(Lecture lecture) {
        if ( lecture == null ) {
            return null;
        }

        LectureDto.InfoResponse infoResponse = new LectureDto.InfoResponse();

        if ( lecture.getLectureId() != null ) {
            infoResponse.setLectureId( lecture.getLectureId() );
        }
        infoResponse.setName( lecture.getName() );
        infoResponse.setIntroduction( lecture.getIntroduction() );
        infoResponse.setStarPointAverage( lecture.getStarPointAverage() );
        List<LectureTag> list = lecture.getLectureTags();

        ArrayList<Tag.Type> responses = new ArrayList<>();

        for(LectureTag lectureTag: list){
            responses.add(lectureTag.getTag().getType());
        }
        infoResponse.setTags(responses);

        return infoResponse;
    }

    List<LectureDto.InfoResponse> lectureInfoResponsesToLectures(List<Lecture> lectures);
}
