package ynzmz.server.question.answer.dto;

import lombok.*;
import ynzmz.server.member.entity.Member;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.question.dto.QuestionDto;

import java.time.LocalDateTime;
import java.util.List;
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class AnswerInfoResponseDto {
    private Long answerId;
    private QuestionDto.InfoResponse questionId;
    private Member member; // 질문자 회원정보
    private String content;
    private int voteCount;
    private String createdAt;
    private String modifiedAt;
    private Answer.AdoptStatus adoptStatus;
}
