package ynzmz.server.tag.entity.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.board.teacher.entity.Teacher;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "map_teacher_grade_tag")
public class TeacherGradeTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherGradeTagId;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "grade_tag_id")
    @JsonBackReference
    private GradeTag gradeTag;
}
