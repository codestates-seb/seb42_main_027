package ynzmz.server.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.comment.free.mapper.FreeCommentMapper;
import ynzmz.server.comment.free.repository.FreeCommentRepository;
import ynzmz.server.comment.free.service.FreeCommentService;
import ynzmz.server.comment.qna.dto.QnaCommentDto;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.comment.qna.mapper.QnaCommentMapper;
import ynzmz.server.comment.qna.service.QnaCommentService;
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.comment.review.lecture.mapper.LectureReviewCommentMapper;
import ynzmz.server.comment.review.lecture.service.LectureReviewCommentService;
import ynzmz.server.global.dto.MultiResponseDto;
import ynzmz.server.global.dto.SingleResponseDto;
import ynzmz.server.member.dto.MailDto;
import ynzmz.server.member.repository.MemberRepository;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.mapper.MemberMapper;
import ynzmz.server.recomment.free.dto.ReCommentDto;
import ynzmz.server.recomment.free.entity.FreeReComment;
import ynzmz.server.recomment.free.mapper.ReCommentMapper;
import ynzmz.server.recomment.free.service.ReCommentService;
import ynzmz.server.recomment.qna.dto.QnaReCommentDto;
import ynzmz.server.recomment.qna.entity.QnaReComment;
import ynzmz.server.recomment.qna.mapper.QnaReCommentMapper;
import ynzmz.server.recomment.qna.service.QnaReCommentService;


import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Setter
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final LectureReviewService lectureReviewService;
    private final LectureReviewMapper lectureReviewMapper;
    private final FreeService freeService;
    private final FreeMapper freeMapper;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final FreeCommentService freeCommentService;
    private final FreeCommentMapper freeCommentMapper;
    private final FreeCommentRepository freeCommentRepository;
    private final ReCommentService reCommentService;
    private final ReCommentMapper reCommentMapper;
    private final QnaCommentService qnaCommentService;
    private final QnaCommentMapper qnaCommentMapper;
    private final LectureReviewCommentService lectureReviewCommentService;
    private final LectureReviewCommentMapper lectureReviewCommentMapper;
    private final QnaReCommentService qnaReCommentService;
    private final QnaReCommentMapper qnaReCommentMapper;


    //회원가입
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

    //회원정보수정
    @PatchMapping("/{member-id}")
    public ResponseEntity<?> patchMember(@PathVariable("member-id") @Positive long memberId, @RequestBody @Valid MemberDto.Patch requestBody) {
        Member member = memberMapper.memberPatchToMember(requestBody);
        member.setMemberId(memberId);

        Member updatedMember = memberService.updateMember(member);
        MemberDto.Response response=  memberMapper.memberToMemberResponse(updatedMember);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //비밀번호변경
    @PatchMapping("/{member-id}/changepasswords")
    public ResponseEntity<?> changePassword(@PathVariable("member-id") long memberId, @RequestBody MemberDto.ChangePassword changePassword) {
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("패스워드와 패스워드 확인이 일치하지 않습니다.");
        }

        log.info(changePassword.getNowPassword());
        Member member = memberService.changePassword(memberId, changePassword);
        MemberDto.Response response = memberMapper.memberToMemberResponse(member);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //회원조회(email로)
    @GetMapping("/{email}")
    public ResponseEntity<?> getMember(@PathVariable("email") @Positive String email){
        Member findMember = memberService.findMemberByEmail(email);
        MemberDto.Response response = memberMapper.memberToMemberResponse(findMember);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //회원리스트 조회
    @GetMapping
    public ResponseEntity<?> getMembers(@Positive @RequestParam int page,
                                        @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page-1, size);
        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(memberMapper.memberToMemberResponses(members),pageMembers),HttpStatus.OK);
    }

    //회원삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member-id") @Positive long memberId){
//        boolean deleteStatus = memberService.deleteMember(memberId);
//        return deleteStatus ? new ResponseEntity<>("삭제완료", HttpStatus.OK) : new ResponseEntity<>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //이메일 찾기
    @PostMapping("/findemails")
    public ResponseEntity<?> findEmailByUsernameAndPhoneNumber (@RequestBody MemberDto.FindEmail findEmail){
        String username = findEmail.getUsername();
        String phoneNumber = findEmail.getPhoneNumber();

        Optional<Member> member = memberRepository.findByUsernameAndPhoneNumber(username,phoneNumber);
        if(member.isPresent()){
            String email = member.get().getEmail();
            return ResponseEntity.ok(email);
        } else {
            return ResponseEntity.badRequest().body("이메일이 존재하지 않습니다.");
        }
    }

    //비밀번호 찾기 - 일치하는 정보
    @PostMapping("/finds/findpasswords")
    public ResponseEntity<?> fndPassword (@RequestBody MemberDto.FindPassword findPassword) {
        String email = findPassword.getEmail();
        String username = findPassword.getUsername();
        String phoneNumber = findPassword.getPhoneNumber();

        Optional<Member> member = memberRepository.findByUsernameAndEmailAndPhoneNumber(username, email, phoneNumber);

        if (member.isPresent()) {
            Member foundMember = member.get();
            if (!findPassword.getEmail().equals(foundMember.getEmail()) ||
                    !findPassword.getUsername().equals(foundMember.getUsername()) ||
                    !findPassword.getPhoneNumber().equals(foundMember.getPhoneNumber())) {
                return ResponseEntity.badRequest().body("일치하는 회원정보를 찾을 수 없습니다.");
            }
            return ResponseEntity.ok("");
        }
        return ResponseEntity.badRequest().body("일치하는 회원정보를 찾을 수 없습니다.");

    }

    //비밀번호변경
    @PatchMapping("/{email}/finds/changepasswords")
    public ResponseEntity<?> FindChangePassword( @PathVariable("email") String email, @RequestBody MemberDto.FindChangePassword findPassword) {
        if (!findPassword.getNewPassword().equals(findPassword.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("패스워드와 패스워드 확인이 일치하지 않습니다.");
        }
        Member member = memberService.FindChangePassword(email,findPassword);
        MemberDto.Response response = memberMapper.memberToMemberResponse(member);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //임시비밀번호발급
    @Transactional
    @PostMapping("/sendEmail")
    public String SendEmail(@RequestParam("memberEmail") String memberEmail){
        MailDto mailDto = memberService.creatMailAndChangePassword(memberEmail);
        memberService.mailSend(mailDto);
        return "redirect:/auth/login";
    }




//-------------------------------------마이페이지-------------------------------------------//

    //내가쓴 질문글조회
    @GetMapping("/{member-id}/questions")
    public ResponseEntity<?> getMyQuestions(@PathVariable("member-id")
                                                long memberId) {
        List<Question> myQuestions = questionService.findQuestionsByMemberId(memberId);
        List<QuestionDto.ListPageResponse> responses = questionMapper.questionToQuestionListPageResponses(myQuestions); //통일

        return new ResponseEntity<>(new SingleResponseDto<>(responses),HttpStatus.OK);
    }

//    내가쓴 강의리뷰조회
    @GetMapping("/{member-id}/reviews")
    public ResponseEntity<?> getMyReviews(@PathVariable("member-id")
                                                  long memberId){
        List<LectureReview> myLectureReviews = lectureReviewService.findLectureReviewsByMemberId(memberId);
        List<LectureReviewDto.InfoResponse> responses = lectureReviewMapper.lectureReviewToLectureReviewInfoResponses(myLectureReviews);
        return new ResponseEntity<>(new SingleResponseDto<>(responses),HttpStatus.OK);
    }

    //내가쓴 자유게시판글 조회
    @GetMapping("/{member-id}/frees")
    public ResponseEntity<?> getMyFrees(@PathVariable("member-id")
                                            long memberId){

        List<Free> myFrees = freeService.findFreesByMemberId(memberId);
        List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(myFrees);
        return new ResponseEntity<>(new SingleResponseDto<>(responses),HttpStatus.OK);
    }

    //내가쓴 답변글 조회
    @GetMapping("/{member-id}/answers")
    public ResponseEntity<?> getMyAnswers(@PathVariable("member-id")
                                          long memberId){
        List<Answer> myAnswers = answerService.findAnswersByMemberId(memberId);
        List<AnswerDto.SimpleInfoResponse> responses = answerMapper.answersToAnswerInfoResponses(myAnswers);
        return new ResponseEntity<>(new SingleResponseDto<>(responses),HttpStatus.OK);
    }

    //내가쓴 자유게시판댓글 조회
    @GetMapping("/{member-id}/comments/frees")
    public ResponseEntity<?> getMyFreeComments(@PathVariable("member-id")
                                                   long memberId){
        List<FreeComment> myFreeComments = freeCommentService.findFreeCommentsByMemberId(memberId);
        List<FreeCommentDto.SimpleResponse> responses = freeCommentMapper.freeCommentToFreeCommentsSimpleResponses(myFreeComments);
        return new ResponseEntity<>(new SingleResponseDto<>(responses),HttpStatus.OK);
    }

    //내가쓴 질문게시판댓글 조회
    @GetMapping("/{member-id}/comments/qnas")
    public ResponseEntity<?> getMyQnAComments(@PathVariable("member-id")
                                               long memberId){
        List<QnaComment> myQnAComments = qnaCommentService.findQnaCommentByMemberId(memberId);
        List<QnaCommentDto.SimpleResponse> responses = qnaCommentMapper.qnaCommentsToQnaCommentSimpleResponses(myQnAComments);
        return new ResponseEntity<>(new SingleResponseDto<>(responses),HttpStatus.OK);
    }

    //내가쓴 강의리뷰댓글 조회
    @GetMapping("/{member-id}/comments/reviews")
    public ResponseEntity<?> getMyReviewComments(@PathVariable("member-id")
                                                 long memberId){
        List<LectureReviewComment> myLectureReviewComments = lectureReviewCommentService.findLectureReviewCommentsByMemberId(memberId);
        List<LectureReviewCommentDto.SimpleResponse> responses = lectureReviewCommentMapper.lectureReviewCommentsToLectureReviewCommentSimpleResponses(myLectureReviewComments);
        return new ResponseEntity<>(new SingleResponseDto<>(responses),HttpStatus.OK);
    }

    //내가쓴 질문게시판 대댓글 조회
    @GetMapping("/{member-id}/recomments/qnas")
    public ResponseEntity<?> getMyQnaReComments(@PathVariable("member-id") @Valid
                                                long memberId){
        List<QnaReComment> myQnaReComments = qnaReCommentService.findQnaReCommentByMemberId(memberId);
        List<QnaReCommentDto.SimpleResponse> responses = qnaReCommentMapper.qnaReCommentToQnaReCommentSimpleResponse(myQnaReComments);
        return new ResponseEntity<>(new SingleResponseDto<>(responses),HttpStatus.OK);
    }

    //내가쓴 자유게시판 대댓글 조회
   @GetMapping("/{member-id}/recomments/frees")
   public ResponseEntity<?> getFreeReComments(@PathVariable("member-id") @Valid
                                                 long memberId){
        List<FreeReComment> myFreeReComments = reCommentService.findReCommentByMemberId(memberId);
//        List<ReCommentDto.SimpleResponse> responses = reCommentMapper.freeReCommentToFreeReCommentsSimpleResponses(myFreeReComments);
        List<ReCommentDto.SimpleResponse> responses = new ArrayList<>();
        for (FreeReComment freeReComment : myFreeReComments) {
               ReCommentDto.SimpleResponse response = new ReCommentDto.SimpleResponse();
               response.setFreeReCommentId(freeReComment.getFreeReCommentId());
               response.setContent(freeReComment.getContent());
               response.setCreatedAt(freeReComment.getCreatedAt());
               response.setModifiedAt(freeReComment.getModifiedAt());
               response.setVoteCount(freeReComment.getVoteCount());
               FreeComment freeComment = freeReComment.getFreeComment();

               if (freeComment != null) {
                   FreeCommentDto.SimpleResponse freeCommentResponse = new FreeCommentDto.SimpleResponse();
                   freeCommentResponse.setFreeCommentId(freeComment.getFreeCommentId());
                   freeCommentResponse.setContent(freeComment.getContent());
                   freeCommentResponse.setCreatedAt(freeComment.getCreatedAt());
                   freeCommentResponse.setModifiedAt(freeComment.getModifiedAt());
                   response.setFreeComment(freeCommentResponse);
               }

           responses.add(response);
       }

//        List<String> roles = authorityUtils.createRoles(member.getEmail());
//        member.setRoles(roles);

       for (FreeReComment freeReComment : myFreeReComments) {
           ReCommentDto.SimpleResponse response = new ReCommentDto.SimpleResponse();
           response.setFreeReCommentId(freeReComment.getFreeReCommentId());
           response.setContent(freeReComment.getContent());
           response.setCreatedAt(freeReComment.getCreatedAt());
           response.setModifiedAt(freeReComment.getModifiedAt());
           response.setVoteCount(freeReComment.getVoteCount());

           FreeComment freeComment = freeCommentRepository.findById(freeReComment.getFreeComment().getFreeCommentId()).orElse(null);

           if (freeComment != null) {
               FreeCommentDto.SimpleResponse freeCommentResponse = new FreeCommentDto.SimpleResponse();
               freeCommentResponse.setFreeCommentId(freeComment.getFreeCommentId());
               freeCommentResponse.setContent(freeComment.getContent());
               freeCommentResponse.setCreatedAt(freeComment.getCreatedAt());
               freeCommentResponse.setModifiedAt(freeComment.getModifiedAt());
               response.setFreeComment(freeCommentResponse);
           }
           responses.add(response);
       }
        return new ResponseEntity<>(new SingleResponseDto<>(responses), HttpStatus.OK);
   }






}
