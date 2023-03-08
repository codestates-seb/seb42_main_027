package ynzmz.server.lecture.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.tag.LectureTag;
import ynzmz.server.tag.Tag;
import ynzmz.server.teacher.entity.Teacher;

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
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<LectureTag> tags = new ArrayList<>();
}
