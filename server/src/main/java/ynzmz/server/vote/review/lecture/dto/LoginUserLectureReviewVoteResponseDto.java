package ynzmz.server.vote.review.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.Vote;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserLectureReviewVoteResponseDto {
    private long lectureReviewId;
    private Vote.Status voteStatus;
}