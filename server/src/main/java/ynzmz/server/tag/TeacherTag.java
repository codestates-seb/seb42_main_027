package ynzmz.server.tag;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ynzmz.server.teacher.entity.Teacher;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
public class TeacherTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherTagId;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
