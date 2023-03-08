package ynzmz.server.tag.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.tag.entity.Tag;
import ynzmz.server.teacher.entity.Teacher;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class TeacherTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherTagId;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    @JsonBackReference
    private Tag tag;
}
