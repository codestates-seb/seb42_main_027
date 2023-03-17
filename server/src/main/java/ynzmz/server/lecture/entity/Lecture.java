package ynzmz.server.lecture.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.tag.mappingtable.lecture.LectureGradeTag;
import ynzmz.server.tag.mappingtable.lecture.LecturePlatformTag;
import ynzmz.server.tag.mappingtable.lecture.LectureSubjectTag;
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
    private String title;
    private String introduction;
    private Status status;
    private double starPointAverage;
    private long totalReviewCount;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<LectureGradeTag> gradeTags = new ArrayList<>();
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<LectureSubjectTag> subjectTags = new ArrayList<>();
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<LecturePlatformTag> platformTags = new ArrayList<>();
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<LectureReview> lectureReviews = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    public enum Status {
        예정,
        진행중,
        완강
    }

}
