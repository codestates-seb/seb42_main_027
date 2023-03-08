package ynzmz.server.teacher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.tag.Tag;
import ynzmz.server.tag.service.TagService;
import ynzmz.server.tag.service.TeacherTagService;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;
import ynzmz.server.teacher.mapper.TeacherMapper;
import ynzmz.server.teacher.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;
    private final TeacherTagService teacherTagService;
    private final TagService tagService;
    //강사등록
    @PostMapping
    public ResponseEntity<?> postTeacher(@RequestBody TeacherDto.Post teacherPost){
        //태그를 받았을때 태그객체 생성 및 저장 필요
        Teacher teacher = teacherMapper.teacherToTeacherPost(teacherPost);
        Teacher createdTeacher = teacherService.createTeacher(teacher);

        List<Tag.Type> tagsByType = tagService.findTagsByType(teacherPost.getTags());

        teacherTagService.createTeacherTag(createdTeacher,tagsByType);

        Teacher teacher1 = teacherService.getTeacher(createdTeacher.getTeacherId());
        TeacherDto.infoResponse response = teacherMapper.teacherInfoResponseToTeacher(teacher1);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
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
    public ResponseEntity<?> getTeacherDetail(@PathVariable("teacher-id") long teacherId) throws Exception {
        Teacher teacher = teacherService.getTeacher(teacherId);
        TeacherDto.infoResponse response = teacherMapper.teacherInfoResponseToTeacher(teacher);
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }
    //강사 삭제
    @DeleteMapping("/{teacher-id}")
    public void deleteTeacher(@PathVariable("teacher-id") long teacherId){}
}
