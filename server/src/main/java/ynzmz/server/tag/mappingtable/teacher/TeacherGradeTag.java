package ynzmz.server.tag.mappingtable.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.teacher.entity.Teacher;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
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
