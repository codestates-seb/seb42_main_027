package ynzmz.server.s3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.s3.entity.S3FileInfo;

import java.util.List;

@Repository
public interface S3FileInfoRepository extends JpaRepository<S3FileInfo,Long> {
    List<S3FileInfo> findByStatus(S3FileInfo.Status status);
    List<S3FileInfo> findByDbTableName(String dbTableName);
    List<S3FileInfo> findByDbTableNameAndIdOfTable(String dbTableName, long idOfTable);
    List<S3FileInfo> findByFilePathIn(List<String> filePaths);
    S3FileInfo findByFilePath(String filePath);
}
