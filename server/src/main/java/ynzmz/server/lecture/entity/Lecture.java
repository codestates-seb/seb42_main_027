package ynzmz.server.lecture.entity;

import lombok.Getter;
import lombok.Setter;
import ynzmz.server.tag.entity.LectureTag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//강의
@Entity
@Getter @Setter
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;
    private String name;
    private String introduction;
    private long starPointAverage;
    @OneToMany(mappedBy = "lecture")
    private List<LectureTag> lectureTags = new ArrayList<>();

}
