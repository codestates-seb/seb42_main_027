package ynzmz.server.lecturereviewpost.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.lecturereviewpost.repository.LectureReviewPostRepository;

@Service
@RequiredArgsConstructor
public class LectureReviewPostService {
    private final LectureReviewPostRepository lectureReviewPostRepository;
}
