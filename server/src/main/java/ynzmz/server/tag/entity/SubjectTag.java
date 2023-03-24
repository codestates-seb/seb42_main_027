package ynzmz.server.tag.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class SubjectTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectTagId;

    @Enumerated(value = EnumType.STRING)
    @Column(unique = true)
    private Subject subject;

    public SubjectTag(Subject subject) {
        this.subject = subject;
    }

    public enum Subject {
        국어,
        영어,
        수학,
        한국사,
        한문,

        사탐전체,
        일반사회,
        윤리,
        지리,
        역사,
        경제,
        정치와법,
        과탐전체,
        물리학,
        화학,
        생명과학,
        지구과학,
        일반과학,

        기타전체,
        제2외국어,
        대학별고사,
        그외,
        공지
    }
}
