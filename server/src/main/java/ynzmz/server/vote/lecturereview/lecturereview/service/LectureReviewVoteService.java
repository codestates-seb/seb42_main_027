package ynzmz.server.vote.lecturereview.lecturereview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.lecturereview.lecturereview.entity.LectureReviewVote;
import ynzmz.server.vote.lecturereview.lecturereview.repository.LectureReviewVoteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureReviewVoteService {

    private final LectureReviewVoteRepository lectureReviewVoteRepository;

    public LectureReviewVote lectureReviewPostVoteUp(LectureReview lectureReview, Member member) {

        LectureReviewVote lectureReviewVote = findLectureReviewPostVote(lectureReview, member);// 현재 상태값 불러오기

        if(lectureReviewVote.getVoteStatus().equals(LectureReviewVote.VoteStatus.UP)){ //만약에 UP 이면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.NONE); // UP 취소로 동작 (NONE 으로 변경)
            lectureReview.setVoteCount(lectureReview.getVoteCount() -1); // 게시글의 카운트수 -1
        } else if (lectureReviewVote.getVoteStatus().equals(LectureReviewVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.UP); // UP 으로 변경
            lectureReview.setVoteCount(lectureReview.getVoteCount() +1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.UP); // DOWN -> UP 으로 변경
            lectureReview.setVoteCount(lectureReview.getVoteCount() +2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewVoteRepository.save(lectureReviewVote);
    }

    public LectureReviewVote lectureReviewPostVoteDown(LectureReview lectureReview, Member member) {

        LectureReviewVote lectureReviewVote = findLectureReviewPostVote(lectureReview, member);// 현재 상태값 불러오기

        if(lectureReviewVote.getVoteStatus().equals(LectureReviewVote.VoteStatus.DOWN)){ //만약에 DOWN 이면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.NONE); // DOWN 취소로 동작 (NONE 으로 변경)
            lectureReview.setVoteCount(lectureReview.getVoteCount() +1); // 게시글의 카운트수 +1
        } else if (lectureReviewVote.getVoteStatus().equals(LectureReviewVote.VoteStatus.NONE)) { // NONE 상태면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.DOWN); // DOWN 으로 변경
            lectureReview.setVoteCount(lectureReview.getVoteCount() -1); // 게시글 카운트 수 +1
        } else { //down 상태면
            lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.DOWN); // UP -> DOWN 으로 변경
            lectureReview.setVoteCount(lectureReview.getVoteCount() -2); // 게시글 카운트 수 + 2 (DOWN -> UP)이라서
        }
        return lectureReviewVoteRepository.save(lectureReviewVote);
    }

    public LectureReviewVote findLectureReviewPostVote (LectureReview lectureReview, Member member){
        Optional<LectureReviewVote> findLectureReviewPostVote = lectureReviewVoteRepository.findByLectureReviewAndMember(lectureReview, member);
        return findLectureReviewPostVote.orElseGet(()-> new LectureReviewVote(lectureReview, member, LectureReviewVote.VoteStatus.NONE));
    }
}
