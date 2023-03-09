package ynzmz.server.teacher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.mapper.TeacherMapper;
import ynzmz.server.teacher.service.TeacherService;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;
    //강사등록
    @PostMapping
    public void postTeacher(@RequestBody TeacherDto.Post teacherPost){

    }
    //강사수정
    @PatchMapping("/{teacher-id}")
    public void patchTeacher(@PathVariable("teacher-id") long teacherId){}
    //강사 전체조회
    @GetMapping
    public void getAllTeachers(){}
    //과목별 강사조회
    @GetMapping("/{subject}")
    public void getTeachersBySubject(@PathVariable String subject){}
    //강사 상세조회
    @GetMapping("/{teacher-id}")
    public void getTeacherDetail(@PathVariable("teacher-id") long teacherId){}
    //강사 삭제
    @DeleteMapping("/{teacher-id}")
    public void deleteTeacher(@PathVariable("teacher-id") long teacherId){}
}
