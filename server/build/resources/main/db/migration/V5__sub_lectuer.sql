SET @teacher_id = (SELECT teacher_id FROM teacher where name = '이다지');


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 이다지 한국사 20시간의 기적 - 개념완성','♥ 두근거리는 스토리텔링 생생한 한국사 ♥', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO lecture_platform_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '한국사');
INSERT INTO lecture_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('이다지도 확실한 한국사 내신완성','前 자사고 선생님이 알려주는 내신 1등급의 비밀', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO lecture_platform_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '한국사');
INSERT INTO lecture_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('이다지도 확실한 통합사회 내신완성','★이유 있는 통합사회 1위★ 더 꼼꼼한 심화 개념 + 최신 트렌드 반영!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO lecture_platform_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '통합사회');
INSERT INTO lecture_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);