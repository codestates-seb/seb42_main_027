package ynzmz.server.vote.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ynzmz.server.board.free.entity.Free;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.free.entity.FreeVote;
import ynzmz.server.vote.free.repository.FreeVoteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreeVoteService {

    private final FreeVoteRepository freeVoteRepository;

    public FreeVote freeVoteUp(Vote countTarget, FreeVote freeVote) {

        if(freeVote.getStatus().equals(Vote.Status.UP)){ //만약에 UP 이면
            freeVote.setStatus(Vote.Status.NONE); // UP 취소로 동작 (NONE 으로 변경)
            countTarget.setVoteCount(countTarget.getVoteCount() -1); // 게시글의 카운트수 -1
        } else if (freeVote.getStatus().equals(Vote.Status.NONE)) { // NONE 상태면
            freeVote.setStatus(Vote.Status.UP); // UP 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() +1); // 게시글 카운트 수 +1
        } else { //down 상태면
            freeVote.setStatus(Vote.Status.UP); // DOWN -> UP 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() +2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return freeVoteRepository.save(freeVote);
    }

    public FreeVote freeVoteDown(Vote countTarget, FreeVote freeVote) {

        if(freeVote.getStatus().equals(Vote.Status.DOWN)){ //만약에 DOWN 이면
            freeVote.setStatus(Vote.Status.NONE); // DOWN 취소로 동작 (NONE 으로 변경)
            countTarget.setVoteCount(countTarget.getVoteCount() +1); // 게시글의 카운트수 +1
        } else if (freeVote.getStatus().equals(Vote.Status.NONE)) { // NONE 상태면
            freeVote.setStatus(Vote.Status.DOWN); // DOWN 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() -1); // 게시글 카운트 수 +1
        } else { //down 상태면
            freeVote.setStatus(Vote.Status.DOWN); // UP -> DOWN 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() -2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return freeVoteRepository.save(freeVote);
    }

    public FreeVote findFreeVoteTargetFree(Free free, Member member){
        Optional<FreeVote> findFreePostVote = freeVoteRepository.findByFreeAndMemberAndTarget(free, member, Vote.Target.REVIEW);
        return findFreePostVote.orElseGet(()-> new FreeVote(free, member, Vote.Status.NONE, Vote.Target.REVIEW));
    }

    public FreeVote findFreeVoteTargetComment(FreeComment freeComment, Member member){
        Optional<FreeVote> findFreePostVote = freeVoteRepository.findByFreeCommentAndMemberAndTarget(freeComment, member, Vote.Target.COMMENT);
        return findFreePostVote.orElseGet(()-> new FreeVote(freeComment, member, Vote.Status.NONE, Vote.Target.COMMENT));
    }
}
