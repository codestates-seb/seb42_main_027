package ynzmz.server.reviewpost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.reviewpost.mapper.ReviewPostMapper;
import ynzmz.server.reviewpost.sevice.ReviewPostService;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewPostController {
    private final ReviewPostService reviewPostService;
    private final ReviewPostMapper reviewPostMapper;
    //리뷰작성
    @PostMapping
    public void postReviewPost(){}
    //리뷰수정
    @PatchMapping("/{review-post-id}")
    public void patchReviewPost(@PathVariable("review-post-id") long parameter){}
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
