package ynzmz.server.lecturereviewpost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.lecturereviewpost.dto.LectureReviewPostDto;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.lecturereviewpost.mapper.LectureReviewPostMapper;
import ynzmz.server.lecturereviewpost.sevice.LectureReviewPostService;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;
import ynzmz.server.teacher.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class LectureReviewPostController {
    private final LectureReviewPostService lectureReviewPostService;
    private final LectureService lectureService;
    private final TeacherService teacherService;
    private final LectureReviewPostMapper lectureReviewPostMapper;
    //리뷰작성
    @PostMapping
    public ResponseEntity<?> postReviewPost(@RequestBody LectureReviewPostDto.Post lectureReviewPostPost){
        LectureReviewPost lectureReviewPost = lectureReviewPostMapper.lectureReviewPostToLectureReviewPostPost(lectureReviewPostPost);
        LectureReviewPost createdLectureReviewPost = lectureReviewPostService.createLectureReviewPost(lectureReviewPost);

        //등록시 강의의 평균점수를 수정
        double averageStarPoint = lectureReviewPostService.getLectureReviewPostAverageStarPoint(createdLectureReviewPost);
        lectureService.LectureStarPointAverageUpdate(createdLectureReviewPost.getLecture(), averageStarPoint);


        LectureReviewPostDto.InfoResponse response = lectureReviewPostMapper.lectureReviewPostInfoResponseToLectureReviewPost(createdLectureReviewPost);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    //리뷰수정
    @PatchMapping("/{review-post-id}")
    public ResponseEntity<?> patchReviewPost(@PathVariable("review-post-id") long lectureReviewPostId,
                                @RequestBody LectureReviewPostDto.Patch lectureReviewPostPatch) {
        LectureReviewPost lectureReviewPost = lectureReviewPostMapper.lectureReviewPostToLectureReviewPostPatch(lectureReviewPostPatch);
        lectureReviewPost.setLectureReviewPostId(lectureReviewPostId);
        LectureReviewPost updatedLectureReviewPost = lectureReviewPostService.updateLectureReviewPost(lectureReviewPost);

        //등록시 강의의 평균점수를 수정
        double averageStarPoint = lectureReviewPostService.getLectureReviewPostAverageStarPoint(updatedLectureReviewPost);
        lectureService.LectureStarPointAverageUpdate(updatedLectureReviewPost.getLecture(), averageStarPoint);

        LectureReviewPostDto.InfoResponse response = lectureReviewPostMapper.lectureReviewPostInfoResponseToLectureReviewPost(updatedLectureReviewPost);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }


    /**
     * <h2>강의별 리뷰 조회 + 강사별 리뷰 조회 + 리뷰 전체 조회 <br></h2>
     * case1 :선생님 지정 x , 강의 지정 x 시 -> 리뷰 전체 조회 <br>
     * case2 : 선생님 지정 o , 강의 지정 x 시 -> 강사의 리뷰 전체 조회 <br>
     * case3 :선생님 지정 x , 강의 지정 o 시 -> 강의 리뷰 전체 조회 ( 강사는 강의에 이미 포함되있는것 ) <br>
     * case4 : 선생님 지정 o , 강의 지정 o 시 -> case 3와 동일 <br>
     */
    @GetMapping
    public ResponseEntity<?> getLectureReviewPosts(@RequestParam(value = "teacher", required = false) long teacherId,
                                              @RequestParam(value = "lecture", required = false) long lectureId,
                                              @RequestParam int page,
                                              @RequestParam int size){

        if(teacherId == 0 && lectureId == 0) {
            //case1 :선생님 지정 x , 강의 지정 x 시 -> 리뷰 전체 조회
            Page<LectureReviewPost> lectureReviewPostPage = lectureReviewPostService.findAllLectureReviewPosts(page -1, size);
            List<LectureReviewPost> lectureReviewPosts = lectureReviewPostPage.getContent();
            List<LectureReviewPostDto.InfoResponse> responses = lectureReviewPostMapper.lectureReviewPostInfoResponsesToLectureReviewPosts(lectureReviewPosts);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, lectureReviewPostPage), HttpStatus.OK);
        } else if( teacherId != 0 && lectureId == 0) {
            //case2 : 선생님 지정 o , 강의 지정 x 시 -> 강사의 리뷰 전체 조회
            Page<LectureReviewPost> lectureReviewPostPage = lectureReviewPostService.findLectureReviewPostsByTeacher(teacherId, page -1, size);
            List<LectureReviewPost> lectureReviewPosts = lectureReviewPostPage.getContent();
            List<LectureReviewPostDto.InfoResponse> responses = lectureReviewPostMapper.lectureReviewPostInfoResponsesToLectureReviewPosts(lectureReviewPosts);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, lectureReviewPostPage), HttpStatus.OK);
        } else {
             //* case3 :선생님 지정 x , 강의 지정 o 시 -> 강의 리뷰 전체 조회 ( 강사는 강의에 이미 포함되있는것 ) <br>
             //case4 : 선생님 지정 o , 강의 지정 o 시 -> case 3와 동일 <br>
            Page<LectureReviewPost> lectureReviewPostPage = lectureReviewPostService.findLectureReviewPostsByLecture(lectureId, page -1, size);
            List<LectureReviewPost> lectureReviewPosts = lectureReviewPostPage.getContent();
            List<LectureReviewPostDto.InfoResponse> responses = lectureReviewPostMapper.lectureReviewPostInfoResponsesToLectureReviewPosts(lectureReviewPosts);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, lectureReviewPostPage), HttpStatus.OK);
        }
    }
    //리뷰 1건 상세조회
    @GetMapping("/{review-post-id}")
    public void getReviewPostDetail(@PathVariable("review-post-id") long reviewPostId){}
    //리뷰 삭제
    @DeleteMapping("/{review-post-id}")
    public void deleteReview(@PathVariable("review-post-id") long reviewPostId){}
}
