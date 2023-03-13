package ynzmz.server.tag.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.tag.Tag;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class GradeTag implements Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeTagId;

    @Enumerated(value = EnumType.STRING)
    @Column(unique = true)
    private Grade grade;

    public GradeTag(Grade grade) {
        this.grade = grade;
    }

    public enum Grade {
        중1,
        중2,
        중3,
        예비중1,
        예비중2,
        예비중3,
        고1,
        고2,
        고3,
        예비고1,
        예비고2,
        예비고3
    }
}
