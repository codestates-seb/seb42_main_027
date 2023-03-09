package ynzmz.server.lecture.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ynzmz.server.lecture.repository.LectureRepository;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
}
