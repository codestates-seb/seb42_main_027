-- 국어
INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('너의 역사를 빛내줄 이다지 선생님', '이다지', 0, 0);
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
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '한국사');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '역사');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 이다지 한국사 20시간의 기적 - 개념완성','♥ 두근거리는 스토리텔링 생생한 한국사 ♥', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '한국사');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('이다지도 확실한 한국사 내신완성','前 자사고 선생님이 알려주는 내신 1등급의 비밀', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '한국사');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('이다지도 확실한 통합사회 내신완성','★이유 있는 통합사회 1위★ 더 꼼꼼한 심화 개념 + 최신 트렌드 반영!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);



INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('있는 그대로의 수능 국어', '강민철', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '힘이 들면... 힘을 내면 되잖아?'),
    (@teacher_id, '여러분 수학 과탐도 하는데, 국어 아무것도 아니에요');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '現 메가스터디 국어과 강사');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 강민철의 기출 분석 [독서]','진하게 분석하는 평가원 기출, 강민철 시그니처!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[입문] 2024 강민철의 기본 - 예비고3 -','[2024 NEW] 수능 국어의 기본(基本)과 근본(根本)', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[입문] 강민철의 기본 -고전시가-','주요 작품을 익히면, 다른 작품도 읽힙니다.', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


-- 수학
INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('시작부터 끝까지 수학은 누구나', '현우진', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '내가 왜 성공했을까? 재능일까, 노력일까, 운일까? 셋 다지.'),
    (@teacher_id, '얘들아, 붕어빵처럼 살면 안 돼. 누군가 와서 뒤집어주겠지 하는 생각으로 살면 다 타죽어.');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '미국 Stanford University, B.S. Mathematics(스탠포드 대학교 수학과 졸업)'),
    (@teacher_id, '現 메가스터디 수학영역 대표강사'),
    (@teacher_id, '대치동 현장강의 문/이과 최다 수강생 보유');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 현우진의 드릴 - 수학l (공통)','■ 2등급과 1등급 사이 반드시 존재하는 훈련 ■', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 현우진의 드릴 - 수학ll (공통)','■ 2등급과 1등급 사이 반드시 존재하는 훈련 ■', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 현우진의 뉴런 - 미적분 (선택)','■ 수능 실전개념 뉴런 & 복습을 위한 시냅스 ■', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2023 현우진의 뉴런 - 기하 (선택)','■ 수능 실전개념 뉴런 & 복습을 위한 시냅스 ■', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);



-- 수학
INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('수능 영어는 ALL TIME', '조정식', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '이 단어도 모르는 애들은 "영어 어디서 배우셨어요?" 그러면 "독학했어요"라고 하세요'),
    (@teacher_id, '이거 모르면 엄마한테 물어봐. 엄마도 놀라실거야.');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '고려대학교 졸업'),
    (@teacher_id, '메가스터디 온라인 영어 대표강사'),
    (@teacher_id, '메가스터디 러셀 영어 대표강사');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 3월 학평 대비 조정식 모의고사','* 첫 모의고사 전, 꼭 풀어보세요! *', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024 NEW] 믿어봐! 글 읽는 법을 알려줄게 (현장버전)','영어 지문을 읽고 정보를 정리하는 학습', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024 NEW] 시작해! 수능 영어의 처음부터','수능 영어의 시작, 기초, 기본! 영어가 처음이라면 반드시!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('실전에 강한 수능 지리 TOP', '이기상', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '떠오르는 것만이 니것이다.'),
    (@teacher_id, '좋은 습관이 인생을 바청주');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대학교 지리교육과 졸업'),
    (@teacher_id, '現) 메가스터디 사회탐구 강사'),
    (@teacher_id, '現) 거인사탐 전문학원 원장'),
    (@teacher_id, '前) 반포고등학교 교사'),
    (@teacher_id, '前) 우신고등학교 교사'),
    (@teacher_id, '前) EBSi 경제지리 담당(2004 ~ 2005)');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지리');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[한국지리] 2024 이것이 개념이다','기초부터 심화까지 모든 것이 담긴 실전개념!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[세계지리] 2024 이것이 개념이다','기초부터 심화까지 모든 것이 담긴 실전개념!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[세계지리] 2024 이것이 만점복습노트다','이것이 개념이다 초단기 복습 버전', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[한국지리] 2024 이것이 만점복습노트다','이것이 개념이다 초단기 복습 버전', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('윤리 잘 아는 평가원 베프', '김종익', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '앞으로도 샘만 믿고 쭉 따라온다면 등급컷 걱정 없는 윤리 점수 즉 만점!!');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '現) 고려대학교 윤리교육과 석사'),
    (@teacher_id, '現) 메가스터디 사회탐구 윤리 강사'),
    (@teacher_id, '前) EBSi 사회탐구 윤리 대표 강사/대표 강사상 수상 (2018)'),
    (@teacher_id, '前) 한국교육과정평가원 생윤,윤사 교과서 검정위원'),
    (@teacher_id, '前) 한국교육과정평가원 생활과윤리 현장 적합성 검토위원'),
    (@teacher_id, '前) 인문계 고등학교 1급 정교사 (2008-2018)'),
    (@teacher_id, '前) EBS 수능윤리 검수교사'),
    (@teacher_id, '前) EBS 수특/수완 생윤,윤사 검토위원 (2013~2018년)');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '윤리');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[윤리와사상] 2024 잘 생긴 개념','평가원이 원하는 진짜 개념과 출제 포인트!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '윤리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[생활과윤리] 2024 이것만 들어도 3등급 나온다 (삼삼한 개념집)','윤알못을 위한 수능 입문 짤강의!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '윤리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[생활과윤리] 잘 아는 윤리 내신 완성','내신도 역시 김종익!', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '윤리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('신뢰의 물리 만점 전략가', '배기범', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '내년에도 이런 식으로 나오면, 물2 강의를 그만두는 수가 있습니다.');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대 물리교육과 졸업'),
    (@teacher_id, '(주) YJ Creative 대표'),
    (@teacher_id, '도서 출판 파란북스 대표'),
    (@teacher_id, '메가스터디 물리학 대표 강사'),
    (@teacher_id, '메가엠디 PEET 물리추론 전임교수');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '물리학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[물리학l] 2024 필수본 개념완성 <기본부터 심화까지>','※ 필히 들어야 하는 수능 물리학의 본질적 접근! 필수본 ※', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '물리학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[물리학l] 2023 필수본 개념완성 <기본부터 심화까지>','※ 개념부터 앞서 나가고 싶다면, 필수본은 필수 ※', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '물리학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[물리학ll] 2023 필수본 개념완성 <기본부터 심화까지>','※ 개념부터 앞서 나가고 싶다면, 필수본은 필수 ※', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '메가스터디');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '물리학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);