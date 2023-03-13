package ynzmz.server.tag.mappingtable.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.teacher.entity.Teacher;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class TeacherPlatformTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherPlatformTagId;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "platform_tag_id")
    @JsonBackReference
    private PlatformTag platformTag;
}
