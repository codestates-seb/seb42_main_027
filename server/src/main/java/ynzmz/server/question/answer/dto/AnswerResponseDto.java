package ynzmz.server.question.answer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.member.entity.Member;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.question.question.dto.QuestionDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AnswerResponseDto {
    private Long answerId;
    private String content;
    private int voteCount;
    private String createdAt;
    private String modifiedAt;
    private Answer.AdoptStatus adoptStatus;
    private Member member;
    private QuestionDto.ListPageResponse question;
}
