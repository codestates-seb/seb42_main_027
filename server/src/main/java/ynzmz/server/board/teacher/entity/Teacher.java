package ynzmz.server.board.teacher.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.tag.entity.teacher.TeacherGradeTag;
import ynzmz.server.tag.entity.teacher.TeacherPlatformTag;
import ynzmz.server.tag.entity.teacher.TeacherSubjectTag;

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
    private String profileImageUrl;
    private String realImageUrl;
    private double starPointAverage;
    private long totalReviewCount;
    @ElementCollection(targetClass=String.class)
    @Column
    private List<String> profile = new ArrayList<>();
    @ElementCollection(targetClass=String.class)
    @Column
    private List<String> analects = new ArrayList<>();
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<TeacherGradeTag> gradeTags = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<TeacherSubjectTag> subjectTags = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<TeacherPlatformTag> platformTags = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Lecture> lectures = new ArrayList<>();
}
