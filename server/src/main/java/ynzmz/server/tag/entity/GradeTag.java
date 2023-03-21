package ynzmz.server.tag.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class GradeTag {
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
        고1,
        고2,
        고3,
        N수
    }
}
