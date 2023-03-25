package ynzmz.server.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.entity.lecture.LectureGradeTag;
import ynzmz.server.tag.entity.lecture.LecturePlatformTag;
import ynzmz.server.tag.entity.lecture.LectureSubjectTag;
import ynzmz.server.tag.entity.teacher.TeacherGradeTag;
import ynzmz.server.tag.entity.teacher.TeacherPlatformTag;
import ynzmz.server.tag.entity.teacher.TeacherSubjectTag;
import ynzmz.server.tag.manager.LectureTagManager;
import ynzmz.server.tag.manager.TagManager;
import ynzmz.server.tag.manager.TeacherTagManager;
import ynzmz.server.board.teacher.entity.Teacher;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagManager tagManager;
    private final TeacherTagManager teacherTagManager;
    private final LectureTagManager lectureTagManager;

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
        return findTags(grades, GradeTag.Grade.class);
    }
    public List<PlatformTag.Platform> findPlatformTags(List<String> platforms){
        return findTags(platforms, PlatformTag.Platform.class);
    }
    public List<SubjectTag.Subject> findSubjectTags(List<String> subjects){
        return findTags(subjects, SubjectTag.Subject.class);
    }

    public void createTeacherTag(Teacher teacher,
                                 List<GradeTag.Grade> grades,
                                 List<PlatformTag.Platform> platforms,
                                 List<SubjectTag.Subject> subjects){
        for(GradeTag.Grade grade : grades){
            GradeTag findGradeTag = tagManager.findTagByGrade(grade);
            TeacherGradeTag teacherTag = TeacherGradeTag.builder()
                    .teacher(teacher)
                    .gradeTag(findGradeTag)
                    .build();
            teacherTagManager.saveTeacherGradeTag(teacherTag);
        }
        for(PlatformTag.Platform platform : platforms){
            PlatformTag findPlatformTag = tagManager.findTagByPlatform(platform);
            TeacherPlatformTag teacherTag = TeacherPlatformTag.builder()
                    .teacher(teacher)
                    .platformTag(findPlatformTag)
                    .build();
            teacherTagManager.saveTeacherPlatformTag(teacherTag);
        }
        for(SubjectTag.Subject subject : subjects){
            SubjectTag findSubjectTag = tagManager.findTagBySubject(subject);
            TeacherSubjectTag teacherTag = TeacherSubjectTag.builder()
                    .teacher(teacher)
                    .subjectTag(findSubjectTag)
                    .build();
            teacherTagManager.saveTeacherSubjectTag(teacherTag);
        }
    }

    @Transactional
    public void deleteAllTeacherTagByTeacher(Teacher teacher) {
        teacherTagManager.deleteAllTeacherTagByTeacher(teacher);
    }

    public void createLectureTag(Lecture lecture,
                                 List<GradeTag.Grade> grades,
                                 List<PlatformTag.Platform> platforms,
                                 List<SubjectTag.Subject> subjects){
        for(GradeTag.Grade grade : grades){
            GradeTag findGradeTag = tagManager.findTagByGrade(grade);
            LectureGradeTag lectureTag = LectureGradeTag.builder()
                    .lecture(lecture)
                    .gradeTag(findGradeTag)
                    .build();
            lectureTagManager.saveLectureGradeTag(lectureTag);
        }
        for(PlatformTag.Platform platform : platforms){
            PlatformTag findPlatformTag = tagManager.findTagByPlatform(platform);
            LecturePlatformTag lectureTag = LecturePlatformTag.builder()
                    .lecture(lecture)
                    .platformTag(findPlatformTag)
                    .build();
            lectureTagManager.saveLecturePlatformTag(lectureTag);
        }
        for(SubjectTag.Subject subject : subjects){
            SubjectTag findSubjectTag = tagManager.findTagBySubject(subject);
            LectureSubjectTag lectureTag = LectureSubjectTag.builder()
                    .lecture(lecture)
                    .subjectTag(findSubjectTag)
                    .build();
            lectureTagManager.saveLectureSubjectTag(lectureTag);
        }
    }

    @Transactional
    public void deleteAllLectureTagByLecture(Lecture lecture) {
        lectureTagManager.deleteAllLectureTagByLecture(lecture);
    }

    public <T extends Enum<T>> List<T> findTags(List<String> tagStrings, Class<T> tagType) {
        return tagStrings.stream().map(tag -> Enum.valueOf(tagType, tag)).collect(Collectors.toList());
    }
}
