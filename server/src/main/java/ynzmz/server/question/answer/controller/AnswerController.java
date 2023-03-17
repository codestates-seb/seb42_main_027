package ynzmz.server.question.answer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.question.answer.dto.AnswerDto;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.answer.mapper.AnswerMapper;
import ynzmz.server.question.answer.service.AnswerService;
import ynzmz.server.question.question.service.QuestionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<?> postAnswer(@Valid @RequestBody AnswerDto.Post answerPost){

        Answer postDtoToAnswer = answerMapper.answerPostDtoToAnswer(answerPost);
        String loginMemberId = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        postDtoToAnswer.setMember(memberService.findMemberByEmail(loginMemberId));
        postDtoToAnswer.setQuestion(questionService.findQuestion(answerPost.getQuestionId()));

        Answer answer = answerService.createAnswer(postDtoToAnswer);
        AnswerDto.InfoResponse response = answerMapper.answerToAnswerInfoResponse(answer);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity<?> patchAnswer(@PathVariable("answer-id") long answerId, @RequestBody AnswerDto.Patch answerPatch) {
        answerPatch.setAnswerId(answerId);
        memberService.memberValidation(loginMemberFindByToken(), answerService.findAnswer(answerId).getMember().getMemberId()); //본인검증

        Answer answer = answerService.updateAnswer(answerMapper.answerPatchDtoToAnswer(answerPatch));
        AnswerDto.InfoResponse response = answerMapper.answerToAnswerInfoResponse(answer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity<?> getAnswer(@PathVariable("answer-id")long answerId) {
        Answer answer = answerService.findAnswer(answerId);
        AnswerDto.InfoResponse response = answerMapper.answerToAnswerInfoResponse(answer);
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }

    //내가쓴 댓글조회
    @GetMapping("/{member-id}/answer")
    public ResponseEntity<?> getMemberAnswer(@PathVariable("member-id") long memberId, @RequestParam int page, @RequestParam int size) {

        //페이지네이션 으로 질문글전체조회와 리스폰값 명세 통일(요청사항)
        Page<Answer> pageAnswers = answerService.findAnswersByMemberId(memberId, page, size);
        List<Answer> answers = pageAnswers.getContent();
        List<AnswerDto.InfoResponse> responses = answerMapper.answersToAnswerInfoResponses(answers);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageAnswers), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAnswers(@RequestParam int page, @RequestParam int size){
        Page<Answer> pageAnswers = answerService.findAnswers(page,size);
        List<Answer> answers = pageAnswers.getContent();
        List<AnswerDto.Response> response = answerMapper.answersToAnswerResponses(answers);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageAnswers),HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("answer-id")long answerId) {
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        Member member = memberService.findMemberByEmail(loginEmail);
        boolean deleteStatus = answerService.deleteAnswer(answerId, member);
        return deleteStatus ? new ResponseEntity<>("삭제완료", HttpStatus.OK) : new ResponseEntity<>("삭제실패",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Member loginMemberFindByToken() {
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}
