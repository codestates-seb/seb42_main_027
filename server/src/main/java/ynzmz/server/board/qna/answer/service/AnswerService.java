package ynzmz.server.board.qna.answer.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynzmz.server.board.qna.answer.repository.AnswerRepository;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.board.qna.answer.entity.Answer;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer){
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findAnswerById(answer.getAnswerId());
        Optional.ofNullable(answer.getContent()).ifPresent(findAnswer::setContent);
        Optional.ofNullable(answer.getModifiedAt()).ifPresent(findAnswer::setModifiedAt);
        return answerRepository.save(findAnswer);
    }
    @Transactional
    public void deleteAnswer(long answerId) {
        answerRepository.deleteById(answerId);
    }

    public List<Answer> findAnswersByMemberId(long memberId){
        return answerRepository.findByMemberId(memberId);
    }

    public Answer findAnswerById(long answerId) {
        Optional<Answer> optionalQuestion = answerRepository.findById(answerId);

        return optionalQuestion.orElseThrow(()-> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }
    public Optional<Answer> findOptionalAnswerById(long answerId) {
        return answerRepository.findById(answerId);
    }

}
