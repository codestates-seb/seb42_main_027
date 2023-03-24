package ynzmz.server.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ynzmz.server.dto.SingleResponseDto;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController{
    private final S3Service s3Service;

    @PostMapping("/{patch}")
    public ResponseEntity<?> uploadImage(@ModelAttribute MultipartFile image,
                                         @PathVariable String patch) throws IOException {
        String imagePatch = s3Service.uploadImage(image, patch);

        return new ResponseEntity<>(new SingleResponseDto<>(imagePatch), HttpStatus.OK);
    }
}
