package ynzmz.server.tag.entity.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.board.teacher.entity.Teacher;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "map_teacher_platform_tag")
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
