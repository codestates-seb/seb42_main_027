package ynzmz.server.vote.lecturereview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.VoteCount;
import ynzmz.server.vote.lecturereview.entity.LectureReviewVote;
import ynzmz.server.vote.lecturereview.repository.LectureReviewVoteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureReviewVoteService {

    private final LectureReviewVoteRepository lectureReviewVoteRepository;

    public LectureReviewVote lectureReviewVoteUp(VoteCount countTarget, LectureReviewVote lectureReviewVote) {

        if(lectureReviewVote.getVoteStatus().equals(LectureReviewVote.VoteStatus.UP)){ //만약에 UP 이면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.NONE); // UP 취소로 동작 (NONE 으로 변경)
            countTarget.setVoteCount(countTarget.getVoteCount() -1); // 게시글의 카운트수 -1
        } else if (lectureReviewVote.getVoteStatus().equals(LectureReviewVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.UP); // UP 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() +1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.UP); // DOWN -> UP 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() +2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewVoteRepository.save(lectureReviewVote);
    }

    public LectureReviewVote lectureReviewVoteDown(VoteCount countTarget, LectureReviewVote lectureReviewVote) {

        if(lectureReviewVote.getVoteStatus().equals(LectureReviewVote.VoteStatus.DOWN)){ //만약에 DOWN 이면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.NONE); // DOWN 취소로 동작 (NONE 으로 변경)
            countTarget.setVoteCount(countTarget.getVoteCount() +1); // 게시글의 카운트수 +1
        } else if (lectureReviewVote.getVoteStatus().equals(LectureReviewVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.DOWN); // DOWN 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() -1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.DOWN); // UP -> DOWN 으로 변경
            countTarget.setVoteCount(countTarget.getVoteCount() -2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewVoteRepository.save(lectureReviewVote);
    }

    public LectureReviewVote findLectureReviewVoteTargetReview(LectureReview lectureReview, Member member){
        Optional<LectureReviewVote> findLectureReviewPostVote = lectureReviewVoteRepository.findByLectureReviewAndMemberAndTarget(lectureReview, member, LectureReviewVote.Target.REVIEW);
        return findLectureReviewPostVote.orElseGet(()-> new LectureReviewVote(lectureReview, member, LectureReviewVote.VoteStatus.NONE, LectureReviewVote.Target.REVIEW));
    }

    public LectureReviewVote findLectureReviewVoteTargetComment(LectureReviewComment lectureReviewComment, Member member){
        Optional<LectureReviewVote> findLectureReviewPostVote = lectureReviewVoteRepository.findByLectureReviewCommentAndMemberAndTarget(lectureReviewComment, member, LectureReviewVote.Target.COMMENT);
        return findLectureReviewPostVote.orElseGet(()-> new LectureReviewVote(lectureReviewComment, member, LectureReviewVote.VoteStatus.NONE, LectureReviewVote.Target.COMMENT));
    }
}
