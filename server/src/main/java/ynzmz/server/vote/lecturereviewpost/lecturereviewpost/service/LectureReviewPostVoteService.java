package ynzmz.server.vote.lecturereviewpost.lecturereviewpost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity.LectureReviewPostVote;
import ynzmz.server.vote.lecturereviewpost.lecturereviewpost.repository.LectureReviewPostVoteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureReviewPostVoteService {

    private final LectureReviewPostVoteRepository lectureReviewPostVoteRepository;

    public LectureReviewPostVote lectureReviewPostVoteUp(LectureReviewPost lectureReviewPost, Member member) {

        LectureReviewPostVote lectureReviewPostVote = findLectureReviewPostVote(lectureReviewPost, member);// 현재 상태값 불러오기

        if(lectureReviewPostVote.getVoteStatus().equals(LectureReviewPostVote.VoteStatus.UP)){ //만약에 UP 이면
            lectureReviewPostVote.setVoteStatus(LectureReviewPostVote.VoteStatus.NONE); // UP 취소로 동작 (NONE 으로 변경)
            lectureReviewPost.setVoteCount(lectureReviewPost.getVoteCount() -1); // 게시글의 카운트수 -1
        } else if (lectureReviewPostVote.getVoteStatus().equals(LectureReviewPostVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewPostVote.setVoteStatus(LectureReviewPostVote.VoteStatus.UP); // UP 으로 변경
            lectureReviewPost.setVoteCount(lectureReviewPost.getVoteCount() +1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewPostVote.setVoteStatus(LectureReviewPostVote.VoteStatus.UP); // DOWN -> UP 으로 변경
            lectureReviewPost.setVoteCount(lectureReviewPost.getVoteCount() +2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewPostVoteRepository.save(lectureReviewPostVote);
    }

    public LectureReviewPostVote lectureReviewPostVoteDown(LectureReviewPost lectureReviewPost, Member member) {

        LectureReviewPostVote lectureReviewPostVote = findLectureReviewPostVote(lectureReviewPost, member);// 현재 상태값 불러오기

        if(lectureReviewPostVote.getVoteStatus().equals(LectureReviewPostVote.VoteStatus.DOWN)){ //만약에 DOWN 이면
            lectureReviewPostVote.setVoteStatus(LectureReviewPostVote.VoteStatus.NONE); // DOWN 취소로 동작 (NONE 으로 변경)
            lectureReviewPost.setVoteCount(lectureReviewPost.getVoteCount() +1); // 게시글의 카운트수 +1
        } else if (lectureReviewPostVote.getVoteStatus().equals(LectureReviewPostVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewPostVote.setVoteStatus(LectureReviewPostVote.VoteStatus.DOWN); // DOWN 으로 변경
            lectureReviewPost.setVoteCount(lectureReviewPost.getVoteCount() -1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewPostVote.setVoteStatus(LectureReviewPostVote.VoteStatus.DOWN); // UP -> DOWN 으로 변경
            lectureReviewPost.setVoteCount(lectureReviewPost.getVoteCount() -2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewPostVoteRepository.save(lectureReviewPostVote);
    }

    public LectureReviewPostVote findLectureReviewPostVote (LectureReviewPost lectureReviewPost, Member member){
        Optional<LectureReviewPostVote> findLectureReviewPostVote = lectureReviewPostVoteRepository.findByLectureReviewPostAndMember(lectureReviewPost, member);
        return findLectureReviewPostVote.orElseGet(()-> new LectureReviewPostVote(lectureReviewPost, member, LectureReviewPostVote.VoteStatus.NONE));
    }
}
