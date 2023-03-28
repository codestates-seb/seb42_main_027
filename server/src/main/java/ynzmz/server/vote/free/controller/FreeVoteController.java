package ynzmz.server.vote.free.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.service.FreeService;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.comment.free.service.FreeCommentService;
import ynzmz.server.global.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.vote.free.dto.FreeVoteDto;
import ynzmz.server.vote.free.entity.FreeVote;
import ynzmz.server.vote.free.mapper.FreeVoteMapper;
import ynzmz.server.vote.free.service.FreeVoteService;

@RestController
@RequestMapping("/votes/frees")
@RequiredArgsConstructor
public class FreeVoteController {
    private final FreeVoteService freeVoteService;
    private final FreeService FreeService;
    private final FreeCommentService FreeCommentService;
    private final FreeVoteMapper freeVoteMapper;
    private final  MemberService memberService;

    @PostMapping("/{free-id}/up")
    public ResponseEntity<?> FreeVoteUp(@PathVariable("free-id") long FreePostId) {

        Free Free = FreeService.findFreeById(FreePostId);
        Member member = loginMemberFindByToken();
        FreeVote freeVote = freeVoteService.findFreeVoteTargetFree(Free, member);// 현재 상태값 불러오기

        FreeVote voteUpFreeVote = freeVoteService.freeVoteUp(Free, freeVote);

        FreeVoteDto.Response response = freeVoteMapper.FreeVoteToFreeResponse(voteUpFreeVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/{free-id}/down")
    public ResponseEntity<?> FreeVoteDown(@PathVariable("free-id") long FreePostId) {
        Free Free = FreeService.findFreeById(FreePostId);
        Member member = loginMemberFindByToken();
        FreeVote freeVote = freeVoteService.findFreeVoteTargetFree(Free, member);// 현재 상태값 불러오기

        FreeVote voteDownFreeVote = freeVoteService.freeVoteDown(Free, freeVote);

        FreeVoteDto.Response response = freeVoteMapper.FreeVoteToFreeResponse(voteDownFreeVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/comments/{free-comment-id}/up")
    public ResponseEntity<?> FreeCommentVoteUp(@PathVariable("free-comment-id") long FreeCommentId) {

        FreeComment FreeComment = FreeCommentService.findFreeCommentById(FreeCommentId);
        Member member = loginMemberFindByToken();
        FreeVote freeVote = freeVoteService.findFreeVoteTargetComment(FreeComment, member);// 현재 상태값 불러오기

        FreeVote voteUpFreeVote = freeVoteService.freeVoteUp(FreeComment, freeVote);

        FreeVoteDto.Response response = freeVoteMapper.FreeVoteToFreeResponse(voteUpFreeVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PostMapping("/comments/{free-comment-id}/down")
    public ResponseEntity<?> FreeCommentVoteDown(@PathVariable("free-comment-id") long FreeCommentId) {
        FreeComment FreeComment = FreeCommentService.findFreeCommentById(FreeCommentId);
        Member member = loginMemberFindByToken();
        FreeVote freeVote = freeVoteService.findFreeVoteTargetComment(FreeComment, member);// 현재 상태값 불러오기

        FreeVote voteDownFreeVote = freeVoteService.freeVoteDown(FreeComment, freeVote);

        FreeVoteDto.Response response = freeVoteMapper.FreeVoteToFreeResponse(voteDownFreeVote);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}
