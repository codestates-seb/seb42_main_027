package ynzmz.server.tag.mappingtable.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.teacher.entity.Teacher;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class TeacherSubjectTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherSubjectTagId;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_tag_id")
    @JsonBackReference
    private SubjectTag subjectTag;
}
