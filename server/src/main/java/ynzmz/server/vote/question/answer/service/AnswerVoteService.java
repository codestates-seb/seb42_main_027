package ynzmz.server.vote.question.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.answer.service.AnswerService;
import ynzmz.server.vote.question.answer.entity.AnswerVote;
import ynzmz.server.vote.question.answer.repository.AnswerVoteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerVoteService {

    private final AnswerVoteRepository answerVoteRepository;
    private final AnswerService answerService;
    private final MemberService memberService;

    public AnswerVote answerVoteUp(long answerId) {
        String loginMemberEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        Answer answer = answerService.findAnswer(answerId);
        Member member = memberService.findMemberByEmail(loginMemberEmail);

        AnswerVote answerVote = findAnswerVote(answer, member); // 추천이 처음이면 만들고, 있으면 변경
        if(answerVote.getVoteStatus().equals(AnswerVote.VoteStatus.UP)) {
            answerVote.setVoteStatus(AnswerVote.VoteStatus.NONE);
            answer.setVoteCount(answer.getVoteCount() - 1);
        } else if(answerVote.getVoteStatus().equals(AnswerVote.VoteStatus.NONE)){
            answerVote.setVoteStatus(AnswerVote.VoteStatus.UP);
            answer.setVoteCount(answer.getVoteCount() + 1);
        } else {
            answerVote.setVoteStatus(AnswerVote.VoteStatus.UP);
            answer.setVoteCount(answer.getVoteCount() + 2);
        }
        return answerVoteRepository.save(answerVote);
    }

    public AnswerVote answerVoteDown(long answerId) {
        String loginMemberEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        Answer answer = answerService.findAnswer(answerId);
        Member member = memberService.findMemberByEmail(loginMemberEmail);

        AnswerVote answerVote = findAnswerVote(answer, member);
        if(answerVote.getVoteStatus().equals(AnswerVote.VoteStatus.DOWN)) {
            answerVote.setVoteStatus(AnswerVote.VoteStatus.NONE);
            answer.setVoteCount(answer.getVoteCount() + 1);
        } else if(answerVote.getVoteStatus().equals(AnswerVote.VoteStatus.NONE)){
            answerVote.setVoteStatus(AnswerVote.VoteStatus.DOWN);
            answer.setVoteCount(answer.getVoteCount() - 1);
        } else {
            answerVote.setVoteStatus(AnswerVote.VoteStatus.DOWN);
            answer.setVoteCount(answer.getVoteCount() - 2);
        }
        return answerVoteRepository.save(answerVote);
    }

    public AnswerVote findAnswerVote(Answer answer,Member member) { // 추천이 처음이면 만들고, 있으면 변경
        Optional<AnswerVote> findAnswerVote = answerVoteRepository.findByAnswerAndMember(answer, member);
        return findAnswerVote.orElseGet(() -> new AnswerVote(answer, member, AnswerVote.VoteStatus.NONE));
        //없으면 생성,있으면 반환
    }
}