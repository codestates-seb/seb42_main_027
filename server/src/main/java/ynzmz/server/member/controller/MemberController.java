package ynzmz.server.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.mapper.FreeMapper;
import ynzmz.server.board.free.service.FreeService;
import ynzmz.server.board.qna.answer.dto.AnswerDto;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.answer.mapper.AnswerMapper;
import ynzmz.server.board.qna.answer.service.AnswerService;
import ynzmz.server.board.qna.question.dto.QuestionDto;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.board.qna.question.mapper.QuestionMapper;
import ynzmz.server.board.qna.question.service.QuestionService;
import ynzmz.server.board.review.lecture.dto.LectureReviewDto;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.board.review.lecture.mapper.LectureReviewMapper;
import ynzmz.server.board.review.lecture.sevice.LectureReviewService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.mapper.MemberMapper;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.PrimitiveIterator;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Setter
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final LectureReviewService lectureReviewService;
    private final LectureReviewMapper lectureReviewMapper;
    private final FreeService freeService;
    private final FreeMapper freeMapper;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @PostMapping
    public ResponseEntity<?> postMember(@RequestBody @Valid MemberDto.Post requestBody){
        if (!requestBody.getPassword().equals(requestBody.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("패스워드와 패스워드 확인이 일치하지 않습니다.");
        }
        Member member = memberMapper.memberPostToMember(requestBody);
        member.setState(Enum.valueOf( Member.State.class, requestBody.getState() ));

        Member createdMember = memberService.createMember(member);
        MemberDto.Response response=  memberMapper.memberToMemberResponse(createdMember);
        member.setCreatedAt(requestBody.getCreatedAt());

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity<?> patchMember(@PathVariable("member-id") @Positive long memberId, @RequestBody @Valid MemberDto.Patch requestBody) {
        Member member = memberMapper.memberPatchToMember(requestBody);
        member.setMemberId(memberId);

        Member updatedMember = memberService.updateMember(member);
        MemberDto.Response response=  memberMapper.memberToMemberResponse(updatedMember);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }


    @GetMapping("/{email}")
    public ResponseEntity<?> getMember(@PathVariable("email") @Positive String email){
        Member findMember = memberService.findMemberByEmail(email);
        MemberDto.Response response = memberMapper.memberToMemberResponse(findMember);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getMembers(@Positive @RequestParam int page,
                                        @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page-1, size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(new MultiResponseDto<>(memberMapper.memberToMemberResponses(members),pageMembers),HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member-id") @Positive long memberId){
//        boolean deleteStatus = memberService.deleteMember(memberId);
//        return deleteStatus ? new ResponseEntity<>("삭제완료", HttpStatus.OK) : new ResponseEntity<>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //내가쓴 질문글조회
    @GetMapping("/{member-id}/questions")
    public ResponseEntity<?> getMyQuestions(@PathVariable("member-id")
                                                long memberId,
                                            @Positive @RequestParam int page,
                                        @Positive @RequestParam int size) {
        Page<Question> pageQuestions = questionService.findQuestionsByMemberId(memberId,page-1, size);
        List<Question> myQuestions = pageQuestions.getContent();
        List<QuestionDto.ListPageResponse> responses = questionMapper.questionToQuestionListPageResponses(myQuestions); //통일
        return new ResponseEntity<>(new MultiResponseDto<>(responses,pageQuestions),HttpStatus.OK);
    }

//    내가쓴 강의리뷰조회
    @GetMapping("/{member-id}/reviews")
    public ResponseEntity<?> getMyReviews(@PathVariable("member-id")
                                                  long memberId,
                                              @Positive @RequestParam int page,
                                              @Positive @RequestParam int size){
        Page<LectureReview> pageLectureReviews = lectureReviewService.findLectureReviewsByMemberId(memberId, page-1,size);
        List<LectureReview> myLectureReviews = pageLectureReviews.getContent();
        List<LectureReviewDto.InfoResponse> responses = lectureReviewMapper.lectureReviewToLectureReviewInfoResponses(myLectureReviews);
        return new ResponseEntity<>(new MultiResponseDto<>(responses,pageLectureReviews),HttpStatus.OK);
    }

    //내가쓴 자유게시판글 조회
    @GetMapping("/{member-id}/frees")
    public ResponseEntity<?> getMyFrees(@PathVariable("member-id")
                                            long memberId,
                                        @Positive @RequestParam int page,
                                        @Positive @RequestParam int size){
        Page<Free> pageFrees = freeService.findFreeByMemberId(memberId,page-1,size);
        List<Free> myFrees = pageFrees.getContent();
        List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(myFrees);
        return new ResponseEntity<>(new MultiResponseDto<>(responses,pageFrees),HttpStatus.OK);
    }

    //내가쓴 답변글 조회
    @GetMapping("/{member-id}/answers")
    public ResponseEntity<?> getMyAnswers(@PathVariable("member-id")
                                          long memberId,
                                          @Positive @RequestParam int page,
                                          @Positive @RequestParam int size){
        Page<Answer> pageAnswers = answerService.findAnswersByMemberId(memberId,page-1,size);
        List<Answer> myAnswers = pageAnswers.getContent();
        List<AnswerDto.SimpleInfoResponse> responses = answerMapper.answersToAnswerInfoResponses(myAnswers);
        return new ResponseEntity<>(new MultiResponseDto<>(responses,pageAnswers),HttpStatus.OK);
    }

}
