package ynzmz.server.s3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ynzmz.server.dto.SingleResponseDto;
import ynzmz.server.s3.service.S3FileService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class S3FileController {
    private final S3FileService s3FileService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@ModelAttribute MultipartFile image,
                                        @RequestParam(required = false) String filePath) throws IOException {
        // filePath 에서 어디 테이블인지 구분해야함.
        // 이미지 업로드후 경로를 S3File 테이블에 상태값 TEMP 로 저장
        // 글이 작성되면, 아이디값을 붙히고, ACTIVE 로 상태값 변경
        // TEMP 상태인것은 한시간마다 자동삭제

        //파일 업로드
        String imagePatch = s3FileService.uploadFile(image, filePath);

        return new ResponseEntity<>(new SingleResponseDto<>(imagePatch), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getFileNameByS3Patch(@RequestParam(required = false) String filePath){
        List<String> strings = s3FileService.listFilesInBucketDirectory(filePath);

        return new ResponseEntity<>(new SingleResponseDto<>(strings), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFiles(@RequestBody List<String> filePaths) {
        s3FileService.deleteFilesByS3Urls(filePaths);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
