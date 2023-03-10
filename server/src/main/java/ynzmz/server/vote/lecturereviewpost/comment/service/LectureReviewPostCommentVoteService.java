package ynzmz.server.vote.lecturereviewpost.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.lecturereviewpost.comment.entity.LectureReviewPostCommentVote;
import ynzmz.server.vote.lecturereviewpost.comment.repository.LectureReviewPostCommentVoteRepository;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity.LectureReviewPostVote;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureReviewPostCommentVoteService {

    private final LectureReviewPostCommentVoteRepository lectureReviewPostCommentVoteRepository;

    public LectureReviewPostCommentVote lectureReviewPostVoteUp(LectureReviewPostComment lectureReviewPostComment, Member member) {

        LectureReviewPostCommentVote lectureReviewPostCommentVote = findLectureReviewPostVote(lectureReviewPostComment, member);// 현재 상태값 불러오기

        if(lectureReviewPostCommentVote.getVoteStatus().equals(LectureReviewPostCommentVote.VoteStatus.UP)){ //만약에 UP 이면
            lectureReviewPostCommentVote.setVoteStatus(LectureReviewPostCommentVote.VoteStatus.NONE); // UP 취소로 동작 (NONE 으로 변경)
            lectureReviewPostComment.setVoteCount(lectureReviewPostComment.getVoteCount() -1); // 게시글의 카운트수 -1
        } else if (lectureReviewPostCommentVote.getVoteStatus().equals(LectureReviewPostCommentVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewPostCommentVote.setVoteStatus(LectureReviewPostCommentVote.VoteStatus.UP); // UP 으로 변경
            lectureReviewPostComment.setVoteCount(lectureReviewPostComment.getVoteCount() +1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewPostCommentVote.setVoteStatus(LectureReviewPostCommentVote.VoteStatus.UP); // DOWN -> UP 으로 변경
            lectureReviewPostComment.setVoteCount(lectureReviewPostComment.getVoteCount() +2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewPostCommentVoteRepository.save(lectureReviewPostCommentVote);
    }

    public LectureReviewPostCommentVote lectureReviewPostVoteDown(LectureReviewPostComment lectureReviewPostComment, Member member) {

        LectureReviewPostCommentVote lectureReviewPostCommentVote = findLectureReviewPostVote(lectureReviewPostComment, member);// 현재 상태값 불러오기

        if(lectureReviewPostCommentVote.getVoteStatus().equals(LectureReviewPostCommentVote.VoteStatus.DOWN)){ //만약에 DOWN 이면
            lectureReviewPostCommentVote.setVoteStatus(LectureReviewPostCommentVote.VoteStatus.NONE); // DOWN 취소로 동작 (NONE 으로 변경)
            lectureReviewPostComment.setVoteCount(lectureReviewPostComment.getVoteCount() +1); // 게시글의 카운트수 +1
        } else if (lectureReviewPostCommentVote.getVoteStatus().equals(LectureReviewPostCommentVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewPostCommentVote.setVoteStatus(LectureReviewPostCommentVote.VoteStatus.DOWN); // DOWN 으로 변경
            lectureReviewPostComment.setVoteCount(lectureReviewPostComment.getVoteCount() -1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewPostCommentVote.setVoteStatus(LectureReviewPostCommentVote.VoteStatus.DOWN); // UP -> DOWN 으로 변경
            lectureReviewPostComment.setVoteCount(lectureReviewPostComment.getVoteCount() -2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewPostCommentVoteRepository.save(lectureReviewPostCommentVote);
    }

    public LectureReviewPostCommentVote findLectureReviewPostVote (LectureReviewPostComment lectureReviewPostComment, Member member){
        Optional<LectureReviewPostCommentVote> findLectureReviewPostVote = lectureReviewPostCommentVoteRepository.findByLectureReviewPostCommentAndMember(lectureReviewPostComment, member);
        return findLectureReviewPostVote.orElseGet(()-> new LectureReviewPostCommentVote(lectureReviewPostComment, member, LectureReviewPostCommentVote.VoteStatus.NONE));
    }
}
