package ynzmz.server.s3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.s3.entity.S3FileInfo;
import ynzmz.server.s3.repository.S3FileInfoRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class S3FileInfoService {
    private final S3FileInfoRepository s3FileInfoRepository;

    public List<S3FileInfo> findS3FileInfosForTemp(){
        return s3FileInfoRepository.findByStatus(S3FileInfo.Status.TEMP);
    }

    public void saveTempS3FileInfo(String fileUrl) {
        String filePath = getFilePathToFileUrl(fileUrl);

        S3FileInfo s3FileInfo = new S3FileInfo();
        s3FileInfo.setFilePath(filePath);
        s3FileInfo.setDbTableName(extractTable(filePath));
        s3FileInfo.setStatus(S3FileInfo.Status.TEMP);

        s3FileInfoRepository.save(s3FileInfo);
    }

    public List<S3FileInfo> findS3FileInfoByTableName(String dbTableName) {
        return s3FileInfoRepository.findByDbTableName(dbTableName);
    }
    public List<S3FileInfo> findS3FileInfoByTableNameAndId(String dbTableName, long idOfTable) {
        return s3FileInfoRepository.findByDbTableNameAndIdOfTable(dbTableName,idOfTable);
    }
    public List<S3FileInfo> findS3FileInfosByFilePaths(List<String> filePaths) {
        return s3FileInfoRepository.findByFilePathIn(filePaths);
    }
    public S3FileInfo findS3FileInfoByFilePath(String filePath) {
        return s3FileInfoRepository.findByFilePath(filePath);
    }

    public List<String> getFilePathByS3FileInfo(List<S3FileInfo> s3FileInfos) {
        List<String> filePaths = new ArrayList<>();
        for(S3FileInfo s3FileInfo : s3FileInfos) {
            filePaths.add(s3FileInfo.getFilePath());
        }
        return filePaths;
    }
    public void setS3FileInfosStatusActiveAndIdConnection(List<String> usedFilePaths, List<S3FileInfo> s3FileInfos, long idOfTable ) {
        for(S3FileInfo s3FileInfo : s3FileInfos) {
            if(usedFilePaths.contains(s3FileInfo.getFilePath())) {
                s3FileInfo.setIdOfTable(idOfTable);
                s3FileInfo.setStatus(S3FileInfo.Status.ACTIVE);
                s3FileInfoRepository.save(s3FileInfo);
            }
        }
    }
    public void setS3FileInfosStatusActiveAndIdConnection(String proFileImagePath, String realImagePath, List<S3FileInfo> s3FileInfos, long idOfTable ) {
        for (S3FileInfo s3FileInfo : s3FileInfos) {
            if (proFileImagePath.equals(s3FileInfo.getFilePath()) || realImagePath.equals(s3FileInfo.getFilePath())) {
                s3FileInfo.setIdOfTable(idOfTable);
                s3FileInfo.setStatus(S3FileInfo.Status.ACTIVE);
                s3FileInfoRepository.save(s3FileInfo);
            }
        }
    }

    public void setS3FileInfosStatusActiveAndIdConnection(String ImagePath, List<S3FileInfo> s3FileInfos, long idOfTable ) {
        for (S3FileInfo s3FileInfo : s3FileInfos) {
            if (ImagePath.equals(s3FileInfo.getFilePath())) {
                s3FileInfo.setIdOfTable(idOfTable);
                s3FileInfo.setStatus(S3FileInfo.Status.ACTIVE);
                s3FileInfoRepository.save(s3FileInfo);
            }
        }
    }

    public List<String> checkNewFilesByUpdate(List<String> savedFilePaths, List<String> updateFilePaths) {
        //두개 비교해서 중복되는것 => 그대로 놔두기
        //update 에만 있는것 => active 로 변경
        List<String> newFiles = new ArrayList<>(updateFilePaths);
        newFiles.removeAll(savedFilePaths);
        return newFiles;
    }

    public List<String> checkDeleteFilesByUpdate(List<String> savedFilePaths, List<String> updateFilePaths) {
        //두개 비교해서 중복되는것 => 그대로 놔두기
        //save 에만 있는것 => 삭제
        List<String> deleteFiles = new ArrayList<>(savedFilePaths);
        deleteFiles.removeAll(updateFilePaths);
        return deleteFiles;

    }
    public void deleteS3FileInfos(List<S3FileInfo> s3FileInfos) {
        s3FileInfoRepository.deleteAll(s3FileInfos);
    }
    public void deleteS3FileInfo(S3FileInfo s3FileInfo) {
        s3FileInfoRepository.delete(s3FileInfo);
    }
    public String getFilePathToFileUrl(String fileUrl) {
        String prefix = "https://main-project-28-img.s3.ap-northeast-2.amazonaws.com/";
        return fileUrl.replace(prefix, "");
    }
    public List<String> getFilePathsByFileUrls(List<String> fileUrls) {
        String prefix = "https://main-project-28-img.s3.ap-northeast-2.amazonaws.com/";
        List<String> filePaths = new ArrayList<>();
        for(String fileUrl : fileUrls) filePaths.add(fileUrl.replace(prefix, ""));
        return filePaths;
    }

    public String extractTable(String filePath) {
        String[] splitPath = filePath.split("/");

        if (splitPath.length == 0) {
            return null;
        }

        String category = splitPath[0];

        switch (category) {
            case "boards":
                if (splitPath.length < 3) return splitPath[1];
                String subCategory = splitPath[1];
                if (subCategory.equals("qnas")) return splitPath[2].equals("answers") ? "answer" : "question";
                else if (subCategory.equals("frees")) return "free";
                else if (subCategory.equals("teachers")) return "teacher";
                else if (subCategory.equals("reviews") && splitPath[2].equals("lectures")) return "lectureReview";
                break;
            case "members": return "member";
            case "emoticons": return "emoticon";
        }
        //못알아보면 전체 반환
        return filePath;
    }
}
