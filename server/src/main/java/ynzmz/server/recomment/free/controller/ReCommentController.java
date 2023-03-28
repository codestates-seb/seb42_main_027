package ynzmz.server.recomment.free.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.comment.free.service.FreeCommentService;
import ynzmz.server.recomment.free.dto.ReCommentDto;
import ynzmz.server.recomment.free.entity.FreeReComment;
import ynzmz.server.recomment.free.mapper.ReCommentMapper;
import ynzmz.server.recomment.free.service.ReCommentService;
import ynzmz.server.global.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

@RestController
@RequestMapping("/recomments/frees")
@RequiredArgsConstructor
public class ReCommentController {
    private final MemberService memberService;
    private final ReCommentMapper recommentMapper;
    private final ReCommentService recommentService;
    private final FreeCommentService freeCommentService;
    @PostMapping("/{free-comment-id}")
    public ResponseEntity<?> createReComment(@PathVariable("free-comment-id") long freeCommentId,
                                                   @RequestBody ReCommentDto.Post postDto) {
        FreeReComment recomment = recommentMapper.recommentPostToRecomment(postDto);
        recomment.setMember(loginMemberFindByToken());
        FreeComment freeComment = freeCommentService.findFreeCommentById(freeCommentId);
        recomment.setFreeComment(freeComment);

        if(recomment.getMember().getMemberId() == recomment.getFreeComment().getMember().getMemberId()){
            recomment.setMemberSim(true);
        }
        else {
            recomment.setMemberSim(false);
        }

        FreeReComment createFreeReComment = recommentService.creatRecomment(recomment);
        ReCommentDto.Response response = recommentMapper.recommentToRecommentResponse(createFreeReComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{reComment-id}")
    public ResponseEntity<?> updateReComment(@RequestBody ReCommentDto.Patch patchDto,
                                                        @PathVariable("reComment-id") long reCommentId) {
        FreeReComment recomment = recommentMapper.recommentPatchToRecomment(patchDto);
        recomment.setFreeReCommentId(reCommentId);
//        freeComment.setMember(loginMemberFindByToken());


        FreeReComment updateFreeReComment = recommentService.updateRecomment(recomment);
        ReCommentDto.Response response = recommentMapper.recommentToRecommentResponse(updateFreeReComment);
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

    @DeleteMapping("/{reComment-id}")
    public void ReComment(@PathVariable("reComment-id") long reCommentId) {
        recommentService.deleteReComment(reCommentId);
    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}
