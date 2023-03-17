package ynzmz.server.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.mappingtable.lecture.LectureGradeTag;
import ynzmz.server.tag.mappingtable.lecture.LecturePlatformTag;
import ynzmz.server.tag.mappingtable.lecture.LectureSubjectTag;
import ynzmz.server.tag.mappingtable.question.QuestionSubjectTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherGradeTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherPlatformTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherSubjectTag;
import ynzmz.server.tag.repository.*;
import ynzmz.server.tag.repository.lecture.LectureGradeTagRepository;
import ynzmz.server.tag.repository.lecture.LecturePlatformTagRepository;
import ynzmz.server.tag.repository.lecture.LectureSubjectTagRepository;
import ynzmz.server.tag.repository.question.QuestionSubjectTagRepository;
import ynzmz.server.tag.repository.teacher.TeacherGradeTagRepository;
import ynzmz.server.tag.repository.teacher.TeacherPlatformTagRepository;
import ynzmz.server.tag.repository.teacher.TeacherSubjectTagRepository;
import ynzmz.server.teacher.entity.Teacher;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TagService {
    private final GradeTagRepository gradeTagRepository;
    private final PlatformTagRepository platformTagRepository;
    private final SubjectTagRepository subjectTagRepository;

    private final TeacherGradeTagRepository teacherGradeTagRepository;
    private final TeacherPlatformTagRepository teacherPlatformTagRepository;
    private final TeacherSubjectTagRepository teacherSubjectTagRepository;

    private final LectureGradeTagRepository lectureGradeTagRepository;
    private final LecturePlatformTagRepository lecturePlatformTagRepository;
    private final LectureSubjectTagRepository lectureSubjectTagRepository;

    private final QuestionSubjectTagRepository questionSubjectTagRepository;

    public GradeTag.Grade findGradeTag(String grade){
        return GradeTag.Grade.valueOf(grade);
    }
    public PlatformTag.Platform findPlatformTag(String platform){
        return PlatformTag.Platform.valueOf(platform);
    }

    public SubjectTag.Subject findSubjectTag(String subject) {
        return SubjectTag.Subject.valueOf(subject);
    }
    public List<GradeTag.Grade> findGradeTags(List<String> grades){
        List<GradeTag.Grade> gradeTags = new ArrayList<>();
        for(String grade : grades){
            gradeTags.add(GradeTag.Grade.valueOf(grade));
        }
        return gradeTags;
    }
    public List<PlatformTag.Platform> findPlatformTags(List<String> platforms){
        List<PlatformTag.Platform> platformTags = new ArrayList<>();
        for(String platform : platforms){
            platformTags.add(PlatformTag.Platform.valueOf(platform));
        }
        return platformTags;
    }
    public List<SubjectTag.Subject> findSubjectTags(List<String> subjects){
        List<SubjectTag.Subject> subjectTags = new ArrayList<>();
        for(String subject : subjects){
            subjectTags.add(SubjectTag.Subject.valueOf(subject));
        }
        return subjectTags;
    }

    public void createTeacherTag(Teacher teacher,
                                 List<GradeTag.Grade> grades,
                                 List<PlatformTag.Platform> platforms,
                                 List<SubjectTag.Subject> subjects){
        for(GradeTag.Grade grade : grades){
            GradeTag findGradeTag = gradeTagRepository.findTagByGrade(grade);
            TeacherGradeTag teacherTag = TeacherGradeTag.builder()
                    .teacher(teacher)
                    .gradeTag(findGradeTag)
                    .build();
            teacherGradeTagRepository.save(teacherTag);
        }
        for(PlatformTag.Platform platform : platforms){
            PlatformTag findPlatformTag = platformTagRepository.findTagByPlatform(platform);
            TeacherPlatformTag teacherTag = TeacherPlatformTag.builder()
                    .teacher(teacher)
                    .platformTag(findPlatformTag)
                    .build();
            teacherPlatformTagRepository.save(teacherTag);
        }
        for(SubjectTag.Subject subject : subjects){
            SubjectTag findSubjectTag = subjectTagRepository.findTagBySubject(subject);
            TeacherSubjectTag teacherTag = TeacherSubjectTag.builder()
                    .teacher(teacher)
                    .subjectTag(findSubjectTag)
                    .build();
            teacherSubjectTagRepository.save(teacherTag);
        }
    }

    public void createQuestionTag(Question question,
                                  List<SubjectTag.Subject> subjects){
        for(SubjectTag.Subject subject : subjects){
            SubjectTag findSubjectTag = subjectTagRepository.findTagBySubject(subject);
            QuestionSubjectTag questionSubjectTag = QuestionSubjectTag.builder()
                    .question(question)
                    .subjectTag(findSubjectTag)
                    .build();
            questionSubjectTagRepository.save(questionSubjectTag);
        }
    }

    @Transactional
    public void deleteAllTeacherTagByTeacher(Teacher teacher) {
        teacherGradeTagRepository.deleteAllTeacherGradeTagByTeacher(teacher);
        teacherPlatformTagRepository.deleteAllTeacherPlatformTagByTeacher(teacher);
        teacherSubjectTagRepository.deleteAllTeacherSubjectTagByTeacher(teacher);
    }

    @Transactional
    public void deleteAllQuestionTagByQuestion(Question question) {
        questionSubjectTagRepository.deleteAllTeacherGradeTagByQuestion(question);
    }

    public void createLectureTag(Lecture lecture,
                                 List<GradeTag.Grade> grades,
                                 List<PlatformTag.Platform> platforms,
                                 List<SubjectTag.Subject> subjects){
        for(GradeTag.Grade grade : grades){
            GradeTag findGradeTag = gradeTagRepository.findTagByGrade(grade);
            LectureGradeTag lectureTag = LectureGradeTag.builder()
                    .lecture(lecture)
                    .gradeTag(findGradeTag)
                    .build();
            lectureGradeTagRepository.save(lectureTag);
        }
        for(PlatformTag.Platform platform : platforms){
            PlatformTag findPlatformTag = platformTagRepository.findTagByPlatform(platform);
            LecturePlatformTag lectureTag = LecturePlatformTag.builder()
                    .lecture(lecture)
                    .platformTag(findPlatformTag)
                    .build();
            lecturePlatformTagRepository.save(lectureTag);
        }
        for(SubjectTag.Subject subject : subjects){
            SubjectTag findSubjectTag = subjectTagRepository.findTagBySubject(subject);
            LectureSubjectTag lectureTag = LectureSubjectTag.builder()
                    .lecture(lecture)
                    .subjectTag(findSubjectTag)
                    .build();
            lectureSubjectTagRepository.save(lectureTag);
        }
    }

    @Transactional
    public void deleteAllLectureTagByLecture(Lecture lecture) {
        lectureGradeTagRepository.deleteAllTeacherGradeTagByLecture(lecture);
        lecturePlatformTagRepository.deleteAllLecturePlatformTagByLecture(lecture);
        lectureSubjectTagRepository.deleteAllLectureSubjectTagByLecture(lecture);
    }

}
