-- 국어
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
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '한국사');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '역사');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 이다지 한국사 20시간의 기적 - 개념완성','♥ 두근거리는 스토리텔링 생생한 한국사 ♥', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
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

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO lecture_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);



INSERT INTO teacher (image_url, introduction, name, star_point_average, total_review_count)
VALUES
    ('없음','있는 그대로의 수능 국어', '강민철', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '힘이 들면... 힘을 내면 되잖아?'),
    (@teacher_id, '여러분 수학 과탐도 하는데, 국어 아무것도 아니에요');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '現 메가스터디 국어과 강사');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 강민철의 기출 분석 [독서]','진하게 분석하는 평가원 기출, 강민철 시그니처!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO lecture_platform_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO lecture_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[입문] 2024 강민철의 기본 - 예비고3 -','[2024 NEW] 수능 국어의 기본(基本)과 근본(根本)', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '예비고3');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO lecture_platform_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO lecture_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[입문] 강민철의 기본 -고전시가-','주요 작품을 익히면, 다른 작품도 읽힙니다.', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO lecture_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO lecture_platform_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO lecture_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

-- 수학
INSERT INTO teacher (image_url, introduction, name, star_point_average, total_review_count)
VALUES
    ('없음','시작부터 끝까지 수학은 누구나', '현우진', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '힘이 들면... 힘을 내면 되잖아?'),
    (@teacher_id, '여러분 수학 과탐도 하는데, 국어 아무것도 아니에요');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '미국 Stanford University, B.S. Mathematics
(스탠포드 대학교 수학과 졸업)'),
    (@teacher_id, '現 메가스터디 수학영역 대표강사'),
    (@teacher_id, '대치동 현장강의 문/이과 최다 수강생 보유');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);