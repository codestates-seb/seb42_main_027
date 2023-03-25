package ynzmz.server.tag.entity.lecture;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.tag.entity.PlatformTag;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "map_lecute_platfom_tag")
public class LecturePlatformTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturePlatformTagId;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    @JsonBackReference
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "platform_tag_id")
    @JsonBackReference
    private PlatformTag platformTag;
}
