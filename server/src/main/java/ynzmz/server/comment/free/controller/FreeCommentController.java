package ynzmz.server.comment.free.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.service.FreeService;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.comment.free.mapper.FreeCommentMapper;
import ynzmz.server.comment.free.service.FreeCommentService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/comments/frees")
@RequiredArgsConstructor
public class FreeCommentController  {
    private final MemberService memberService;
    private final FreeCommentMapper freeCommentMapper;
    private final FreeCommentService freeCommentService;
    private final FreeService freeService;
    @PostMapping("/{free-id}")
    public ResponseEntity<?> createFreeComment(@PathVariable("free-id") long freeId,
                                               @RequestBody FreeCommentDto.Post postDto) {
        FreeComment freeComment = freeCommentMapper.freeCommentPostToFreeComment(postDto);
        freeComment.setMember(loginMemberFindByToken());
        Free free = freeService.findFreeById(freeId);
        freeComment.setFree(free);

        if(freeComment.getMember().getMemberId() == freeComment.getFree().getMember().getMemberId()){
            freeComment.setMemberSim(true);
        }
        else {
            freeComment.setMemberSim(false);
        }

        FreeComment createFreeComment = freeCommentService.creatFreeComment(freeComment);
        FreeCommentDto.Response response = freeCommentMapper.freeCommentToFreeCommentResponse(createFreeComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }



    @PatchMapping("/{free-comment-id}")
    public ResponseEntity<?> updateFreeComment(@RequestBody FreeCommentDto.Patch patchDto,
                                                        @PathVariable("free-comment-id") long freePostCommentId) {
        FreeComment freeComment = freeCommentMapper.freeCommentPatchToFreeComment(patchDto);
        freeComment.setFreeCommentId(freePostCommentId);
//        freeComment.setMember(loginMemberFindByToken());


        FreeComment updateFreeComment = freeCommentService.updateFreeComment(freeComment);
        FreeCommentDto.Response response = freeCommentMapper.freeCommentToFreeCommentResponse(updateFreeComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

//게시글별 코멘트 받아오기
//    @GetMapping
//    public ResponseEntity<?> getFreeComments(@RequestParam(value = "filter", required = false) String filter,
//                                                      @RequestParam long freeId,
//                                                      @RequestParam int page,
//                                                      @RequestParam int size) {
//
//        if (filter == null){
//            filter = "freeCommentId";
//        }
//        Page<FreeComment> pageFreePostComments = freeCommentService.getFrees(freeId, filter, page - 1, size);
//        List<FreeComment> freeComments = pageFreePostComments.getContent();
//
//        List<FreeCommentDto.Response> responses = freeCommentMapper.freeCommentToFreeCommentsResponses(freeComments);
//
//        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageFreePostComments), HttpStatus.OK);
//    }

    @DeleteMapping("/{free-comment-id}")
    public void deleteFreeComment(@PathVariable("free-comment-id") long freeCommentId) {
        freeCommentService.deleteFreeComment(freeCommentId);
    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}
