package ynzmz.server.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ynzmz.server.s3.repository.S3FileInfoRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@RequiredArgsConstructor
@Service
public class S3UpLoadService {
    private final AmazonS3Client amazonS3Client;
    private final S3FileInfoRepository s3FileInfoRepository;
    private final String bucketName = "main-project-28-img";
    public String uploadFile(MultipartFile multipartFile, String uploadPath) throws IOException {
        if(multipartFile.isEmpty()) return null;

        //현재 저장할 경로에 파일명 불러오기
        List<String> listFilesInBucketDirectory = listFilesInBucketDirectory(uploadPath);

        //파일 경로가 없으면 경로 만들기 ( 파일명에 / 붙히면 자동으로 만들어짐)

        //디렉토리 제외, 확장자 없엔 이름에서 마지막 숫자 확인후 +1 숫자로 파일명설정
        long createdFileName = createFileName(listFilesInBucketDirectory);
        //파일 확장자
        String fileExtension = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().indexOf("."));
        //생성할 파일명
        String fileName = uploadPath + "/" + createdFileName + fileExtension;

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

    //파일명 생성 ( 폴더당 start 0 ~ ++ )
    private long createFileName(List<String> listFilesInBucketDirectory) {
        int maxNumber = -1;

        for (String file : listFilesInBucketDirectory) {
            int lastSlashIndex = file.lastIndexOf('/');
            int lastDotIndex = file.lastIndexOf('.');

            if (lastDotIndex > lastSlashIndex) {
                String fileNameWithoutExtension = file.substring(lastSlashIndex + 1, lastDotIndex);

                try {
                    int number = Integer.parseInt(fileNameWithoutExtension);
                    maxNumber = Math.max(maxNumber, number);
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return maxNumber == -1 ? 0 : maxNumber + 1;
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
