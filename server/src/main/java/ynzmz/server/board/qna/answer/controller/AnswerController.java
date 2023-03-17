package ynzmz.server.board.qna.answer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.qna.question.service.QuestionService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.board.qna.answer.dto.AnswerDto;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.answer.mapper.AnswerMapper;
import ynzmz.server.board.qna.answer.service.AnswerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/boards/qnas/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<?> postAnswer(@Valid @RequestBody AnswerDto.Post answerPost){

        Answer postDtoToAnswer = answerMapper.answerPostDtoToAnswer(answerPost);
        // 토큰에서 유저 email 확인
        String loginMemberId = SecurityContextHolder.getContext().getAuthentication().getName();
        postDtoToAnswer.setMember(memberService.findMemberByEmail(loginMemberId));
        postDtoToAnswer.setQuestion(questionService.findQuestionById(answerPost.getQuestionId()));

        Answer createAnswer = answerService.createAnswer(postDtoToAnswer);
        AnswerDto.SimpleInfoResponse response = answerMapper.answerToAnswerInfoResponse(createAnswer);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity<?> patchAnswer(@PathVariable("answer-id") long answerId, @RequestBody AnswerDto.Patch answerPatch) {
        //수정시 본인인지 확인
        memberService.memberValidation(loginMemberFindByToken(), answerService.findAnswerById(answerId).getMember().getMemberId());

        Answer answer = answerMapper.answerPatchDtoToAnswer(answerPatch);
        answer.setAnswerId(answerId);

        Answer updateAnswer = answerService.updateAnswer(answer);
        AnswerDto.SimpleInfoResponse response = answerMapper.answerToAnswerInfoResponse(updateAnswer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity<?> getAnswer(@PathVariable("answer-id")long answerId) {
        Answer answer = answerService.findAnswerById(answerId);
        AnswerDto.SimpleInfoResponse response = answerMapper.answerToAnswerInfoResponse(answer);
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }

    //내가쓴 댓글조회
    @GetMapping("/{member-id}/answers")
    public ResponseEntity<?> getMemberAnswer(@PathVariable("member-id") long memberId, @RequestParam int page, @RequestParam int size) {

        //페이지네이션 으로 질문글전체조회와 리스폰값 명세 통일(요청사항)
        Page<Answer> pageAnswers = answerService.findAnswersByMemberId(memberId, page, size);
        List<Answer> answers = pageAnswers.getContent();
        List<AnswerDto.SimpleInfoResponse> responses = answerMapper.answersToAnswerInfoResponses(answers);

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
