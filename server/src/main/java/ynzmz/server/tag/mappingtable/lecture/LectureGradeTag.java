package ynzmz.server.tag.mappingtable.lecture;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.entity.GradeTag;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class LectureGradeTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureGradeTagId;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    @JsonBackReference
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "grade_tag_id")
    @JsonBackReference
    private GradeTag gradeTag;
}
