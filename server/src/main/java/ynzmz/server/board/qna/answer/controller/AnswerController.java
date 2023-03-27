package ynzmz.server.board.qna.answer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ynzmz.server.board.qna.question.service.QuestionService;
import ynzmz.server.global.dto.SingleResponseDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.board.qna.answer.dto.AnswerDto;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.answer.mapper.AnswerMapper;
import ynzmz.server.board.qna.answer.service.AnswerService;
import ynzmz.server.s3.entity.S3FileInfo;
import ynzmz.server.s3.service.S3FileInfoService;
import ynzmz.server.s3.service.S3UpLoadService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards/qnas/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;
    private final AnswerService answerService;
    private final S3FileInfoService s3FileInfoService;
    private final S3UpLoadService s3UpLoadService;

    @PostMapping
    public ResponseEntity<?> postAnswer(@Valid @RequestBody AnswerDto.Post answerPost){
        Answer postDtoToAnswer = answerMapper.answerPostDtoToAnswer(answerPost);
        postDtoToAnswer.setMember(loginMemberFindByToken());
        postDtoToAnswer.setQuestion(questionService.findQuestionById(answerPost.getQuestionId()));

        Answer createAnswer = answerService.createAnswer(postDtoToAnswer);

        //가지고 있는 저장된 사진이름과 특정지어서 S3File 에 QuestionId 를 입력시키고,상태값 ACTIVE 로 변경
        List<String> uploadFilePaths = s3FileInfoService.getFilePathsByFileUrls(createAnswer.getUploadImages());
        List<S3FileInfo> s3FileInfos = s3FileInfoService.findS3FileInfoByTableName("answer");
        s3FileInfoService.setS3FileInfosStatusActiveAndIdConnection(uploadFilePaths, s3FileInfos, createAnswer.getAnswerId());

        AnswerDto.SimpleInfoResponse response = answerMapper.answerToAnswerInfoResponse(createAnswer);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity<?> patchAnswer(@PathVariable("answer-id") long answerId, @RequestBody AnswerDto.Patch answerPatch) {
        //게시글 작성자 & 로그인된 회원 일치하는지 확인
        memberService.memberValidation(loginMemberFindByToken(), answerService.findAnswerById(answerId).getMember().getMemberId());

        Answer answer = answerMapper.answerPatchDtoToAnswer(answerPatch);
        answer.setAnswerId(answerId);

        Answer updateAnswer = answerService.updateAnswer(answer);

        //가지고 있는 저장된 사진이름과 특정지어서 S3File 에 IdOfTable 를 입력시키고,상태값 ACTIVE 로 변경

        //수정전 저장된 이미지파일 경로들
        List<String> savedFilePaths = s3FileInfoService.getFilePathsByFileUrls(answer.getUploadImages());
        //수정후 저장된 이미지파일 경로들
        List<String> updateFilePaths = s3FileInfoService.getFilePathsByFileUrls(updateAnswer.getUploadImages());
        //비교후 새로 저장되는 이미지파일 경로들
        List<String> newFilePathsByUpdate = s3FileInfoService.checkNewFilesByUpdate(savedFilePaths, updateFilePaths);
        //비교후 삭제되어야할 이미지파일 경로들
        List<String> deleteFilePathsByUpdate = s3FileInfoService.checkDeleteFilesByUpdate(savedFilePaths, updateFilePaths);

        //S3FileInfo 값 불러오기
        List<S3FileInfo> s3FileInfos = s3FileInfoService.findS3FileInfoByTableNameAndId("answer", answerId);
        //새로 저장되는 이미지파일들 TEMP -> ACTIVE 로 변경 ( 수정글쓰기시 이미 TEMP 상태로 생성되어있음 )
        s3FileInfoService.setS3FileInfosStatusActiveAndIdConnection(newFilePathsByUpdate, s3FileInfos, updateAnswer.getAnswerId());

        //삭제되어야할 파일들 S3 에서 삭제 & DB S3FileInfo 값 삭제
        List<S3FileInfo> s3FileInfosByFilePaths = s3FileInfoService.findS3FileInfosByFilePaths(deleteFilePathsByUpdate);
        s3FileInfoService.deleteS3FileInfos(s3FileInfosByFilePaths);
        s3UpLoadService.deleteFilesByFilePaths(deleteFilePathsByUpdate);

        AnswerDto.SimpleInfoResponse response = answerMapper.answerToAnswerInfoResponse(updateAnswer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("answer-id")long answerId) {
        //게시글 작성자 & 로그인된 회원 일치하는지 확인
        memberService.memberValidation(loginMemberFindByToken(), answerService.findAnswerById(answerId).getMember().getMemberId());

        Answer answer = answerService.findAnswerById(answerId);
        //삭제되어야할 파일 Url -> 파일 경로 변환
        List<String> deleteFilePaths = s3FileInfoService.getFilePathsByFileUrls(answer.getUploadImages());
        //삭제되어야할 파일들 S3 에서 삭제 & DB S3FileInfo 값 삭제
        List<S3FileInfo> s3FileInfosByFilePaths = s3FileInfoService.findS3FileInfosByFilePaths(deleteFilePaths);
        s3FileInfoService.deleteS3FileInfos(s3FileInfosByFilePaths);
        s3UpLoadService.deleteFilesByFilePaths(deleteFilePaths);

        answerService.deleteAnswer(answerId);
        Optional<Answer> deletedAnswer = answerService.findOptionalAnswerById(answerId);

        return deletedAnswer.isEmpty() ? new ResponseEntity<>("삭제완료", HttpStatus.OK) : new ResponseEntity<>("삭제실패",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Member loginMemberFindByToken() {
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName(); // 토큰에서 유저 email 확인
        return memberService.findMemberByEmail(loginEmail);
    }
}
