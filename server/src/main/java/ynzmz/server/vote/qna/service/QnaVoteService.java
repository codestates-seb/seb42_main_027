package ynzmz.server.vote.qna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.board.qna.question.service.QuestionService;
import ynzmz.server.recomment.qna.entity.QnaReComment;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.qna.entity.QnaVote;
import ynzmz.server.vote.qna.repository.QnaVoteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaVoteService {

    private final QnaVoteRepository qnaVoteRepository;

    private final QuestionService questionService;

    private final MemberService memberService;

    public QnaVote qnaVoteUp(Vote target, QnaVote qnaVote) {

        //로그인된사람, 상태값이 있는상태
        if(qnaVote.getVoteStatus().equals(Vote.Status.UP)){ //만약에 UP 이면
            qnaVote.setVoteStatus(Vote.Status.NONE); // UP 취소로 동작 (NONE 으로 변경)
            target.setVoteCount(target.getVoteCount() -1); // 게시글의 카운트수 -1
        } else if (qnaVote.getVoteStatus().equals(Vote.Status.NONE)) { // NONE 상태면
            qnaVote.setVoteStatus(Vote.Status.UP); // UP 으로 변경
            target.setVoteCount(target.getVoteCount() +1); // 게시글 카운트 수 +1
        } else { //down 상태면
            qnaVote.setVoteStatus(Vote.Status.UP); // DOWN -> UP 으로 변경
            target.setVoteCount(target.getVoteCount() +2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return qnaVoteRepository.save(qnaVote);
    }


    public QnaVote qnaVoteDown(Vote target, QnaVote qnaVote) {

        if(qnaVote.getVoteStatus().equals(Vote.Status.DOWN)){ //만약에 DOWN 이면
            qnaVote.setVoteStatus(Vote.Status.NONE); // DOWN 취소로 동작 (NONE 으로 변경)
            target.setVoteCount(target.getVoteCount() +1); // 게시글의 카운트수 +1
        } else if (qnaVote.getVoteStatus().equals(Vote.Status.NONE)) { // NONE 상태면
            qnaVote.setVoteStatus(Vote.Status.DOWN); // DOWN 으로 변경
            target.setVoteCount(target.getVoteCount() -1); // 게시글 카운트 수 +1
        } else { //down 상태면
            qnaVote.setVoteStatus(Vote.Status.DOWN); // UP -> DOWN 으로 변경
            target.setVoteCount(target.getVoteCount() -2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return qnaVoteRepository.save(qnaVote);
    }

    public QnaVote findQnaVoteTargetQuestion(Question question, Member member){
        Optional<QnaVote> findQuestionVote = qnaVoteRepository.findByQuestionAndMemberAndTarget(question, member, Vote.Target.QUESTION);
        return findQuestionVote.orElseGet(()-> new QnaVote(question, member, Vote.Status.NONE, Vote.Target.QUESTION));
    }

    public QnaVote findQnaVoteTargetAnswer(Answer answer, Member member){
        Optional<QnaVote> findQuestionVote = qnaVoteRepository.findByAnswerAndMemberAndTarget(answer, member, Vote.Target.ANSWER);
        return findQuestionVote.orElseGet(()-> new QnaVote(answer, member, Vote.Status.NONE, Vote.Target.QUESTION));
    }

    public QnaVote findQnaVoteTargetComment(QnaComment qnaComment, Member member){
        Optional<QnaVote> findQuestionVote = qnaVoteRepository.findByQnaCommentAndMemberAndTarget(qnaComment, member, Vote.Target.QUESTION);
        return findQuestionVote.orElseGet(()-> new QnaVote(qnaComment, member, Vote.Status.NONE, Vote.Target.COMMENT));
    }

    public QnaVote findQnaVoteTargetReComment(QnaReComment qnaReComment, Member member){
        Optional<QnaVote> findQuestionVote = qnaVoteRepository.findByQnaReCommentAndMemberAndTarget(qnaReComment, member, Vote.Target.QUESTION);
        return findQuestionVote.orElseGet(()-> new QnaVote(qnaReComment, member, Vote.Status.NONE, Vote.Target.RECOMMENT));
    }

}
