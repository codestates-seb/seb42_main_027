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
        사회탐구,
        일반사회,
        윤리,
        지리,
        역사,
        통합사회,
        과학탐구,
        물리학,
        지구과학,
        화학,
        생명과학,
        통합과학,
        제2외국어,
        대학별고사,
        사탐,
        과탐,
        기타
    }
}
