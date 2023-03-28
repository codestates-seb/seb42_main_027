package ynzmz.server.board.qna.question.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynzmz.server.board.qna.question.repository.QuestionRepository;
import ynzmz.server.global.error.exception.BusinessLogicException;
import ynzmz.server.global.error.exception.ExceptionCode;
import ynzmz.server.member.entity.Member;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.answer.service.AnswerService;
import ynzmz.server.board.qna.question.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    @Transactional
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findQuestionById(question.getQuestionId());
        Optional.ofNullable(question.getTitle()).ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContent()).ifPresent(findQuestion::setContent);
        Optional.ofNullable(question.getCategory()).ifPresent(findQuestion::setCategory);
        Optional.ofNullable(question.getModifiedAt()).ifPresent(findQuestion::setModifiedAt);
        Optional.ofNullable(question.getUploadImages()).ifPresent(findQuestion::setUploadImages);
        return questionRepository.save(findQuestion);
    }

    @Transactional
    public void deleteQuestion(long questionId) {
        questionRepository.deleteById(questionId);
    }

    public Page<Question> findAllQuestions(String title,String sort, int page, int size) {
        List<Question> noticeQuestions = questionRepository.findNoticeListQuestions(PageRequest.of(0, 3, Sort.by("questionId").descending()));
        Page<Question> allQuestions = questionRepository.findAllQuestions(title, PageRequest.of(page, size, Sort.by(sort).descending()));
        List<Question> mergedList = new ArrayList<>(noticeQuestions);
        allQuestions.forEach(question -> {
            if(!noticeQuestions.contains(mergedList)) {
                mergedList.add(question);
            }
        });

        int start = (int) allQuestions.getPageable().getOffset();
        int end = Math.min((start + allQuestions.getPageable().getPageSize()), mergedList.size());
        return new PageImpl<>(mergedList.subList(start, end), allQuestions.getPageable(), mergedList.size());
    }

    public Page<Question> findQuestionsByCategory(String category, String title,String sort, int page, int size) {
        List<Question> noticeQuestions = questionRepository.findNoticeListQuestions(PageRequest.of(0, 3, Sort.by("questionId").descending()));
        Page<Question> allQuestions = questionRepository.findQuestionByCategory(category, title, PageRequest.of(page, size, Sort.by(sort).descending()));
        List<Question> mergedList = new ArrayList<>(noticeQuestions);
        allQuestions.forEach(question -> {
            if(!noticeQuestions.contains(mergedList)) {
                mergedList.add(question);
            }
        });

        int start = (int) allQuestions.getPageable().getOffset();
        int end = Math.min((start + allQuestions.getPageable().getPageSize()), mergedList.size());
        return new PageImpl<>(mergedList.subList(start, end), allQuestions.getPageable(), mergedList.size());
    }

    public Page<Question> findQuestionsByNotice(String sort, int page, int size) {
        return questionRepository.findNoticePageQuestions(PageRequest.of(page, size, Sort.by(sort).descending()));
    }


    public Page<Question> findQuestionsByMemberId(long memberId, int page, int size) {
        return questionRepository.findByMemberId(memberId, PageRequest.of(page, size, Sort.by("questionId").descending()));
    }

    public List<Question> findQuestionsByMemberId(long memberId){
        return questionRepository.findByMemberId(memberId);
    }

    @Transactional
    public Question findQuestionById(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        return optionalQuestion.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }
    @Transactional
    public Optional<Question> findOptionalQuestionById(long questionId) {
        return questionRepository.findById(questionId);
    }

    @Transactional
    public void setViewCount(Question question) {
        question.setViewCount(question.getViewCount() + 1);
        questionRepository.save(question);
    }

    public void adoptAnswer(long questionId, Answer answer, Member member) {

        //질문글 등록한사람 본인이 답변채택 할수있어야함.
        Question question = checkTheAuthorOfTheQuestion(member, questionId);
        log.info("질문글 찾기 완료");
        if (question.getAdoptAnswerId() == null) { //만약 채택되있는게 없으면 신청한걸로 채택
            question.setAdoptAnswerId(answer.getAnswerId());
            answer.setAdoptStatus(Answer.AdoptStatus.TRUE);
            log.info("처음 채택시 그냥 채택");
        } else if ((long) question.getAdoptAnswerId() != answer.getAnswerId()) { // 다른것이 채택되어있으면, 기존꺼 취소후 신청한걸로 채택
            Answer oldAdoptAnswer = answerService.findAnswerById(question.getAdoptAnswerId()); // 기존꺼 답변 채택취소로변경
            oldAdoptAnswer.setAdoptStatus(Answer.AdoptStatus.FALSE); // 기존꺼 답변 채택취소로변경
            question.setAdoptAnswerId(answer.getAnswerId()); //새로운거 채택
            answer.setAdoptStatus(Answer.AdoptStatus.TRUE); //새로운거 채택
            log.info("다른거 채택되있을시 기존값 변경후 채택");
        } else if ((long) question.getAdoptAnswerId() == answer.getAnswerId()) { // 채택된것을 다시 채택을 누르면 채택취소되게
            question.setAdoptAnswerId(null); // 질문에는 아무것도 없는상태로
            answer.setAdoptStatus(Answer.AdoptStatus.FALSE); //답변글을  취소로 변경
            log.info("채택 두번시 채택취소");
        }
        questionRepository.save(question);
    }

    private Question checkTheAuthorOfTheQuestion(Member member, long questionId) {
        List<Question> questions = member.getQuestions();
        log.info("회원의 질문 요청 리스트 확인완료");

        for (Question question : questions) {
            long findQuestionId = question.getQuestionId();
            if (findQuestionId == questionId) return question;
            log.info("요청받은 질문글 특정완료");
        }
        log.warn("게시글 쓴사람이 아닌데 채택요청함");
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }
}
