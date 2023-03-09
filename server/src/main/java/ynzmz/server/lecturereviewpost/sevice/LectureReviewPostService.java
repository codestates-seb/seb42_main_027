package ynzmz.server.lecturereviewpost.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.lecturereviewpost.repository.LectureReviewPostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureReviewPostService {
    private final LectureReviewPostRepository lectureReviewPostRepository;

    public LectureReviewPost createLectureReviewPost(LectureReviewPost lectureReviewPost) {
        return lectureReviewPostRepository.save(lectureReviewPost);
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
