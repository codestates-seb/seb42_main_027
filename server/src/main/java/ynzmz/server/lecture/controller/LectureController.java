package ynzmz.server.lecture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.lecture.mapper.LectureMapper;
import ynzmz.server.lecture.service.LectureService;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;
    private final LectureMapper lectureMapper;
    //강의 등록
    @PostMapping
    public void postLecture(){}
    //강의 수정
    @PatchMapping("/{lecture-id}")
    public void patchLecture(@PathVariable("lecture-id") long lectureId){}
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
