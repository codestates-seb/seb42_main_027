INSERT INTO teacher (image_url, introduction, name, star_point_average, total_review_count)
VALUES
    ('없음','너의 역사를 빛내줄 이다지 선생님', '이다지', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '3월 학평부터 좋은 결과 받아라, 자신감과 확신이 저절로 따라온다.'),
    (@teacher_id, '수능 한국사를 미리 끝내면 내년에 과목 간 밸런스를 끝까지 유지하고 기적처럼 도약할 거야^^');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '유일하게 평가원이 검토한 EBS 한국사 교재 검토'),
    (@teacher_id, '(비상) 동아시아사 교과서 집필'),
    (@teacher_id, '전) 인천 하늘고(자사고)'),
    (@teacher_id, '2014년 EBSi 사회탐구 영역 대상 수상'),
    (@teacher_id, '전) EBSi 인기 강사'),
    (@teacher_id, '정교사 1급 자격증 보유'),
    (@teacher_id, '이화여대 사학과 수석 졸업'),
    (@teacher_id, '현) 메가스터디 온/오프라인');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '한국사');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '역사');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '통합사회');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

