package ynzmz.server.lecturereviewpost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.lecturereviewpost.dto.LectureReviewPostDto;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.lecturereviewpost.mapper.LectureReviewPostMapper;
import ynzmz.server.lecturereviewpost.sevice.LectureReviewPostService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class LectureReviewPostController {
    private final LectureReviewPostService lectureReviewPostService;
    private final LectureService lectureService;
    private final LectureReviewPostMapper lectureReviewPostMapper;
    //리뷰작성
    @PostMapping
    public ResponseEntity<?> postReviewPost(@RequestBody LectureReviewPostDto.Post lectureReviewPostPost){
        LectureReviewPost lectureReviewPost = lectureReviewPostMapper.LectureReviewPostToLectureReviewPostPost(lectureReviewPostPost);
        LectureReviewPost createdLectureReviewPost = lectureReviewPostService.createLectureReviewPost(lectureReviewPost);

        //등록시 강의의 평균점수를 수정
        double averageStarPoint = lectureReviewPostService.getLectureReviewPostAverageStarPoint(createdLectureReviewPost);
        lectureService.LectureStarPointAverageUpdate(createdLectureReviewPost.getLecture(), averageStarPoint);


        LectureReviewPostDto.InfoResponse response = lectureReviewPostMapper.LectureReviewPostInfoResponseToLectureReviewPost(createdLectureReviewPost);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    //리뷰수정
    @PatchMapping("/{review-post-id}")
    public ResponseEntity<?> patchReviewPost(@PathVariable("review-post-id") long lectureReviewPostId,
                                @RequestBody LectureReviewPostDto.Patch lectureReviewPostPatch) {
        LectureReviewPost lectureReviewPost = lectureReviewPostMapper.LectureReviewPostToLectureReviewPostPatch(lectureReviewPostPatch);
        lectureReviewPost.setLectureReviewPostId(lectureReviewPostId);
        LectureReviewPost updatedLectureReviewPost = lectureReviewPostService.updateLectureReviewPost(lectureReviewPost);

        //등록시 강의의 평균점수를 수정
        double averageStarPoint = lectureReviewPostService.getLectureReviewPostAverageStarPoint(updatedLectureReviewPost);
        lectureService.LectureStarPointAverageUpdate(updatedLectureReviewPost.getLecture(), averageStarPoint);

        LectureReviewPostDto.InfoResponse response = lectureReviewPostMapper.LectureReviewPostInfoResponseToLectureReviewPost(updatedLectureReviewPost);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    //리뷰 전체 조회
    @GetMapping
    public void getAllReviewPost(){}
    //리뷰 강의별 조회
    @GetMapping("/{subject}")
    public void getReviewPostsBySubject(@PathVariable String subject){}
    //리뷰 강사별 조회
    @GetMapping("/teacher/{teacher-id}")
    public void getReviewPostsByTeacher(@PathVariable("teacher-id") long teacherId){}
    //리뷰 1건 상세조회
    @GetMapping("/{review-post-id}")
    public void getReviewPostDetail(@PathVariable("review-post-id") long reviewPostId){}
    //리뷰 삭제
    @DeleteMapping("/{review-post-id}")
    public void deleteReview(@PathVariable("review-post-id") long reviewPostId){}
}
