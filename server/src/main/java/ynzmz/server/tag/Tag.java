package ynzmz.server.tag;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Enumerated(value = EnumType.STRING)
    private Type type;

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
        특강,
        여름방학,
        메가스터디,
        인프런,
        EBS,

    }
}
