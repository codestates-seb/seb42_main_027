package ynzmz.server.question.question.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.member.entity.Member;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.tag.mappingtable.question.QuestionSubjectTag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long questionId;

    String title;
    String content;
    long viewCount;
    long voteCount;
    private long answerCount;
    String createdAt;
    String modifiedAt;
    private long adoptAnswerId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<QuestionSubjectTag> subjectTags = new ArrayList<>();

}
