package ynzmz.server.lecture.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.Tag;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LectureMapper {
    Lecture lectureToLecturePost(LectureDto.Post lecturePost);
    Lecture lectureToLecturePatch(LectureDto.Patch lecurePatch);

    default LectureDto.SimpleInfoResponse lectureInfoResponseToLecture(Lecture lecture) {
        if ( lecture == null ) {
            return null;
        }

        LectureDto.SimpleInfoResponse simpleInfoResponse = new LectureDto.SimpleInfoResponse();

        if ( lecture.getLectureId() != null ) {
            simpleInfoResponse.setLectureId( lecture.getLectureId() );
        }
        simpleInfoResponse.setName( lecture.getTitle() );
        simpleInfoResponse.setIntroduction( lecture.getIntroduction() );
        simpleInfoResponse.setStarPointAverage( lecture.getStarPointAverage() );
        List<LectureTag> list = lecture.getLectureTags();

        ArrayList<Tag.Type> responses = new ArrayList<>();

        for(LectureTag lectureTag : list){
            responses.add(lectureTag.getTag().getType());
        }
        simpleInfoResponse.setTags(responses);

        return simpleInfoResponse;
    }

    List<LectureDto.SimpleInfoResponse> lectureInfoResponsesToLectures(List<Lecture> lectures);
}
