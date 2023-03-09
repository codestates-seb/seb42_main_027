package ynzmz.server.lecture.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;

@Mapper(componentModel = "spring")
public interface LectureMapper {
    Lecture lectureToLecturePost(LectureDto.Post lecturePost);
    Lecture lectureToLecturePatch(LectureDto.Patch lecurePatch);

    LectureDto.InfoResponse lectureInfoResponseToLecture(Lecture lecture);
}
