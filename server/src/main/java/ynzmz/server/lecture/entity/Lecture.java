package ynzmz.server.lecture.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.tag.LectureTag;
import ynzmz.server.tag.Tag;
import ynzmz.server.tag.TeacherTag;
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
    @OneToMany(mappedBy = "lecture")
    private List<LectureTag> lectureTags = new ArrayList<>();

}
