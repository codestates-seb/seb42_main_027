package ynzmz.server.review.post.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewPostController {
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
    @GetMapping("/{lecture}")
    public void getReviewsByLecture(@PathVariable String lecture){}
    //리뷰 강사별 조회
    @GetMapping("/teacher/{teacher-id}")
    public void getReviewsByTeacher(@PathVariable("teacher-id") long parameter){}
    //리뷰 1건 상세조회
    @GetMapping("/{review-post-id}")
    public void getReviewDetail(@PathVariable("review-post-id") long reviewPostId){}
    //리뷰 삭제
    @DeleteMapping
    public void deleteReview(){}
}
