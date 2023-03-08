package ynzmz.server.tag;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ynzmz.server.lecture.entity.Lecture;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureTagId;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    @JsonBackReference
    private Lecture lecture;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
