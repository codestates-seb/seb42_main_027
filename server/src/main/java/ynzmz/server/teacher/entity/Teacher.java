package ynzmz.server.teacher.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.tag.entity.TeacherTag;

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
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<TeacherTag> teacherTags = new ArrayList<>();
}
