package ynzmz.server.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    public String uploadImage(MultipartFile multipartFile, String patch) throws IOException {
        if(multipartFile.isEmpty()) return null;
        String fileName = multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        String imageBucket = "main-project-28-img/teachers";
        amazonS3Client.putObject(
                new PutObjectRequest(imageBucket, fileName, multipartFile.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

//        GetObjectRequest getObjectRequest = new GetObjectRequest();
//        S3Object object = amazonS3Client.getObject(getObjectRequest);
//        object.get

        return amazonS3Client.getUrl(imageBucket, fileName).toString();
    }
}
