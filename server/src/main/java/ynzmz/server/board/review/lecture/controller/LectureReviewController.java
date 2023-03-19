package ynzmz.server.board.review.lecture.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.review.lecture.dto.LectureReviewDto;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.board.review.lecture.mapper.LectureReviewMapper;
import ynzmz.server.board.review.lecture.sevice.LectureReviewService;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.teacher.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/boards/reviews/lectures")
@RequiredArgsConstructor
public class LectureReviewController {
    private final LectureReviewService lectureReviewService;
    private final LectureService lectureService;
    private final TeacherService teacherService;
    private final MemberService memberService;
    private final LectureReviewMapper lectureReviewMapper;
    //리뷰작성
    @PostMapping
    public ResponseEntity<?> postLectureReview(@RequestBody LectureReviewDto.Post lectureReviewPost){
        LectureReview lectureReview = lectureReviewMapper.lectureReviewPostToLectureReview(lectureReviewPost);
        Lecture lecture = lectureService.findLectureById(lectureReviewPost.getLectureId());
        lectureReview.setLecture(lecture);

        //토큰에서 memberId 확인
        lectureReview.setMember(loginMemberFindByToken());

        LectureReview createdLectureReview = lectureReviewService.createLectureReview(lectureReview);

        //리뷰 등록시 강의의 평균점수를 수정 & 리뷰 총 갯수 수정
        lectureService.setLectureStaPointAverageAndTotalReviewCount(createdLectureReview.getLecture());
        //리뷰 등록시 강사의 평균점수를 수정 & 리뷰 총 갯수 수정
        teacherService.setTeacherStarPointAverageAndTotalReviewCount(createdLectureReview.getLecture().getTeacher());

        LectureReviewDto.InfoResponse response = lectureReviewMapper.lectureReviewToLectureReviewInfoResponse(createdLectureReview);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    //리뷰수정
    @PatchMapping("/{lecture-review-id}")
    public ResponseEntity<?> patchLectureReview(@PathVariable("lecture-review-id") long lectureReviewPostId,
                                                @RequestBody LectureReviewDto.Patch lectureReviewPostPatch) {
        LectureReview lectureReview = lectureReviewMapper.lectureReviewPatchToLectureReview(lectureReviewPostPatch);
        lectureReview.setLectureReviewId(lectureReviewPostId);
        LectureReview updatedLectureReview = lectureReviewService.updateLectureReview(lectureReview);

        //리뷰 등록시 강의의 평균점수를 수정
        lectureService.setLectureStaPointAverageAndTotalReviewCount(updatedLectureReview.getLecture());
        //리뷰 등록시 강사의 평균점수를 수정
        teacherService.setTeacherStarPointAverageAndTotalReviewCount(updatedLectureReview.getLecture().getTeacher());

        LectureReviewDto.InfoResponse response = lectureReviewMapper.lectureReviewToLectureReviewInfoResponse(updatedLectureReview);

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
    public ResponseEntity<?> getLectureReviews(@RequestParam(value = "teacher", required = false) long teacherId,
                                               @RequestParam(value = "lecture", required = false) long lectureId,
                                               @RequestParam int page,
                                               @RequestParam int size){

        if(teacherId == 0 && lectureId == 0) {
            //case1 :선생님 지정 x , 강의 지정 x 시 -> 리뷰 전체 조회
            Page<LectureReview> lectureReviewPostPage = lectureReviewService.findAllLectureReviews(page -1, size);
            List<LectureReview> lectureReviews = lectureReviewPostPage.getContent();
            List<LectureReviewDto.ListPageResponse> responses = lectureReviewMapper.lectureReviewsToLectureReviewListPageResponses(lectureReviews);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, lectureReviewPostPage), HttpStatus.OK);
        } else if( teacherId != 0 && lectureId == 0) {
            //case2 : 선생님 지정 o , 강의 지정 x 시 -> 강사의 리뷰 전체 조회
            Page<LectureReview> lectureReviewPostPage = lectureReviewService.findLectureReviewsByTeacher(teacherId, page -1, size);
            List<LectureReview> lectureReviews = lectureReviewPostPage.getContent();
            List<LectureReviewDto.ListPageResponse> responses = lectureReviewMapper.lectureReviewsToLectureReviewListPageResponses(lectureReviews);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, lectureReviewPostPage), HttpStatus.OK);
        } else {
             //* case3 :선생님 지정 x , 강의 지정 o 시 -> 강의 리뷰 전체 조회 ( 강사는 강의에 이미 포함되있는것 ) <br>
             //case4 : 선생님 지정 o , 강의 지정 o 시 -> case 3와 동일 <br>
            Page<LectureReview> lectureReviewPostPage = lectureReviewService.findLectureReviewsByLecture(lectureId, page -1, size);
            List<LectureReview> lectureReviews = lectureReviewPostPage.getContent();
            List<LectureReviewDto.ListPageResponse> responses = lectureReviewMapper.lectureReviewsToLectureReviewListPageResponses(lectureReviews);
            return new ResponseEntity<>(new MultiResponseDto<>(responses, lectureReviewPostPage), HttpStatus.OK);
        }
    }

    //리뷰 1건 상세조회
    @GetMapping("/{lecture-review-id}")
    public ResponseEntity<?> getReviewDetail(@PathVariable("lecture-review-id") long lectureReviewId){
        LectureReview lectureReview = lectureReviewService.findLectureReviewById(lectureReviewId);
        LectureReviewDto.DetailPageResponse response = lectureReviewMapper.lectureReviewToLectureReviewDetailPageResponse(lectureReview);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    //리뷰 삭제
    @DeleteMapping("/{lecture-review-id}")
    public void deleteReview(@PathVariable("lecture-review-id") long lectureReviewPostId){
        lectureReviewService.deleteLectureReview(lectureReviewPostId);
    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}
