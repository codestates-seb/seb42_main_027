package ynzmz.server.tag.mappingtable.question;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.tag.entity.SubjectTag;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class QuestionSubjectTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionSubjectTagId;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;

    @ManyToOne
    @JoinColumn(name = "subject_tag_id")
    @JsonBackReference
    private SubjectTag subjectTag;
}
