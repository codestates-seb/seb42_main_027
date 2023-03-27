package ynzmz.server.tag.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;
import ynzmz.server.tag.repository.GradeTagRepository;
import ynzmz.server.tag.repository.PlatformTagRepository;
import ynzmz.server.tag.repository.SubjectTagRepository;

@Component
@RequiredArgsConstructor
public class TagManager {
    private final GradeTagRepository gradeTagRepository;
    private final PlatformTagRepository platformTagRepository;
    private final SubjectTagRepository subjectTagRepository;

    public GradeTag findTagByGrade(GradeTag.Grade grade) {
        return gradeTagRepository.findTagByGrade(grade);
    }

    public PlatformTag findTagByPlatform(PlatformTag.Platform platform) {
        return platformTagRepository.findTagByPlatform(platform);
    }

    public SubjectTag findTagBySubject(SubjectTag.Subject subject){
        return subjectTagRepository.findTagBySubject(subject);
    }
}
