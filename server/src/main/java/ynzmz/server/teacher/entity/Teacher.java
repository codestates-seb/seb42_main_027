package ynzmz.server.teacher.entity;

import lombok.Getter;
import lombok.Setter;
import ynzmz.server.global.SubjectType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    private String name;
    private String introduction;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<SubjectType> teacherTypes = new ArrayList<>();



}
