package ynzmz.server.lecture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecture.mapper.LectureMapper;
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.service.TagService;

import java.util.List;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;
    private final LectureMapper lectureMapper;
    private final TagService tagService;
    //강의 등록
    @PostMapping
    public ResponseEntity<?> postLecture(@RequestBody LectureDto.Post lecturePost){
        Lecture lecture = lectureMapper.lectureToLecturePost(lecturePost);
        Lecture createdLecture = lectureService.createdLecture(lecture);

        //학년,과목,플랫폼 Tag 찾기 ( String -> 저장된 객체 )
        List<GradeTag.Grade> gradeTags = tagService.findGradeTags(lecturePost.getGradeTag());
        List<PlatformTag.Platform> platformTags = tagService.findPlatformTags(lecturePost.getPlatformTag());
        List<SubjectTag.Subject> subjectTags = tagService.findSubjectTags(lecturePost.getSubjectTag());

        //생성된 강사 맵핑테이블 생성
        tagService.createLectureTag(createdLecture, gradeTags, platformTags, subjectTags);

        LectureDto.SimpleInfoResponse response = lectureMapper.lectureInfoResponseToLecture(createdLecture);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    //강의 수정
    @PatchMapping("/{lecture-id}")
    public ResponseEntity<?> patchLecture(@PathVariable("lecture-id") long lectureId,
                             @RequestBody LectureDto.Patch lecturePatch){
        Lecture lecture = lectureMapper.lectureToLecturePatch(lecturePatch);
        lecture.setLectureId(lectureId);
        Lecture updateLecture = lectureService.updateLecture(lecture);

        //학년,과목,플랫폼 Tag 찾기 ( String -> 저장된 객체 )
        List<GradeTag.Grade> gradeTags = tagService.findGradeTags(lecturePatch.getGradeTag());
        List<PlatformTag.Platform> platformTags = tagService.findPlatformTags(lecturePatch.getPlatformTag());
        List<SubjectTag.Subject> subjectTags = tagService.findSubjectTags(lecturePatch.getSubjectTag());

        //태그 수정방법 : 저장값 전부 삭제후 재등록
        tagService.deleteAllLectureTagByLecture(updateLecture);
        //맵핑테이블 생성
        tagService.createLectureTag(updateLecture, gradeTags, platformTags, subjectTags);

        LectureDto.SimpleInfoResponse response = lectureMapper.lectureInfoResponseToLecture(updateLecture);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //과목별 강의조회 + 강의 전체 조회
    @GetMapping
    public ResponseEntity<?> getLectureByTag(@RequestParam(value = "tag", required = false) String tag,
                                                  @RequestParam int page,
                                                  @RequestParam int size){

        if(tag == null) {
            Page<Lecture> lecturePage = lectureService.findLectures(page -1, size);
            List<Lecture> lectures = lecturePage.getContent();
            List<LectureDto.SimpleInfoResponse> responses = lectureMapper.lectureInfoResponsesToLectures(lectures);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, lecturePage), HttpStatus.OK);
        } else {
            Page<Lecture> lecturePage = lectureService.findLectures(page -1, size);
            List<Lecture> lectures = lecturePage.getContent();
            List<LectureDto.SimpleInfoResponse> responses = lectureMapper.lectureInfoResponsesToLectures(lectures);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, lecturePage), HttpStatus.OK);

        }
    }

    //강사별 강의조회
    @GetMapping("/teacher")
    public ResponseEntity<?> getLecturesByTeacher(@RequestParam(value = "teacher", required = false) long teacherId,
                                     @RequestParam int page,
                                     @RequestParam int size) {

        Page<Lecture> lecturePage = lectureService.findLecturesByTeacher(teacherId,page -1, size);
        List<Lecture> lectures = lecturePage.getContent();
        List<LectureDto.SimpleInfoResponse> responses = lectureMapper.lectureInfoResponsesToLectures(lectures);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, lecturePage), HttpStatus.OK);

    }

    //강의 한건 디테일조회
    @GetMapping("/{lecture-id}")
    public ResponseEntity<?> getLecturesDetail(@PathVariable("lecture-id") long lectureId) {

        Lecture lecture = lectureService.findLectureById(lectureId);
        LectureDto.SimpleInfoResponse response = lectureMapper.lectureInfoResponseToLecture(lecture);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    //강의 삭제
    @DeleteMapping("/{lecture-id}")
    public void deleteLecture(@PathVariable("lecture-id") long lectureId) {
        lectureService.deleteLecture(lectureId);
    }
}
