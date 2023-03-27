package ynzmz.server.board.free.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.mapper.FreeMapper;
import ynzmz.server.board.free.repository.FreeRepository;
import ynzmz.server.board.free.service.FreeService;
import ynzmz.server.global.dto.MultiResponseDto;
import ynzmz.server.global.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/boards/frees")
@RequiredArgsConstructor
@Slf4j
public class FreeController {
    private final FreeService freeService;
    private final MemberService memberService;
    private final FreeMapper freeMapper;

    private final FreeRepository freeRepository;


    @PostMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postFree(@RequestBody FreeDto.post post){
        Free free = freeMapper.freePostToFree(post);
        free.setMember(loginMemberFindByToken());

        freeService.createFree(free);
        FreeDto.DetailResponse Response = freeMapper.freeToFreeDetailResponse(free);
        return new ResponseEntity<>(new SingleResponseDto<>(Response),HttpStatus.CREATED);
    }


    @GetMapping("/{free-id}")
    public ResponseEntity<?> getDetailFree(@PathVariable("free-id") long Id) {
        Free foundFree = freeService.findFreeById(Id);
        freeService.plusViewCount(foundFree);
        freeService.getCommentNum(foundFree);

        FreeDto.DetailResponse response = freeMapper.freeToFreeDetailResponse(foundFree);

    return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<?> getListedFree(
                                           @RequestParam(required = false) String sort,//추천순 조회순
                                           @RequestParam(required = false) String category,
                                           @RequestParam int page) {
        String sortion = null;
        if(sort.equals("추천순")){
            sortion = "voteCount";
        }
        if(sort.equals("조회순")){
            sortion = "viewCount";
        }
        if(sort.equals("최신순")){
            sortion = null;
        }

        if(category.equals("전체")){

            Page<Free> foundFreePage = freeService.findAllFree(page-1);
            List<Free> listFoundFree = foundFreePage.getContent();
            List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
            return new ResponseEntity<>(new MultiResponseDto<>(responses,foundFreePage),HttpStatus.OK);

        }

        else {
            if (category != null && sortion != null) { //정렬/ 카테고리 다 사용


                Page<Free> foundFreePage = freeService.findFreesByCategoryAndSort(page - 1, category, sortion);//일상 정보등 카테고리
                List<Free> listFoundFree = foundFreePage.getContent();
                List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
                return new ResponseEntity<>(new MultiResponseDto<>(responses, foundFreePage), HttpStatus.OK);
            } else if (category != null && sortion == null) {//카테고리만 사용
                Page<Free> foundFreePage = freeService.findFreesByCategoryAndSort(page - 1, category);
                List<Free> listFoundFree = foundFreePage.getContent();
                List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
                return new ResponseEntity<>(new MultiResponseDto<>(responses, foundFreePage), HttpStatus.OK);
            } else if (category == null && sortion != null) { //정렬만 사용
                Page<Free> foundFreePage = freeService.findFreesWithSort(page - 1, sortion);
                List<Free> listFoundFree = foundFreePage.getContent();
                List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
                return new ResponseEntity<>(new MultiResponseDto<>(responses, foundFreePage), HttpStatus.OK);

            }
            else {//기본 --> 카테고리, 정렬 모두 없을떄
                Page<Free> foundFreePage = freeService.findAllFree(page - 1);
                List<Free> listFoundFree = foundFreePage.getContent();
                List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
                return new ResponseEntity<>(new MultiResponseDto<>(responses, foundFreePage), HttpStatus.OK);
            }
        }

        //추천순 X 조회순 X --> 바닐라 버전

    }

    @GetMapping("/search")
    public ResponseEntity<?> getFindListedFree(
            @RequestParam(required = false) String sort,//추천순 조회순
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String title,
            @RequestParam int page) {
        String sortion = null;
        if(sort.equals("추천순")){
            sortion = "voteCount";
        }
        if(sort.equals("조회순")){
            sortion = "viewCount";
        }
        if(sort.equals("최신순")){
            sortion = null;
        }

        if(category.equals("전체")){

            Page<Free> foundFreePage = freeService.findAllFree(page-1);
            List<Free> listFoundFree = foundFreePage.getContent();
            List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
            return new ResponseEntity<>(new MultiResponseDto<>(responses,foundFreePage),HttpStatus.OK);

        }

        else {
            if (category != null && sortion != null) { //정렬/ 카테고리 다 사용


                Page<Free> foundFreePage = freeService.findFreesByCategoryAndSortAndTitle(page - 1, category, sortion,title);//일상 정보등 카테고리
                List<Free> listFoundFree = foundFreePage.getContent();
                List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
                return new ResponseEntity<>(new MultiResponseDto<>(responses, foundFreePage), HttpStatus.OK);
            } else if (category != null && sortion == null) {//카테고리만 사용
                Page<Free> foundFreePage = freeService.findFreesByCategoryAndSortAndTitle(page - 1, category , title);
                List<Free> listFoundFree = foundFreePage.getContent();
                List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
                return new ResponseEntity<>(new MultiResponseDto<>(responses, foundFreePage), HttpStatus.OK);
            } else if (category == null && sortion != null) { //정렬만 사용
                Page<Free> foundFreePage = freeService.findFreesWithSort(page - 1, sortion,title);
                List<Free> listFoundFree = foundFreePage.getContent();
                List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
                return new ResponseEntity<>(new MultiResponseDto<>(responses, foundFreePage), HttpStatus.OK);

            }
            else {//기본 --> 카테고리, 정렬 모두 없을떄
                Page<Free> foundFreePage = freeService.findAllFree(page - 1);
                List<Free> listFoundFree = foundFreePage.getContent();
                List<FreeDto.ListResponse> responses = freeMapper.freesToFreeListResponses(listFoundFree);
                return new ResponseEntity<>(new MultiResponseDto<>(responses, foundFreePage), HttpStatus.OK);
            }
        }

        //추천순 X 조회순 X --> 바닐라 버전

    }

    @PatchMapping("/{free-id}")
    public ResponseEntity<?> patchFree(@PathVariable("free-id")long id, @RequestBody FreeDto.patch freepatch) {
        memberService.memberValidation(loginMemberFindByToken(), freeService.findFreeById(id).getMember().getMemberId());

        Free free = freeMapper.freePatchToFree(freepatch);
        free.setFreeId(id);

        Free newFree = freeService.updateFree(free);
        FreeDto.DetailResponse freeDetailResponse = freeMapper.freeToFreeDetailResponse(newFree);


        return new ResponseEntity<>(new SingleResponseDto<>(freeDetailResponse),HttpStatus.OK);
    }

    @DeleteMapping("/{free-id}")
    public ResponseEntity<?> deleteFree(@PathVariable("free-id") long id){
        freeService.deleteFree(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Member loginMemberFindByToken(){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }


}
