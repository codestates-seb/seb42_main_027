package ynzmz.server.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
@RequiredArgsConstructor
@Service
public class S3Service {
    private String Bucket = "main-project-28-img";
    private final AmazonS3Client amazonS3Client;
}
