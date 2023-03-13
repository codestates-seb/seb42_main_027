package ynzmz.server.vote.lecturereview.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.lecturereview.comment.entity.LectureReviewCommentVote;
import ynzmz.server.vote.lecturereview.comment.repository.LectureReviewCommentVoteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureReviewCommentVoteService {

    private final LectureReviewCommentVoteRepository lectureReviewCommentVoteRepository;

    public LectureReviewCommentVote lectureReviewCommentVoteUp(LectureReviewComment lectureReviewComment, Member member) {

        LectureReviewCommentVote lectureReviewCommentVote = findLectureReviewPostVote(lectureReviewComment, member);// 현재 상태값 불러오기

        if(lectureReviewCommentVote.getVoteStatus().equals(LectureReviewCommentVote.VoteStatus.UP)){ //만약에 UP 이면
            lectureReviewCommentVote.setVoteStatus(LectureReviewCommentVote.VoteStatus.NONE); // UP 취소로 동작 (NONE 으로 변경)
            lectureReviewComment.setVoteCount(lectureReviewComment.getVoteCount() -1); // 게시글의 카운트수 -1
        } else if (lectureReviewCommentVote.getVoteStatus().equals(LectureReviewCommentVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewCommentVote.setVoteStatus(LectureReviewCommentVote.VoteStatus.UP); // UP 으로 변경
            lectureReviewComment.setVoteCount(lectureReviewComment.getVoteCount() +1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewCommentVote.setVoteStatus(LectureReviewCommentVote.VoteStatus.UP); // DOWN -> UP 으로 변경
            lectureReviewComment.setVoteCount(lectureReviewComment.getVoteCount() +2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewCommentVoteRepository.save(lectureReviewCommentVote);
    }

    public LectureReviewCommentVote lectureReviewCommentVoteDown(LectureReviewComment lectureReviewComment, Member member) {

        LectureReviewCommentVote lectureReviewCommentVote = findLectureReviewPostVote(lectureReviewComment, member);// 현재 상태값 불러오기

        if(lectureReviewCommentVote.getVoteStatus().equals(LectureReviewCommentVote.VoteStatus.DOWN)){ //만약에 DOWN 이면
            lectureReviewCommentVote.setVoteStatus(LectureReviewCommentVote.VoteStatus.NONE); // DOWN 취소로 동작 (NONE 으로 변경)
            lectureReviewComment.setVoteCount(lectureReviewComment.getVoteCount() +1); // 게시글의 카운트수 +1
        } else if (lectureReviewCommentVote.getVoteStatus().equals(LectureReviewCommentVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewCommentVote.setVoteStatus(LectureReviewCommentVote.VoteStatus.DOWN); // DOWN 으로 변경
            lectureReviewComment.setVoteCount(lectureReviewComment.getVoteCount() -1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewCommentVote.setVoteStatus(LectureReviewCommentVote.VoteStatus.DOWN); // UP -> DOWN 으로 변경
            lectureReviewComment.setVoteCount(lectureReviewComment.getVoteCount() -2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewCommentVoteRepository.save(lectureReviewCommentVote);
    }

    //
    public LectureReviewCommentVote findLectureReviewPostVote (LectureReviewComment lectureReviewComment, Member member){
        Optional<LectureReviewCommentVote> findLectureReviewPostVote = lectureReviewCommentVoteRepository.findByLectureReviewCommentAndMember(lectureReviewComment, member);
        return findLectureReviewPostVote.orElseGet(()-> new LectureReviewCommentVote(lectureReviewComment, member, LectureReviewCommentVote.VoteStatus.NONE));
    }
}
