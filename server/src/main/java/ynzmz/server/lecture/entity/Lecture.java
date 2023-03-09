package ynzmz.server.lecture.entity;

import lombok.Getter;
import lombok.Setter;
import ynzmz.server.global.SubjectType;
import ynzmz.server.teacher.entity.Teacher;

import javax.persistence.*;
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
    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<SubjectType> subjectTypes;
    private long starPointAverage;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
