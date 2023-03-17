package ynzmz.server.tag.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PlatformTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long platformTagId;

    @Enumerated(value = EnumType.STRING)
    @Column(unique = true)
    private Platform platform;

    public PlatformTag(Platform platform) {
        this.platform = platform;
    }

    public enum Platform {
        메가스터디,
        대성마이맥,
        이투스,
        EBS,
        에듀윌,
        기타
    }
}
