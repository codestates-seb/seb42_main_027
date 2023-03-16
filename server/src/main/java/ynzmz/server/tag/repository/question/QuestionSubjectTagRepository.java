package ynzmz.server.tag.repository.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.question.question.entity.Question;
import ynzmz.server.tag.mappingtable.question.QuestionSubjectTag;

@Repository
public interface QuestionSubjectTagRepository extends JpaRepository<QuestionSubjectTag,Long> {
    void deleteAllTeacherGradeTagByQuestion(Question question);
}
