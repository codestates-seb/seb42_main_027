package ynzmz.server.tag;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Enumerated(value = EnumType.STRING)
    @Column(unique = true)
    private Type type;
    @OneToMany(mappedBy = "tag")
    @JsonManagedReference
    private List<TeacherTag> teacherTags = new ArrayList<>();

    @OneToMany(mappedBy = "tag")
    @JsonManagedReference
    private List<LectureTag> lectureTags =new ArrayList<>();

    public enum Type {
        KOREAN,
        ENGLISH,
        MATH,
        SCIENCE,
        SOCIETY,
        고3,
        고2,
        고1,
        내신,
        정시,
        수시,
        수능,
        메가스터디,
        대성마이맥,
        이투스,
        EBS

    }
}
