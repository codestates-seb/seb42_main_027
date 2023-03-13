package ynzmz.server.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.mappingtable.lecture.LectureTag;
import ynzmz.server.tag.entity.Tag;
import ynzmz.server.tag.repository.LectureTagRepository;
import ynzmz.server.tag.repository.TagRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureTagService {
    private final LectureTagRepository lectureTagRepository;
    private final TagRepository tagRepository;
    public void createLectureTag(Lecture lecture, List<Tag.Type> types){
        for(Tag.Type type : types){
            Tag findType = tagRepository.findTagByType(type);
            LectureTag lectureTag = LectureTag.builder()
                    .lecture(lecture)
                    .tag(findType)
                    .build();
            lectureTagRepository.save(lectureTag);
        }
    }
    @Transactional
    public void deleteAllLectureTagByLecture(Lecture lecture) {
        lectureTagRepository.deleteAllLectureTagByLecture(lecture);
    }
}
