package ynzmz.server.teacher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.service.TagService;
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
    private final TagService tagService;
    //강사등록
    @PostMapping
    public ResponseEntity<?> postTeacher(@RequestBody TeacherDto.Post teacherPost){
        //태그를 받았을때 태그객체 생성 및 저장 필요
        Teacher teacher = teacherMapper.teacherToTeacherPost(teacherPost);
        Teacher createdTeacher = teacherService.createTeacher(teacher);

        //학년,과목,플랫폼 Tag 찾기 ( String -> 저장된 객체 )
        List<GradeTag.Grade> gradeTags = tagService.findGradeTags(teacherPost.getGradeTags());
        List<PlatformTag.Platform> platformTags = tagService.findPlatformTags(teacherPost.getPlatformTags());
        List<SubjectTag.Subject> subjectTags = tagService.findSubjectTags(teacherPost.getSubjectTags());

        //생성된 강사 맵핑테이블 생성
        tagService.createTeacherTag(createdTeacher, gradeTags, platformTags, subjectTags);

        TeacherDto.SimpleInfoResponse response = teacherMapper.teacherInfoResponseToTeacher(teacher);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    //강사수정
    @PatchMapping("/{teacher-id}")
    public ResponseEntity<?> patchTeacher(@PathVariable("teacher-id") long teacherId,
                             @RequestBody TeacherDto.Patch teacherPatch) {
        Teacher teacher = teacherMapper.teacherToTeacherPatch(teacherPatch);
        teacher.setTeacherId(teacherId);
        Teacher updatedTeacher = teacherService.updateTeacher(teacher);

        //학년,과목,플랫폼 Tag 찾기 ( String -> 저장된 객체 )
        List<GradeTag.Grade> gradeTags = tagService.findGradeTags(teacherPatch.getGradeTags());
        List<PlatformTag.Platform> platformTags = tagService.findPlatformTags(teacherPatch.getPlatformTags());
        List<SubjectTag.Subject> subjectTags = tagService.findSubjectTags(teacherPatch.getSubjectTags());

        //태그 수정방법 : 저장값 전부 삭제후 재등록
        tagService.deleteAllTeacherTagByTeacher(updatedTeacher);
        //생성된 강사 맵핑테이블 생성
        tagService.createTeacherTag(updatedTeacher, gradeTags, platformTags, subjectTags);

        TeacherDto.SimpleInfoResponse response = teacherMapper.teacherInfoResponseToTeacher(updatedTeacher);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    //과목별 강사조회 + 강사 전체 조회
    @GetMapping
    public ResponseEntity<?> getTeachersByTag(@RequestParam(value = "tag", required = false) String tag,
                                     @RequestParam int page,
                                     @RequestParam int size){

        if(tag == null) {
            Page<Teacher> teacherPage = teacherService.findTeachers(page -1, size);
            List<Teacher> teachers = teacherPage.getContent();
            List<TeacherDto.SimpleInfoResponse> responses = teacherMapper.teacherInfoResponsesToTeachers(teachers);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, teacherPage), HttpStatus.OK);
        } else {
            Page<Teacher> teacherPage = teacherService.findTeachers(tag,page -1, size);
            List<Teacher> teachers = teacherPage.getContent();
            List<TeacherDto.SimpleInfoResponse> responses = teacherMapper.teacherInfoResponsesToTeachers(teachers);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, teacherPage), HttpStatus.OK);

        }
    }
    //강사 상세조회
    @GetMapping("/{teacher-id}")
    public ResponseEntity<?> getTeacherDetail(@PathVariable("teacher-id") long teacherId){
        Teacher teacher = teacherService.findTeacherById(teacherId);
        TeacherDto.SimpleInfoResponse response = teacherMapper.teacherInfoResponseToTeacher(teacher);
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }
    //강사 삭제
    @DeleteMapping("/{teacher-id}")
    public void deleteTeacher(@PathVariable("teacher-id") long teacherId){
        teacherService.deleteTeacher(teacherId);
    }
}
