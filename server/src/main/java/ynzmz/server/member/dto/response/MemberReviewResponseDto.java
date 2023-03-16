package ynzmz.server.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.review.lecture.entity.LectureReview;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MemberReviewResponseDto {
    private List<LectureReview> lectureReview = new ArrayList<>();
}
