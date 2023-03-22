package ynzmz.server.member.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.repository.MemberRepository;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.security.auths.userdetails.MemberDetailsService;
import ynzmz.server.security.auths.utils.CustomAuthorityUtils;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.qna.dto.LoginUserAnswerVoteResponseDto;
import ynzmz.server.vote.qna.entity.QnaVote;
import ynzmz.server.vote.review.lecture.dto.LoginUserLectureReviewCommentVoteResponseDto;
import ynzmz.server.vote.review.lecture.dto.LoginUserLectureReviewVoteResponseDto;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher publisher;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private AuthenticationManager authenticationManager;
    private MemberDetailsService memberDetailsService;


    public MemberService (MemberRepository memberRepository,
                          ApplicationEventPublisher publisher,
                          PasswordEncoder passwordEncoder,
                          CustomAuthorityUtils authorityUtils){
        this.memberRepository = memberRepository;
        this.publisher = publisher;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

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

        Optional.ofNullable(findMember.getPhoneNumber()).ifPresent(findMember::setPhoneNumber);
        Optional.ofNullable(findMember.getDisplayName()).ifPresent(findMember::setDisplayName);
        Optional.ofNullable(findMember.getPassword()).ifPresent(password-> findMember.setPassword(passwordEncoding(password)));
        Optional.ofNullable(findMember.getIconImageUrl()).ifPresent(findMember::setIconImageUrl);

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


//    public boolean deleteMember(long memberId){
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Member findMember = findVerifiedMember(memberId);
//        if(Objects.equals(findMember.getEmail(),username)){
//            memberRepository.deleteById(memberId);
//        } else {
//            throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
//        }
//        Optional<Member> deleteMember = memberRepository.findById(memberId);
//        return deleteMember.isEmpty();

//    }

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
        if (loginMember.getMemberId() != memberId) throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER_STATUS);
    }

    //해당 게시글에서 게시글&답변에 추천여부 확인
    public MemberDto.VoteInfo setMemberVoteStatus(Member member, Question question) {
        MemberDto.VoteInfo loginMemberVoteInfo = new MemberDto.VoteInfo();

        loginMemberVoteInfo.setMemberId(member.getMemberId() );
        loginMemberVoteInfo.setEmail(member.getEmail() );
        loginMemberVoteInfo.setQuestionId( question.getQuestionId() );

        ArrayList<LoginUserAnswerVoteResponseDto> loginUserAnswerVoteResponseDtos = new ArrayList<>();
        List<QnaVote> qnaVotes = member.getQnaVotes();
        List<QnaVote> answerVotes = member.getQnaVotes();
        List<Answer> questionAnswers = question.getAnswers();

        for(QnaVote qnaVote : qnaVotes) {
            if(Objects.equals(qnaVote.getQuestion().getQuestionId(), question.getQuestionId())) {
                loginMemberVoteInfo.setQuestionvoteStatus(qnaVote.getVoteStatus());
                break;
            }
        }

//        for (Answer questionAnswer : questionAnswers) {
//            for (AnswerVote answerVote : answerVotes) {
//                if(Objects.equals(questionAnswer.getAnswerId(), answerVote.getAnswer().getAnswerId())){
//                    LoginUserAnswerVoteResponseDto loginUserAnswerVote = new LoginUserAnswerVoteResponseDto(answerVote.getAnswer().getAnswerId(), answerVote.getVoteStatus());
//                    loginUserAnswerVoteResponseDtos.add(loginUserAnswerVote);
//                }
//            }
//        }
        loginMemberVoteInfo.setAnswerVoteStatus(loginUserAnswerVoteResponseDtos);
        return loginMemberVoteInfo;
    }

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
            }
        }

        for (LectureReviewComment reviewComment : lectureReviewComments) {
            for (ReviewVote reviewVote : reviewVotes) {
                if (reviewVote.getTarget() == Vote.Target.COMMENT) {
                    if (Objects.equals(reviewComment.getLectureReviewCommentId(), reviewVote.getLectureReviewComment().getLectureReviewCommentId())) {
                        LoginUserLectureReviewCommentVoteResponseDto loginUserLectureReviewCommentVoteResponseDto = new LoginUserLectureReviewCommentVoteResponseDto();
                        loginUserLectureReviewCommentVoteResponseDto.setLectureReviewCommentId(reviewVote.getLectureReviewComment().getLectureReviewCommentId());
                        loginUserLectureReviewCommentVoteResponseDto.setVoteStatus(reviewVote.getStatus());
                        loginUserLectureReviewCommentVoteResponseDtos.add(loginUserLectureReviewCommentVoteResponseDto);
                    }
                }
            }
        }
            loginUserLectureReviewVoteInfo.setCommentVoteStatus(loginUserLectureReviewCommentVoteResponseDtos);
            return loginUserLectureReviewVoteInfo;
    }

    private String passwordEncoding(String password) {
        return passwordEncoder.encode(password);
    }


//    public void changePassword(long memberId, String nowPassword, String newPassword){
//        Member member = memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
//
//        if(passwordEncoder.matches(nowPassword,member.getPassword())){
//            if(nowPassword.equals(newPassword)){
//                throw new BusinessLogicException(ExceptionCode.SAME_PASSWORD);
//            }
//            String encryptedPassword = passwordEncoder.encode(newPassword);
//            member.setPassword(encryptedPassword);
//            memberRepository.save(member);
//        } else {
//            throw new BusinessLogicException(ExceptionCode.INVALID_NOW_PASSWORD);
//        }

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

}

//    public void changePassword(MemberDto.ChangePasswordRequest changePasswordRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(SecurityContextHolder.getContext().getAuthentication().getName(), changePasswordRequest.getNowPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        MemberDetailsService.MemberDetails member = (MemberDetailsService.MemberDetails)  memberDetailsService.loadUserByUsername(authentication.getName());
//
//        if(!passwordEncoder.matches(changePasswordRequest.getNowPassword(), member.getPassword())) {
//            throw new RuntimeException("현재 비밀번호가 일치하지 않습니다.");
//        }
//
//        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())){
//            throw new RuntimeException("새로운 비밀번호와 새로운 비밀번호 확인이 일치하지 않습니다.");
//        }
//
//        String encodedPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
//        member.setPassword(encodedPassword);
//        memberDetailsService.updateMemberPassword(member);
//    }

//    public void updateMemberPassword(Long memberId, String newPassword){
//        String thisPassword = passwordEncoder.encode(newPassword);
//        memberRepository.updateMemberPassword(memberId, thisPassword);
//    }



