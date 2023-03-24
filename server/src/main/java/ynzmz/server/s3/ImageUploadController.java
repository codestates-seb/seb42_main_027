package ynzmz.server.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ynzmz.server.dto.SingleResponseDto;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class ImageUploadController {
    private final S3Service s3Service;

    @GetMapping
    public ResponseEntity<?> getDirectoryFileUrls(@RequestParam(required = false) String filePatch){
        List<String> strings = s3Service.listFilesInBucketDirectory(filePatch);

        return new ResponseEntity<>(new SingleResponseDto<>(strings), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@ModelAttribute MultipartFile image,
                                        @RequestParam(required = false) String filePatch) throws IOException {
        String imagePatch = s3Service.uploadFile(image, filePatch);

        return new ResponseEntity<>(new SingleResponseDto<>(imagePatch), HttpStatus.OK);
    }
}
