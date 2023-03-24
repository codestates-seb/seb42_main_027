package ynzmz.server.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    private final String bucketName = "main-project-28-img";
    public String uploadFile(MultipartFile multipartFile, String uploadPatch) throws IOException {
        if(multipartFile.isEmpty()) return null;

        //현재 저장할 경로에 파일명 불러오기
        List<String> listFilesInBucketDirectory = listFilesInBucketDirectory(uploadPatch);

        //디렉토리 제외, 확장자 없엔 이름에서 마지막 숫자 확인후 +1 숫자로 파일명설정
        long createdFileName = createFileName(listFilesInBucketDirectory);
        //파일 확장자
        String fileExtension = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().indexOf("."));
        //생성할 파일명
        String fileName = uploadPatch + "/" + createdFileName + fileExtension;

        //파일 정보 등록
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        amazonS3Client.putObject(
                new PutObjectRequest(bucketName, fileName, multipartFile.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    private long createFileName(List<String> listFilesInBucketDirectory) {
        List<String> fileNameByPatch = new ArrayList<>();
        List<Integer> numFileName = new ArrayList<>();
        for(String file : listFilesInBucketDirectory) {
            int lastSlashIndex = file.lastIndexOf('/');
            int lastDotIndex = file.lastIndexOf('.');
            if (lastDotIndex > lastSlashIndex) {
                String fileNameWithoutExtension = file.substring(lastSlashIndex + 1, lastDotIndex);
                fileNameByPatch.add(fileNameWithoutExtension);
            }
        }

        for(String str : fileNameByPatch) {
            try {
                int number = Integer.parseInt(str);
                numFileName.add(number);
            } catch (NumberFormatException ignored) {
            }
        }

        //정렬
        Collections.sort(numFileName);

        if(numFileName.isEmpty()) return 0;
        else return numFileName.get(numFileName.size() -1) + 1;
    }

    //디렉토리 생성
    public void createDirectory(String directoryName) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[0]);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, directoryName + "/", inputStream, metadata);
        amazonS3Client.putObject(putObjectRequest);
    }
    //디렉토리 내부 파일명 확인
    public List<String> listFilesInBucketDirectory(String directoryName) {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName).withPrefix(directoryName);
        ObjectListing objectListing;

        List<String> s3ImageName = new ArrayList<>();

        objectListing = amazonS3Client.listObjects(listObjectsRequest);

        do {
            List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();

            for (S3ObjectSummary objectSummary : objectSummaries) {
                s3ImageName.add(objectSummary.getKey());
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
        } while (objectListing.isTruncated());

        return s3ImageName;
    }

    public void deleteFile(String bucketName, String key) {
        amazonS3Client.deleteObject(bucketName, key);
    }
}
