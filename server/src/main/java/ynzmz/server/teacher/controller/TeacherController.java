package ynzmz.server.teacher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.tag.entity.Tag;
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
@Slf4j
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

        //TeacherTag 생성
        List<Tag.Type> tagsByType = tagService.findTagsByType(teacherPost.getTags());
        teacherTagService.createTeacherTag(createdTeacher,tagsByType);

        TeacherDto.infoResponse response = teacherMapper.teacherInfoResponseToTeacher(teacher);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    //강사수정
    @PatchMapping("/{teacher-id}")
    public ResponseEntity<?> patchTeacher(@PathVariable("teacher-id") long teacherId,
                             @RequestBody TeacherDto.Patch teacherPatch) {
        teacherPatch.setTeacherId(teacherId);
        Teacher teacher = teacherMapper.teacherToTeacherPatch(teacherPatch);
        Teacher updatedTeacher = teacherService.updateTeacher(teacher);

        //TeacherTag 수정 (삭제후 수정)
        List<Tag.Type> tagsByType = tagService.findTagsByType(teacherPatch.getTags());
        teacherTagService.deleteAllTeacherTagByTeacher(updatedTeacher);
        teacherTagService.createTeacherTag(updatedTeacher,tagsByType);

        TeacherDto.infoResponse response = teacherMapper.teacherInfoResponseToTeacher(updatedTeacher);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    //강사 전체조회
    @GetMapping
    public void getAllTeachers(){}
    //과목별 강사조회
    @GetMapping("/subject/{subject}")
    public void getTeachersBySubject(@PathVariable String subject){}
    //강사 상세조회
    @GetMapping("/{teacher-id}")
    public ResponseEntity<?> getTeacherDetail(@PathVariable("teacher-id") long teacherId){
        Teacher teacher = teacherService.findTeacherById(teacherId);
        TeacherDto.infoResponse response = teacherMapper.teacherInfoResponseToTeacher(teacher);
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }
    //강사 삭제
    @DeleteMapping("/{teacher-id}")
    public void deleteTeacher(@PathVariable("teacher-id") long teacherId){}
}
