package ynzmz.server.s3.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ynzmz.server.s3.entity.S3FileInfo;
import ynzmz.server.s3.service.S3FileInfoService;
import ynzmz.server.s3.service.S3UpLoadService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class S3CleanupScheduler {
    private final S3FileInfoService s3FileInfoService;
    private final S3UpLoadService s3UpLoadService;
//    @Scheduled(cron = "0 0 * * * *") //corn 표현식 ( 매 정각 시간 마다 실행 (1시간주기))
    public void cleanupS3File(){
        //상태값 TEMP 인 S3FileInfo 불러오기
        List<S3FileInfo> unusedFiles = s3FileInfoService.findS3FileInfosForTemp();

        //S3 delete 파일경로 를 통해서 삭제
        for(S3FileInfo unusedFile : unusedFiles){
            s3UpLoadService.deleteFileByFilePath(unusedFile.getFilePath());
        }
        //DB 데이터도 삭제
        s3FileInfoService.deleteS3FileInfos(unusedFiles);

    }
}
