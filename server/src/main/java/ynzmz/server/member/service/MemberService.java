package ynzmz.server.member.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.global.error.exception.BusinessLogicException;
import ynzmz.server.global.error.exception.ExceptionCode;
import ynzmz.server.member.dto.MailDto;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.mapper.MemberMapper;
import ynzmz.server.member.repository.MemberRepository;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.recomment.qna.entity.QnaReComment;
import ynzmz.server.security.auths.userdetails.MemberDetailsService;
import ynzmz.server.security.auths.utils.CustomAuthorityUtils;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.qna.dto.LoginUserAnswerVoteResponseDto;
import ynzmz.server.vote.qna.dto.LoginUserQnaCommentVoteResponseDto;
import ynzmz.server.vote.qna.dto.LoginUserQnaReCommentVoteResponseDto;
import ynzmz.server.vote.qna.entity.QnaVote;
import ynzmz.server.vote.review.lecture.dto.LoginUserLectureReviewCommentVoteResponseDto;
import ynzmz.server.vote.review.lecture.dto.LoginUserLectureReviewVoteResponseDto;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ApplicationEventPublisher publisher;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private AuthenticationManager authenticationManager;
    private MemberDetailsService memberDetailsService;
    @Autowired
    private JavaMailSender javaMailSender;


//    public MemberService (MemberRepository memberRepository,
//                          MemberMapper memberMapper, ApplicationEventPublisher publisher,
//                          PasswordEncoder passwordEncoder,
//                          CustomAuthorityUtils authorityUtils){
//        this.memberRepository = memberRepository;
//        this.memberMapper = memberMapper;
//        this.publisher = publisher;
//        this.passwordEncoder = passwordEncoder;
//        this.authorityUtils = authorityUtils;
//    }

    @Transactional
    public Member createMember(Member member){
        verifyExistsEmail(member.getEmail());

        verifyExistsDisplayName(member.getDisplayName());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        log.info("Encrypted password: {}", encryptedPassword);
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        return memberRepository.save(member);
    }

    public Member updateMember(Member member){
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getPhoneNumber()).ifPresent(findMember::setPhoneNumber);
        Optional.ofNullable(member.getDisplayName()).ifPresent(findMember::setDisplayName);
//        Optional.ofNullable(findMember.getPassword()).ifPresent(password-> findMember.setPassword(passwordEncoding(password)));
        Optional.ofNullable(member.getIconImageUrl()).ifPresent(findMember::setIconImageUrl);



//        List<String> roles = authorityUtils.createRoles(findMember.getEmail());
//        findMember.setRoles(roles);
        return memberRepository.save(findMember);
    }


    public Page<Member> findMembers(int page, int size){
        return memberRepository.findAll(PageRequest.of(page,size,
                Sort.by("memberId").descending()));
    }

    public Member findMemberById(long memberId){
        Optional<Member> foundMember = memberRepository.findById(memberId);
        return foundMember.orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public Member findMemberByEmail(String email){
        Optional<Member> foundMember = memberRepository.findByEmail(email);
        return foundMember.orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public Member findMemberByUsername(String username){
        Optional<Member> foundMember = memberRepository.findByUsername(username);
        return foundMember.orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    public Member findVerifiedMember(long memberId){
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    public void verifyExistsEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }



    public void verifyExistsDisplayName(String displayName){
        Optional<Member> member = memberRepository.findByDisplayName(displayName);
        if(member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.DISPLAY_NAME_EXISTS);
        }
    }

    public void memberValidation(Member loginMember, long memberId) {
        if (loginMember.getMemberId() != memberId) throw new BusinessLogicException(ExceptionCode.THIS_MEMBER_NOT_PERMISSION);
    }

//    //해당 게시글에서 게시글&답변에 추천여부 확인
    public MemberDto.VoteInfo findQnaVoteStatusByLoginUser(Member member, Question question) {
        MemberDto.VoteInfo loginMemberVoteInfo = new MemberDto.VoteInfo();

        loginMemberVoteInfo.setMemberId( member.getMemberId() );
        loginMemberVoteInfo.setEmail( member.getEmail() );
        loginMemberVoteInfo.setQuestionId( question.getQuestionId() );

        List<QnaVote> qnaVotes = member.getQnaVotes();
        List<QnaComment> questionComments = question.getComments();
        List<Answer> answers = question.getAnswers();

        ArrayList<LoginUserAnswerVoteResponseDto> loginUserAnswerVoteResponseDtos = new ArrayList<>();
        ArrayList<LoginUserQnaCommentVoteResponseDto> loginUserQnaCommentVoteResponseDtos = new ArrayList<>();
        ArrayList<LoginUserQnaReCommentVoteResponseDto> loginUserQnaReCommentVoteResponseDtos = new ArrayList<>();

        for (QnaVote qnaVote : qnaVotes) {
            switch (qnaVote.getTarget()) {
                case QUESTION:
                    if (Objects.equals(qnaVote.getQuestion().getQuestionId(), question.getQuestionId())) {
                        loginMemberVoteInfo.setQuestionvoteStatus(qnaVote.getVoteStatus());
                    }
                    break;
                case COMMENT:
                    QnaComment qnaComment = qnaVote.getQnaComment();
                    if (questionComments.contains(qnaComment)) {
                        LoginUserQnaCommentVoteResponseDto dto = new LoginUserQnaCommentVoteResponseDto();
                        dto.setQnaCommentVoteId(qnaComment.getQnaCommentId());
                        dto.setVoteStatus(qnaVote.getVoteStatus());
                        loginUserQnaCommentVoteResponseDtos.add(dto);
                    }
                    break;
                case RECOMMENT:
                    QnaReComment qnaReComment = qnaVote.getQnaReComment();
                    if (questionComments.stream().anyMatch(c -> c.getQnaReComments().contains(qnaReComment))) {
                        LoginUserQnaReCommentVoteResponseDto dto = new LoginUserQnaReCommentVoteResponseDto();
                        dto.setQnaReCommentVoteId(qnaReComment.getQnaReCommentId());
                        dto.setVoteStatus(qnaVote.getVoteStatus());
                        loginUserQnaReCommentVoteResponseDtos.add(dto);
                    }
                    break;
                case ANSWER:
                    Answer answer = qnaVote.getAnswer();
                    if (answers.contains(answer)) {
                        LoginUserAnswerVoteResponseDto dto = new LoginUserAnswerVoteResponseDto();
                        dto.setAnswerId(answer.getAnswerId());
                        dto.setVoteStatus(qnaVote.getVoteStatus());
                        loginUserAnswerVoteResponseDtos.add(dto);
                    }
                    break;
            }
        }
        //로그인 유저 답변글 추천 상태값
        for (Answer answer : answers) {
            for (QnaVote qnaVote : qnaVotes) {
                if (qnaVote.getTarget() == Vote.Target.ANSWER) {
                    if (Objects.equals(answer.getAnswerId(), qnaVote.getAnswer().getAnswerId())) {
                        LoginUserAnswerVoteResponseDto loginUserAnswerVoteResponseDto = new LoginUserAnswerVoteResponseDto();
                        loginUserAnswerVoteResponseDto.setAnswerId(qnaVote.getAnswer().getAnswerId());
                        loginUserAnswerVoteResponseDto.setVoteStatus(qnaVote.getVoteStatus());
                        loginUserAnswerVoteResponseDtos.add(loginUserAnswerVoteResponseDto);
                    }
                }
            }
        }
        //로그인 유저 답변글 댓글 추천 상태값
        for (Answer answer : answers) {
            for (QnaComment qnaComment : answer.getComments()) {
                for (QnaVote qnaVote : qnaVotes) {
                    if (qnaVote.getTarget() == Vote.Target.COMMENT) {
                        if (Objects.equals(qnaComment.getQnaCommentId(), qnaVote.getQnaComment().getQnaCommentId())) {
                            LoginUserQnaCommentVoteResponseDto loginUserQnaCommentVoteResponseDto = new LoginUserQnaCommentVoteResponseDto();
                            loginUserQnaCommentVoteResponseDto.setQnaCommentVoteId(qnaVote.getQnaComment().getQnaCommentId());
                            loginUserQnaCommentVoteResponseDto.setVoteStatus(qnaVote.getVoteStatus());
                            loginUserQnaCommentVoteResponseDtos.add(loginUserQnaCommentVoteResponseDto);
                        }
                    }
                }
            }
        }
        //로그인 유저 답변글 대댓글 추천 상태값
        for (Answer answer : answers) {
            for (QnaComment qnaComment : answer.getComments()) {
                for (QnaReComment qnaReComment : qnaComment.getQnaReComments()) {
                    for (QnaVote qnaVote : qnaVotes) {
                        if (qnaVote.getTarget() == Vote.Target.RECOMMENT) {
                            if (Objects.equals(qnaReComment.getQnaReCommentId(), qnaVote.getQnaReComment().getQnaReCommentId())) {
                                LoginUserQnaReCommentVoteResponseDto loginUserQnaReCommentVoteResponseDto = new LoginUserQnaReCommentVoteResponseDto();
                                loginUserQnaReCommentVoteResponseDto.setQnaReCommentVoteId(qnaVote.getQnaReComment().getQnaReCommentId());
                                loginUserQnaReCommentVoteResponseDto.setVoteStatus(qnaVote.getVoteStatus());
                                loginUserQnaReCommentVoteResponseDtos.add(loginUserQnaReCommentVoteResponseDto);
                            }
                        }
                    }
                }
            }
        }

        loginMemberVoteInfo.setAnswerVoteStatus(loginUserAnswerVoteResponseDtos);
        loginMemberVoteInfo.setQnaCommentVoteStatus(loginUserQnaCommentVoteResponseDtos);
        loginMemberVoteInfo.setQnaReCommentVoteStatus(loginUserQnaReCommentVoteResponseDtos);

        return loginMemberVoteInfo;
    }

    //강의리뷰추천
    public MemberDto.LoginUserLectureReviewVoteInfo findLectureReviewVoteStatusByLoginUser(Member member, LectureReview lectureReview) {
        MemberDto.LoginUserLectureReviewVoteInfo loginUserLectureReviewVoteInfo = new MemberDto.LoginUserLectureReviewVoteInfo();

        loginUserLectureReviewVoteInfo.setMemberId(member.getMemberId());
        loginUserLectureReviewVoteInfo.setUsername(member.getUsername());

        ArrayList<LoginUserLectureReviewCommentVoteResponseDto> loginUserLectureReviewCommentVoteResponseDtos = new ArrayList<>();
        List<ReviewVote> reviewVotes = member.getReviewVotes();
        List<LectureReviewComment> lectureReviewComments = lectureReview.getComments();

        for (ReviewVote reviewVote : reviewVotes) {
            if (reviewVote.getTarget() == Vote.Target.REVIEW) {
                if (Objects.equals(reviewVote.getLectureReview().getLectureReviewId(), lectureReview.getLectureReviewId())) {
                    LoginUserLectureReviewVoteResponseDto loginUserLectureReviewVoteResponseDto = new LoginUserLectureReviewVoteResponseDto();
                    loginUserLectureReviewVoteResponseDto.setLectureReviewId(reviewVote.getReviewVoteId());
                    loginUserLectureReviewVoteResponseDto.setVoteStatus(reviewVote.getStatus());
                    loginUserLectureReviewVoteInfo.setLectureReviewVoteStatus(loginUserLectureReviewVoteResponseDto);
                    break;
                }
            } else if (reviewVote.getTarget() == Vote.Target.COMMENT) {
                LectureReviewComment lectureReviewComment = reviewVote.getLectureReviewComment();
                if(lectureReviewComments.contains(lectureReviewComment)) {
                    LoginUserLectureReviewCommentVoteResponseDto loginUserLectureReviewCommentVoteResponseDto = new LoginUserLectureReviewCommentVoteResponseDto();
                    loginUserLectureReviewCommentVoteResponseDto.setLectureReviewCommentId(reviewVote.getLectureReviewComment().getLectureReviewCommentId());
                    loginUserLectureReviewCommentVoteResponseDto.setVoteStatus(reviewVote.getStatus());
                    loginUserLectureReviewCommentVoteResponseDtos.add(loginUserLectureReviewCommentVoteResponseDto);
                }
            }
        }
            loginUserLectureReviewVoteInfo.setCommentVoteStatus(loginUserLectureReviewCommentVoteResponseDtos);
            return loginUserLectureReviewVoteInfo;
    }

    //비밀번호변경
    @Transactional
    public Member changePassword(long memberId, MemberDto.ChangePassword changePassword) {
        Member member = findVerifiedMember(memberId);
        String nowPassword = changePassword.getNowPassword();
        String newPassword = changePassword.getNewPassword();

        if (!passwordEncoder.matches(nowPassword, member.getPassword())) {
            throw new BusinessLogicException(ExceptionCode.PASSWORD_NOT_MATCHED);
        }

        String encodeNewPassword = passwordEncoder.encode(newPassword);
        member.setPassword(encodeNewPassword);

        return memberRepository.save(member);
    }

    //비밀번호 찾기
    @Transactional
    public Member FindChangePassword(String email,MemberDto.FindChangePassword findPassword){
        Member member = findMemberByEmail(email);
        String newPassword = findPassword.getNewPassword();
        String encodeNewPassword = passwordEncoder.encode(newPassword);
        member.setPassword(encodeNewPassword);

        return memberRepository.save(member);
    }

    //메일 내용 생성. 임시비밀번호로 회원비밀번호 변경
    public MailDto creatMailAndChangePassword(String memberEmail){
        String str = getPassword();
        updatePassword(str,memberEmail);
        MailDto mailDto = new MailDto();
        mailDto.setAddress(memberEmail); //멤버의 이메일로 보낼 주소 설정
        mailDto.setTitle("안녕하세요. 나만의 일타강사 찾기! <일타> 임시 비밀번호 발급 안내 메일입니다.");
        mailDto.setMessage("안녕하세요. 나만의 일타강사 찾기! <일타> 임시 비밀번호 안내 관련 이메일입니다." + "회원님의 임시 비밀번호는 "
                + str + " 입니다." + "로그인 후 비밀번호를 변경해주세요.");
        return mailDto;
    }

    //랜덤으로 비밀번호 구문 만들기
    public String getPassword(){
        char[] charSet = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '!','@','#','$','%','^','&','*','(',')','-','_','=','+',',','.','/','?'};
        String str = "";

        //문자 길이의 값을 랜덤으로 10개 뽑아서 만듦.
        int idx = 0; //초기값 0으로 설정
        for (int i=0; i<10; i++){
            idx = (int) (charSet.length * Math.random()); //랜덤으로 charSet에서 뽑기.
            str += charSet[idx];
        }
        return str;
    }

    //임시비밀번호로 업데이트
    public void updatePassword(String str, String email){
        String memberPassword = passwordEncoder.encode(str);
        Optional<Member> OptionalMember = memberRepository.findByEmail(email);
        OptionalMember.ifPresent(member -> memberRepository.updatePassword(member.getMemberId(), str));
        Long memberId = OptionalMember.get().getMemberId();
        updatePassword(memberId, memberPassword);
    }


    //메일보내기
    public void mailSend(MailDto mailDto){
        System.out.println("전송이 완료되었습니다.");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        try {
            message.setFrom(String.valueOf(new InternetAddress("likedubu@naver.com","일타")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        message.setReplyTo("likedubu@naver.com");
        System.out.println("message" + message);
        if(javaMailSender !=null) {
            javaMailSender.send(message);
        } else {
            throw new RuntimeException("메일전송에 실패했습니다.");
        }
    }

    public void updatePassword(Long memberId, String memberPassword){
        memberRepository.updatePassword(memberId,memberPassword);
    }





}





