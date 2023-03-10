package ynzmz.server.lecturereviewpost.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.BusinessLogicException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.lecturereviewpost.repository.LectureReviewPostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureReviewPostService {
    private final LectureReviewPostRepository lectureReviewPostRepository;

    public LectureReviewPost createLectureReviewPost(LectureReviewPost lectureReviewPost) {
        return lectureReviewPostRepository.save(lectureReviewPost);
    }

    public LectureReviewPost updateLectureReviewPost(LectureReviewPost lectureReviewPost) {
        LectureReviewPost findLectureReviewPost = findLectureReviewPostById(lectureReviewPost.getLectureReviewPostId());

        Optional.ofNullable(lectureReviewPost.getTitle()).ifPresent(findLectureReviewPost::setTitle);
        Optional.ofNullable(lectureReviewPost.getContent()).ifPresent(findLectureReviewPost::setContent);
        Optional.of(lectureReviewPost.getStarPoint()).ifPresent(findLectureReviewPost::setStarPoint);
        Optional.ofNullable(lectureReviewPost.getModifiedAt()).ifPresent(findLectureReviewPost::setModifiedAt);


        return lectureReviewPostRepository.save(findLectureReviewPost);
    }

    public Page<LectureReviewPost> findAllLectureReviewPosts(int page, int size) {
        return lectureReviewPostRepository.findAll(PageRequest.of(page, size, Sort.by("lectureReviewPostId").descending()));
    }

    public Page<LectureReviewPost> findLectureReviewPostsByLecture(long lectureId, int page, int size) {
        return lectureReviewPostRepository.findLectureReviewPostByLectureLectureId(lectureId, PageRequest.of(page, size, Sort.by("lectureReviewPostId").descending()));
    }
    public Page<LectureReviewPost> findLectureReviewPostsByTeacher(long teacherId, int page, int size) {
        return lectureReviewPostRepository.findLectureReviewPostByLectureTeacherTeacherId(teacherId, PageRequest.of(page, size, Sort.by("teacherId").descending()));
    }



    public LectureReviewPost findLectureReviewPostById(long lectureReviewPostId) {
        Optional<LectureReviewPost> lectureReviewPost = lectureReviewPostRepository.findById(lectureReviewPostId);
        return lectureReviewPost.orElseThrow(() -> new BusinessLogicException(ExceptionCode.LECTURE_REVIEW_POST_NOT_FOUND));
    }

    public double getLectureReviewPostAverageStarPoint(LectureReviewPost lectureReviewPost){
        List<LectureReviewPost> lectureReviewPosts = lectureReviewPostRepository.findAllLecturesByLectureReviewPost(lectureReviewPost.getLecture().getLectureId());
        double starPoint = 0;
        for(LectureReviewPost post : lectureReviewPosts ){
            starPoint += post.getStarPoint();
        }
        return starPoint/lectureReviewPosts.size();
    }
}
