package ynzmz.server.s3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ynzmz.server.global.dto.SingleResponseDto;
import ynzmz.server.s3.service.S3FileInfoService;
import ynzmz.server.s3.service.S3UpLoadService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class S3UpLoadController {
    private final S3FileInfoService s3FileInfoService;
    private final S3UpLoadService s3UpLoadService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@ModelAttribute MultipartFile image,
                                        @RequestParam(required = false) String filePath) throws IOException {
        //파일 업로드 ( 경로에 파일명 검색 -> 파일명 생성 -> 저장 )
        String imagePatch = s3UpLoadService.uploadFile(image, filePath);

        // 이미지 업로드후 경로를 S3File 테이블에 상태값 TEMP 로 저장
        s3FileInfoService.saveTempS3FileInfo(imagePatch);
        // 글이 작성되면, 아이디값을 붙히고, ACTIVE 로 상태값 변경 ( 이건 각각 controller 에서 )
        // TEMP 상태인것은 S3CleanupScheduler 에서 한시간마다 자동삭제

        return new ResponseEntity<>(new SingleResponseDto<>(imagePatch), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getFileNameByS3Patch(@RequestParam(required = false) String filePath){
        List<String> strings = s3UpLoadService.getFileNamesInPath(filePath);

        return new ResponseEntity<>(new SingleResponseDto<>(strings), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFiles(@RequestBody List<String> filePaths) {
        s3UpLoadService.deleteFilesByFileUrls(filePaths);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
