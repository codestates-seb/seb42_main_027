package ynzmz.server.comment.free.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.comment.free.mapper.FreePostCommentMapper;
import ynzmz.server.comment.free.service.FreeCommentService;
import ynzmz.server.dto.MultiResponseDto;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/comments/frees")
@RequiredArgsConstructor
public class FreeCommentController {
    private final MemberService memberService;
    private final FreePostCommentMapper freePostCommentMapper;
    private final FreeCommentService freeCommentService;
    @PostMapping
    public ResponseEntity<?> createFreeReviewComment(@RequestBody FreeCommentDto.Post postDto) {
        FreeComment freeComment = freePostCommentMapper.FreeCommentToFreeCommentPost(postDto);
        Member member = memberService.findMemberById(postDto.getMemberId());
        freeComment.setMember(member);

        FreeComment createFreeComment = freeCommentService.creatFreeReviewComment(freeComment);
        FreeCommentDto.Response response = freePostCommentMapper.FreeCommentResponseToFreeComment(createFreeComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{free-comment-id}")
    public ResponseEntity<?> updateFreeReviewComment(@RequestBody FreeCommentDto.Patch patchDto,
                                                        @PathVariable("free-comment-id") long freePostCommentId) {
        FreeComment freeComment = freePostCommentMapper.FreeCommentToFreeCommentPatch(patchDto);
        freeComment.setFreeCommentId(freePostCommentId);

        FreeComment updateFreeComment = freeCommentService.updateFreeComment(freeComment);
        FreeCommentDto.Response response = freePostCommentMapper.FreeCommentResponseToFreeComment(updateFreeComment);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //강의별 댓글 조회 (필터기능)
    @GetMapping
    public ResponseEntity<?> getFreeReviewComments(@RequestParam(value = "filter", required = false) String filter,
                                                      @RequestParam long freeId,
                                                      @RequestParam int page,
                                                      @RequestParam int size) {

        if (filter == null) filter = "freeCommentId";
        Page<FreeComment> pageFreePostComments = freeCommentService.getFreeReviewComments(freeId, filter, page - 1, size);
        List<FreeComment> freeComments = pageFreePostComments.getContent();

        List<FreeCommentDto.Response> responses = freePostCommentMapper.FreeCommentResponsesToFreeComments(freeComments);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageFreePostComments), HttpStatus.OK);
    }

    @DeleteMapping("/{free-comment-id}")
    public void deleteFreeReviewComment(@PathVariable("free-comment-id") long freeCommentId) {
        freeCommentService.deleteFreeReviewComment(freeCommentId);
    }
}
