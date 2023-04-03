package ynzmz.server.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ynzmz.server.s3.repository.S3FileInfoRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@RequiredArgsConstructor
@Service
@Slf4j
public class S3UpLoadService {
    private final AmazonS3Client amazonS3Client;
    private final S3FileInfoRepository s3FileInfoRepository;

    @Value("${s3.upload.bucket-Url}")
    private String imageBucket;
    private final String bucketName = "main-project-28-img";
    public String uploadFile(MultipartFile multipartFile,
                             String uploadPath) throws IOException {
        if(multipartFile.isEmpty()) return null;

        //현재 저장할 경로에 저장된 파일명 불러오기 ( 파일명 = 디렉토리 경로 포함 되어있음 )
        List<String> FileNamesInPath = getFileNamesInPath(uploadPath);
        //파일명 생성 ( 중복방지를 위해 새로운 num 파일명으로 생성 )
        long createdFileName = createFileName(FileNamesInPath);
        //파일 확장자만 가지고오기
        String fileExtension = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        //생성할 파일명 ( 경로 + 생성한 파일명 + 확장자 )
        String fileName = uploadPath + "/" + createdFileName + fileExtension;

        //S3 에 파일 업로드
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        amazonS3Client.putObject(
                new PutObjectRequest(bucketName, fileName, multipartFile.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    //파일명 생성 ( 폴더당 start 0 ~ ++ )
    private long createFileName(List<String> fileNamesInPath) {
        int maxNumber = -1;

        for (String fileName : fileNamesInPath) {
            int lastSlashIndex = fileName.lastIndexOf('/');
            int lastDotIndex = fileName.lastIndexOf('.');

            if (lastDotIndex > lastSlashIndex) {
                String fileNameWithoutExtension = fileName.substring(lastSlashIndex + 1, lastDotIndex);

                try {
                    int number = Integer.parseInt(fileNameWithoutExtension);
                    maxNumber = Math.max(maxNumber, number);
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return maxNumber == -1 ? 0 : maxNumber + 1;
    }


    //저장할 디렉토리 내부 파일명 불러오기
    public List<String> getFileNamesInPath(String filePath) {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName).withPrefix(filePath);
        ObjectListing objectListing;

        List<String> s3ImageName = new ArrayList<>();

        objectListing = amazonS3Client.listObjects(listObjectsRequest);

        do {
            List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();

            for (S3ObjectSummary objectSummary : objectSummaries) {
                log.info(objectSummary.getKey());
                s3ImageName.add(objectSummary.getKey());
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
        } while (objectListing.isTruncated());

        return s3ImageName;
    }

    public void deleteFileByFilePath(String filePath) {
        if(filePath != null) {
            amazonS3Client.deleteObject(bucketName, filePath);
        }
    }
    public void deleteFilesByFilePaths(List<String> filePaths) {
        if(!filePaths.isEmpty()) {
            for(String filePath : filePaths) amazonS3Client.deleteObject(bucketName, filePath);
        }
    }
    public void deleteFileByFileUrl(String fileUrl) {
        String filePath = getFilePathToFileUrl(fileUrl);

        amazonS3Client.deleteObject(bucketName, filePath);
    }

    public void deleteFilesByFileUrls(List<String> fileUrls) {
        if(!fileUrls.isEmpty()) {
            for(String fileUrl : fileUrls) amazonS3Client.deleteObject(bucketName, getFilePathToFileUrl(fileUrl));
        }
    }
    private String getFilePathToFileUrl(String fileUrl) {
        String prefix = "https://main-project-28-img.s3.ap-northeast-2.amazonaws.com/";
        return fileUrl.replace(prefix, "");
    }

}
