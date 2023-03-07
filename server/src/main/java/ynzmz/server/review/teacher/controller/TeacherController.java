package ynzmz.server.review.teacher.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    //강사등록
    @PostMapping
    public void postTeacher(){}
    //강사수정
    @PatchMapping
    public void patchTeacher(){}
    //강사 전체조회
    @GetMapping
    public void getAllTeachers(){}
    //과목별 강사조회
    @GetMapping("/{lecture}")
    public void getTeachersByLecture(@PathVariable String lecture){}
    //강사 상세조회
    @GetMapping("/{teacher-id}")
    public void getTeacherDetail(@PathVariable("teacher-id") long parameter){}
    //강사 삭제
    @DeleteMapping
    public void deleteTeacher(){}
}
