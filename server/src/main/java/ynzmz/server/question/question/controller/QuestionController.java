package ynzmz.server.question.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.member.dto.LoginMemberVoteInfo;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.question.answer.dto.AnswerInfoResponseDto;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.answer.mapper.AnswerMapper;
import ynzmz.server.question.answer.service.AnswerService;
import ynzmz.server.question.question.dto.QuestionDto;
import ynzmz.server.question.question.entity.Question;
import ynzmz.server.question.question.mapper.QuestionMapper;
import ynzmz.server.question.question.service.QuestionService;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.service.TagService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Validated
@Slf4j
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;
    private final AnswerService answerService;
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<?> postQuestion(@Valid @RequestBody QuestionDto.Post questionPost){
        Question requestQuestion = questionMapper.questionPostToQuestion(questionPost);
        //토큰에서 memberId 확인
        requestQuestion.setMember(loginMemberFindByToken());

        Question createdQuestion = questionService.createQuestion(requestQuestion);

        List<SubjectTag.Subject> subjectTags = tagService.findSubjectTags(questionPost.getSubjectTag());
        tagService.createQuestionTag(createdQuestion, subjectTags);

        QuestionDto.InfoResponse response = questionMapper.questionToQuestionInfoResponse(createdQuestion);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity<?> patchQuestion(@PathVariable("question-id") long questionId, @Valid @RequestBody QuestionDto.Patch questionPatch){
        // 본인조건확인
        memberService.memberValidation(loginMemberFindByToken(), questionService.findQuestion(questionId).getMember().getMemberId()); // 작성자 & 로그인된 회원 검증

        Question question = questionMapper.questionPatcToQuestion(questionPatch);
        question.setQuestionId(questionId);

        Question updateQuestion = questionService.updateQuestion(question);

        tagService.deleteAllQuestionTagByQuestion(updateQuestion);

        List<SubjectTag.Subject> subjectTags = tagService.findSubjectTags(questionPatch.getSubjectTag());
        tagService.createQuestionTag(updateQuestion, subjectTags);

        QuestionDto.InfoResponse response = questionMapper.questionToQuestionInfoResponse(updateQuestion);

        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }


    @GetMapping("/{question-id}")
    public ResponseEntity<?> getQuestion(@PathVariable("question-id") long questionId) {
        try {
            Member loginMember = loginMemberFindByToken();

            Question question = questionService.findQuestion(questionId);


            questionService.setViewCount(question); //조회수기능  1번당 1씩 올라가게 (임시)
            LoginMemberVoteInfo loginMemberVoteInfo = memberService.setMemberVoteStatus(loginMember, question);
            QuestionDto.DetailPageResponse response = questionMapper.questionToQuestionDetailPageResponse(question);
            response.setLoginUserInfo(loginMemberVoteInfo);


            return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
        } catch (BusinessLogicException e) {
            Question question = questionService.findQuestion(questionId);

            QuestionDto.DetailPageResponse response = questionMapper.questionToQuestionDetailPageResponse(question);
            Member loginMember2 = new Member();
            loginMember2.setMemberId(null);
            loginMember2.setQuestions(null);
            loginMember2.setEmail(null);
            LoginMemberVoteInfo loginMemberVoteInfo = memberService.setMemberVoteStatus(loginMember2, question);
            response.setLoginUserInfo(loginMemberVoteInfo);

            return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
        }
    }

    //질문글 검색 리스트페이지
    @GetMapping
    public ResponseEntity<?> getQuestions(@RequestParam int page,@RequestParam int size){
        Page<Question> pageQuestions = questionService.findQuestions(page,size);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionDto.ListPageResponse> responses = questionMapper.questionToQuestionListPageResponses(questions);

        return new ResponseEntity<>(new MultiResponseDto<>(responses,pageQuestions), HttpStatus.OK);
    }

    //내가쓴글 조회
    @GetMapping("/{member-id}/question")
    public ResponseEntity<?> getMemberQuestion(@PathVariable("member-id") long memberId, @RequestParam int page, @RequestParam int size) {

        memberService.memberValidation(loginMemberFindByToken(), memberId); // 작성자 & 로그인된 회원 검증

        //페이지네이션 으로 질문글전체조회와 리스폰값 명세 통일(요청사항)
        Page<Question> pageQuestions = questionService.findQuestionsByMemberId(memberId, page, size);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionDto.ListPageResponse> responses = questionMapper.questionToQuestionListPageResponses(questions);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageQuestions), HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("question-id") long questionId){

        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        Member member = memberService.findMemberByEmail(loginEmail);
        boolean deleteStatus = questionService.deleteQuestion(questionId, member);

        return deleteStatus ? new ResponseEntity<>("삭제완료",HttpStatus.OK) : new ResponseEntity<>("삭제실패",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 답변 채택기능
     * @param questionId = 질문글 식별자
     * @param answerId = 답변글 식별자
     * @return ResponseEntity
     */
    @PostMapping("{question-id}/adopt-answer/{answer-id}")
    public ResponseEntity<?> adoptAnswerToQuestion(@PathVariable("question-id") long questionId, @PathVariable("answer-id") long answerId) {

        //답변 채택시 update 된 답변의 정보만 response 요청
        Member member = loginMemberFindByToken();
        Answer answer = answerService.findAnswer(answerId);
        questionService.adoptAnswer(questionId, answer, member);
        Answer adoptedAnswer = answerService.findAnswer(answerId);
        AnswerInfoResponseDto response = answerMapper.answerToAnswerInfoResponse(adoptedAnswer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //로그인된 사용자 확인
    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }

    //게시글 전체조회 검색&필터기능
    @GetMapping("/search")
    public ResponseEntity<?> searchQuestion(@RequestParam(value = "title" , required = false) String title,
                                            @RequestParam(value = "filter", required = false) String filter,
                                            @RequestParam int page, @RequestParam int size) {

        // createdAt , viewCount , voteCount
        if(filter == null) filter = "questionId";
        //필터기능을 메서드로 만들고 -> 검색내용이 있으면 필터기능에 검색값 넣고 리턴 , 검색값 없으면 전체 리턴

        if(title == null) {
            //여기가 검색안했을때
            Page<Question> pageQuestions = questionService.searchQuestion(filter, page, size);
            List<Question> questions = pageQuestions.getContent();
            List<QuestionDto.ListPageResponse> responses = questionMapper.questionToQuestionListPageResponses(questions);

            return new ResponseEntity<>(new MultiResponseDto<>(responses, pageQuestions), HttpStatus.OK);
        }else {
            //여기가 제목 검색값 있을때
            Page<Question> pageQuestions = questionService.searchQuestion(title, filter, page, size);
            List<Question> questions = pageQuestions.getContent();
            List<QuestionDto.ListPageResponse> responses = questionMapper.questionToQuestionListPageResponses(questions);

            return new ResponseEntity<>(new MultiResponseDto<>(responses, pageQuestions), HttpStatus.OK);
        }

    }

}

