package ynzmz.server.tag;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.teacher.entity.Teacher;

import javax.persistence.*;
import java.util.List;

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
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
