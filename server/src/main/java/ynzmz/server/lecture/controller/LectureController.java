package ynzmz.server.lecture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecture.mapper.LectureMapper;
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.tag.entity.Tag;
import ynzmz.server.tag.service.LectureTagService;
import ynzmz.server.tag.service.TagService;

import java.util.List;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;
    private final LectureMapper lectureMapper;
    private final TagService tagService;
    private final LectureTagService lectureTagService;
    //강의 등록
    @PostMapping
    public ResponseEntity<?> postLecture(@RequestBody LectureDto.Post lecturePost){
        Lecture lecture = lectureMapper.lectureToLecturePost(lecturePost);
        Lecture createdLecture = lectureService.createdLecture(lecture);

        //LectureTag 생성
        List<Tag.Type> tagsByType = tagService.findTagsByType(lecturePost.getTags());
        lectureTagService.createLectureTag(createdLecture,tagsByType);


        LectureDto.InfoResponse response = lectureMapper.lectureInfoResponseToLecture(createdLecture);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    //강의 수정
    @PatchMapping("/{lecture-id}")
    public ResponseEntity<?> patchLecture(@PathVariable("lecture-id") long lectureId,
                             @RequestBody LectureDto.Patch lecturePatch){
        Lecture lecture = lectureMapper.lectureToLecturePatch(lecturePatch);
        lecture.setLectureId(lectureId);
        Lecture updateLecture = lectureService.updateLecture(lecture);

        //LectureTag 수정 (삭제후 수정)
        List<Tag.Type> tagsByType = tagService.findTagsByType(lecturePatch.getTags());
        lectureTagService.deleteAllLectureTagByLecture(updateLecture);
        lectureTagService.createLectureTag(updateLecture,tagsByType);

        LectureDto.InfoResponse response = lectureMapper.lectureInfoResponseToLecture(updateLecture);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    //강의 전체조회
    @GetMapping
    public void getAllLecture(){}
    //강사별 강의조회
    @GetMapping("/teacher/{teacher-id}")
    public void getLecturesByTeacher(@PathVariable("teacher-id") long teacherId){}
    //과목별 강의조회
    @GetMapping("/{subject}")
    public void getLecturesBySubject(@PathVariable String subject){}
    //강의 한건 디테일조회
    @GetMapping("/{lecture-id}")
    public void getLecturesDetail(@PathVariable("lecture-id") long lectureId){}
    //강의 삭제
    @DeleteMapping("/{lecture-id}")
    public void deleteLecture(@PathVariable("lecture-id") long lectureId){}
}
