
INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES ('국어, 치열하게 독하게', '유대종', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '쌰발!'),
    (@teacher_id, '여러분들은 대답을 하지 않으면 20XX학년도 수능을 보게됩니다.');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대학교 철학과 졸업'),
    (@teacher_id, '現 대성마이맥 국어과 강사'),
    (@teacher_id, '現 큰울림 국어연구소 오프라인 강사 (대치두각, 대치세정 등)'),
    (@teacher_id, '前 스카이에듀 국어과 강사'),
    (@teacher_id, '前 메가스터디 국어과 강사'),
    (@teacher_id, '前 오르비 온라인 강사'),
    (@teacher_id, '저서)  국어 치열하게 독하게'),
    (@teacher_id, '저서) 국어 더함 모의고사');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 언어 매체 FULL 강좌 패키지', '','진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 인셉션(Inception) FULL패키지', '','진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);



INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('Your realizer', '김승리', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '생각대로 되는 게 능사는 아니다.'),
    (@teacher_id, '생각보다 큰 일이 아니다.');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '고려대학교 졸업'),
    (@teacher_id, '現 대성마이맥 인터넷 강사'),
    (@teacher_id, '現 시대인재학원(대치,목동) 출강'),
    (@teacher_id, '現 강대마이맥(대치) 출강'),
    (@teacher_id, '現 승리하다(분당) 출강');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '국어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('필연의 길을따라 집요하게!', '한석원', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '미적분의 세계에 오신 것을 진심으로 환영합니다.'),
    (@teacher_id, '안광지배철!.');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대학교 공과대학 졸업'),
    (@teacher_id, '現 대성마이맥 온라인 강사'),
    (@teacher_id, '現 깊은생각 수학학원 (원장)'),
    (@teacher_id, '前 강남구청 인터넷 수능방송'),
    (@teacher_id, '前 티치미 대입학원 원장');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('NEXT LEVEL MATH', '배성민', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '수능장에서 교과서에 나온 정석적인 풀이가 안 보일수도 있다고. 다 챙겨가자야. 어떻게든 문제를 맞춰야 점수가 나와.'),
    (@teacher_id, '항상 긍정적으로 살라구. 긍정적으로 살아야 해!'),
    (@teacher_id, '공부하다가 모르면 넘어가라구. 어느정도 지식이 축적되고 처음부터 다시보면 조금씩 이해가 된다고. 모른다고 붙잡지마. 알겠지?');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대학교 자연과학대학 졸업'),
    (@teacher_id, '現 대성마이맥 온라인 강사'),
    (@teacher_id, '前 강남구청 인터넷 수능방송'),
    (@teacher_id, '前 스카이에듀 강사');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 빌드업 [수학 I + 수학 II + 미적분]PACKAGE','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 빌드업 [수학 I + 수학 II + 확률과통계]PACKAGE','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 워밍업 플러스 [수학 I + 수학 II + 미적분]PACKAGE','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 워밍업 플러스 [수학 I + 수학 II + 기하]PACKAGE','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '수학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('REAL[공감영어]', '이명학', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, 'welfare, Farewell. 다신 보지 말자'),
    (@teacher_id, '어법은 의심이고 구문은 신뢰다'),
    (@teacher_id, '자리가 품사를 말한다');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '연세대 영어영문학과 졸업'),
    (@teacher_id, '現 대성마이맥 온라인 강사'),
    (@teacher_id, '現 강남대성학원 강사'),
    (@teacher_id, '前 EBS [한국교육방송] 수능강의');



SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 기본개념 패키지 [일리+Syntax1.0+R’gorithm]','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 구문&지문 독해 패키지 [Syntax1.0+R’gorithm]','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] Read N’ Logic[빈칸완성]+[순서/삽입] 패키지','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
    VALUES ('[2024] 이명학의 수능루틴','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('KISS:Keep It Short and Simple', '션티', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '네! 하이 가이즈 션티입니다.'),
    (@teacher_id, 'Anyway!');


INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '한국외대 영어통번역학과 졸업'),
    (@teacher_id, '국내파 통역장교'),
    (@teacher_id, '4성장군 통역');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '영어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);



INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('한국사, 용기를 찾아라', '권용기', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '구굴게이(구석기 / 굴,막집 / 밀개,긁개,찌르개 / 이동생활'),
     (@teacher_id, '광인=정신병남=머리에벌받음(조선후기)');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '한양대 사학과 졸업'),
    (@teacher_id, '한양대 역사학 석사 졸업'),
    (@teacher_id, '명지대 기록학 석사 졸업'),
    (@teacher_id, '現 대성마이맥 온라인 강사');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '한국사');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '역사');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 용기백배 퍼펙트 한국사 개념완성','','완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);


SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '한국사');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 다회차를 위한 동아시아사 개념', '', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '역사');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 다회차를 위한 세계사 개념','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '역사');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('차이를 만드는 열정! 결과를 바꾸는 사람!', '임정환', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, 'NCT가 뭐야?'),
    (@teacher_id, '열증틀너 열증스탐 임증환 서새임니다. (열정트레이너 열정사탐 임정환 선생님입니다)');

INSERT INTO teacher_profile (teacher_teacher_id, profile)
    VALUES
        (@teacher_id, '서울대학교 사회교육과 졸업'),
        (@teacher_id, '現 대성마이맥 온라인 강사'),
        (@teacher_id, '現 대치 두각학원'),
        (@teacher_id, '現 대치 예섬학원'),
        (@teacher_id, '저서) EBS연계교재 완벽분석 - 생활과윤리'),
        (@teacher_id, '저서) EBS연계교재 완벽분석 - 사회문화');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '윤리');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] IMFACT-사회문화(문제풀이 전 필수 심화)','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] LIM IT-사회문화(사회탐구 기준이 될 개념강좌)','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] IMFACT-생활과윤리(문제풀이 전 필수 심화)','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '윤리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] LIM IT-생활과윤리(사회탐구 기준이 될 개념강좌)','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '윤리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);



INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('원하는 것보다 더! 원더 사탐', '배인영', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대학교 사회교육과 졸업'),
    (@teacher_id, '現 EBSi 사회탐구 사회문화 강사'),
    (@teacher_id, '現 대성마이맥 사회탐구 강사'),
    (@teacher_id, '現 강남대성학원 사회탐구 강사'),
    (@teacher_id, '現 대치대찬학원 사회탐구 강사'),
    (@teacher_id, '現 대치이강학원 사회탐구 강사'),
    (@teacher_id, '現 대치새움학원 사회탐구 강사'),
    (@teacher_id, '現 대치비전21학원 사회탐구 강사'),
    (@teacher_id, 'TBS 상담받고 대학가자 `입시본색` 사회탐구 강사'),
    (@teacher_id, '저서) EBS연계교재 완벽분석 - 사회문화');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[패키지][고1 통합사회] 초생달 + 상현달 패키지','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('내셔널 성오그래피', '전성오', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대학교지리교육과 졸업'),
    (@teacher_id, '한국교원대학교 대학원(지형학)  졸업'),
    (@teacher_id, '충청 문화재 연구원 조사 연구원'),
    (@teacher_id, '現 대성마이맥 지리 강사'),
    (@teacher_id, '現 명인 학원(분당)'),
    (@teacher_id, '現 예섬학원(대치,서초)'),
    (@teacher_id, '現 이강학원(대치,일산)'),
    (@teacher_id, '前 이화여고 교사'),
    (@teacher_id, '前 종로학원 본원 재수종합반 강사'),
    (@teacher_id, '前 EBS지리영역 강사'),
    (@teacher_id, '前 스카이에듀 온라인 강사');




SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지리');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 오로라 한국지리','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 오로라 세계지리','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지리');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('정치와법 지금은 최여름 시대', '최여름', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '조선대학교 대학원 법학 박사 졸업'),
    (@teacher_id, '現 대성마이맥 정치와 법 강사'),
    (@teacher_id, '現 대치 두각학원'),
     (@teacher_id, '現 대치 세정학원'),
    (@teacher_id, '現 강남대성');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '경제');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '정치와법');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반사회');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] BLZA SUMMER 강좌패키지(개념편+기출편)','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '경제');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2023] BLZA 내신특강 SUMMER 내신대비(전 범위)','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '경제');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('효율 만점 민conomy!', '민준호', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대학교 경제학부 졸업'),
    (@teacher_id, '現 대성마이맥 인터넷 강사'),
    (@teacher_id, '前 IB, AP Economics 교사'),
    (@teacher_id, '前 효성그룹 세무회계팀 근무');


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '경제');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '정치와법');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 민conomy - 개념완성','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '경제');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 민conomy - 기출문제풀이','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '경제');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '사탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);



INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('물리학은 Big Bang에서 시작된다.', '방인혁', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, 'KAIST 대학원 석사 졸업'),
    (@teacher_id, 'KAIST 대학원 박사 수료'),
    (@teacher_id, '現 대성마이맥 인터넷 강사'),
    (@teacher_id, '現 강남 대성 본원 출강'),
    (@teacher_id, '現 대치 새움학원 출강'),
    (@teacher_id, '現 예섬학원 출강'),
    (@teacher_id, '現 세정학원 출강');


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '물리학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 물리학I N제:The Expert Zero','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '물리학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 물리학I 개념완성 The Fundamentals','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '물리학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);




INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('Jun or Not', '김준', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_analects (teacher_teacher_id, analects)
VALUES
    (@teacher_id, '제가 한번, 여러분들은 싫어하실지 모르겠지만, 1등급넛 50, 제가 한번 만들어 보겠습니다.'),
    (@teacher_id, '이 정도 암산이 안 되시면 내분풀이는 안쓰는게 낫습니다.(단호)');


INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대학교 화학교육과 졸업'),
    (@teacher_id, '現 대성마이맥 화학 강사'),
    (@teacher_id, '現 메가MD 일반화학 강사'),
    (@teacher_id, '前 아이파트 일반화학 강사'),
    (@teacher_id, '前 프라임PEET/MD 일반화학 강사'),
    (@teacher_id, '前 PEET/MD 단기 일반화학강사'),
    (@teacher_id, '前 MDNP 일반화학강사');


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '화학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 화학I 크리티컬 포인트(Full set 구매 교재 전용 강의)(본교재+해설지+워크북+핸드북)','', '진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '화학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[2024] 화학I Chemistory 필수이론','', '완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '화학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('Bio-Able', '정수민', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대학교 졸업'),
    (@teacher_id, 'KAIST 생물공학과 대학원  졸업'),
    (@teacher_id, '現 대성마이맥 인터넷 강사'),
    (@teacher_id, '現 강남 대성학원 출강'),
    (@teacher_id, '前 EBS, EBSi강의'),
    (@teacher_id, '前 전국 모의고사 출제의원');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '생명과학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[생I] BIo-aBLE 10시간 개념완성(고3 상위권, N수 추천)','','완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '생명과학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('[생I] BIo-aBLE 원샷원킬','','완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '생명과학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('지구과학의 반전이 시작된다. 식`s sense', '이훈식', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '공주대학교대학원 지구과학교육과 석사 졸업'),
    (@teacher_id, '現 대성마이맥 지구과학 강사'),
    (@teacher_id, '現 강남 대성학원'),
    (@teacher_id, '現 대치 두각학원, 다원교육'),
    (@teacher_id, '前 분당 명인학원'),
    (@teacher_id, '前 대치 클탑 학원'),
    (@teacher_id, '前 대치 대찬학원');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지구과학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 지구과학I 개념 Tech tree [스튜디오ver.]','','완강', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지구과학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2024 지구과학I 기출 Tech tree','', '진행중',0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '지구과학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('답이 보이는 VISUAL SCIENCE', '정성태', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '고려대학교 생명과학부 졸업'),
    (@teacher_id, '現 대성마이맥 과학 강사'),
    (@teacher_id, '現 대치 브레인벨리 학원 강사'),
    (@teacher_id, '現 사과나무 학원 강사');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);


SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반과학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2023 답이보인다 VISUAL SCIENCE 통합과학','','진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반과학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);


INSERT INTO lecture (title,introduction, status, star_point_average, total_review_count, teacher_id)
VALUES ('2023 1등급 만들기 [통합과학]','','진행중', 0, 0, @teacher_id);
SET @lecture_id = LAST_INSERT_ID();


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_lecute_grade_tag (grade_tag_id, lecture_id) VALUES (@grade_tag_id, @lecture_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_lecute_platfom_tag (platform_tag_id, lecture_id) VALUES (@platform_tag_id, @lecture_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반과학');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_lecute_subject_tag (subject_tag_id, lecture_id) VALUES (@subject_tag_id, @lecture_id);

INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('WON하는대로', '남궁원', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '경희대 한의대 졸업'),
    (@teacher_id, '동대학원 한의학 박사 졸업'),
    (@teacher_id, 'KAIST 화학,생명과학 복수전공 졸업'),
    (@teacher_id, '現 대성마이맥 강사'),
    (@teacher_id, '現 두각학원 출강'),
    (@teacher_id, '現 세정학원,명인학원 출강'),
    (@teacher_id, '前 메가MD/PEET 의치대 학사 편입 일반화학/유기화학 강사');


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '과탐전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '일반과학');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);

INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('자유롭게 사고하고 논리적으로 답하자', '박성환', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '고려대 교육대학원 교육철학 전공'),
    (@teacher_id, '강남대성학원 강사'),
    (@teacher_id, '대성학원 논술모의고사 출제위원'),
    (@teacher_id, '現 대성마이맥 강사');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);


SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '대학별고사');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '기타전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('Follow Me! <FM> 수리논술', '신재호', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서강대 국어국문학 전공'),
    (@teacher_id, '육군 중위 전역'),
    (@teacher_id, '現 대성마이맥 경찰사관 국어 강사'),
    (@teacher_id, '現 대치두각학원 경찰사관+수능국어'),
    (@teacher_id, '現 광릉한샘기숙학원 국어강사');


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '대학별고사');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '기타전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);



INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('경찰사관부터 수능까지. 국어는 국신, 처음부터끝까지.', '신한종', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '現 대성마이맥 수리논술 강사'),
    (@teacher_id, '現 강남대성학원 수리논술 강사'),
    (@teacher_id, '現 대치 세정학원 수리논술 강사'),
    (@teacher_id, '前 스카이에듀 수능, 경찰사관 국어 인강'),
    (@teacher_id, '前 강남하이퍼 본원'),
    (@teacher_id, '前 대치이강학원 수능+경찰사관 국어');


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);


SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '대학별고사');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '기타전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);



INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('대입 수학의 연결고리,매듭짓다.', '유제승', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '서울대 재료공학부 졸업'),
    (@teacher_id, '부산대성학원 수리논술 강사'),
    (@teacher_id, '대성마이맥 렉투스팀 수리구술면접, 사관학교 수학담당'),
    (@teacher_id, '경찰대사관학교팀');


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);

SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '대학별고사');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '기타전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);



INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('실기없이 미대가기 도전!! 실.미.도 Project', '정지윤', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '홍익대 예술학과 석사 졸업'),
    (@teacher_id, '이화여대 교육학과 석사 졸업'),
    (@teacher_id, 'KAIST 화학,생명과학 복수전공 졸업'),
    (@teacher_id, '現 대성마이맥 미술대학 비실기전형 대표강사'),
    (@teacher_id, '現 강남대치본원 아이엠 입학사정관제(비실기) 전임'),
    (@teacher_id, '現 전국 프랜차이즈 입시미술학원 특강 강사'),
    (@teacher_id, '홍대 본원 그린섬 디자인 Top Class 프로그램/교재개발 전임'),
    (@teacher_id, '홍대 본원 그린섬 입학사정관제(비실기) 전임'),
    (@teacher_id, '前 이화여대 연구실 수석연구원');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);


SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '대학별고사');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '기타전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);



INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('리하이(대단한) 중국어', '리하이', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '한국외대 중어중문학과 석사 졸업'),
    (@teacher_id, '現 대성마이맥 중국어 인터넷 강사'),
(@teacher_id, '現 강남YBM어학원 중국어 TSC 대표강사'),
    (@teacher_id, '現 성결대학교 산학자문의원'),
(@teacher_id, '前 YTN기자'),
    (@teacher_id, '前 삼성그룹 임직원 TSC 기초/실전강의'),
(@teacher_id, '前 삼성그룹 주재원 및 지역전문가 TSC 강의'),
    (@teacher_id, '前 외교부, 여성가족부 등 동시통역'),
(@teacher_id, '前 다수의 대학 및 기업체 TSC 및 HSK 특강');


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);


SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '제2외국어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '기타전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('HOLA, 올라 스페인어', '신승', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
    VALUES
        (@teacher_id, '저서) 수능 스페인어 50가지 법칙'),
    (@teacher_id, '저서) 한번에끝! OPIc 스페인어'),
    (@teacher_id, '저서) 필요할 때 통하는 여행 스페인어'),
    (@teacher_id, '現 대성마이맥 스페인어 인터넷 강사'),
    (@teacher_id, '現 인천 소재 일반고 강의'),
    (@teacher_id, '前 미추홀외고, 인천국제고, 대전외고 강의'),
    (@teacher_id, '前 방송중 <생활스페인어> 온라인강의'),
    (@teacher_id, '前 YBM어학원');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);


SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '제2외국어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '기타전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('야! 이 생생한! 야! 생 베트남어', '유기태', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '한국외대 베트남어과 졸업'),
    (@teacher_id, '호치민 인문사회과학대 베트남어학 수료(C2)'),
    (@teacher_id, '現 대성마이맥 베트남어 인터넷 강사'),
    (@teacher_id, '現 파고다어학원 베트남어 강사'),
    (@teacher_id, '現 성신여대 교내 베트남어 회화과정 강사'),
    (@teacher_id, '前 고려아연, 효성 베트남 주재원과정 강의'),
    (@teacher_id, '前 서울관광재단 관광통역사 과정 전임강사 및 면접위원'),
    (@teacher_id, '前 삼성전자,삼성디스플레이,CJ프레시웨이,CJ올리브네트웍스 비즈니스회화'),
    (@teacher_id, '前 강남 시사 랭기지플러스 어학원 강사');

SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);


SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '제2외국어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '기타전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);


INSERT INTO teacher ( introduction, name, star_point_average, total_review_count)
VALUES
    ('수능 1등급을 만드는 키워드 아랍어', '지은경', 0, 0);
SET @teacher_id = LAST_INSERT_ID();

INSERT INTO teacher_profile (teacher_teacher_id, profile)
VALUES
    (@teacher_id, '한국외대 아랍어과 졸업'),
    (@teacher_id, '한국외대 일반대학원 중동어문학과 아랍문학전공'),
    (@teacher_id, '쿠웨이트 정부초청 국비 장학생'),
    (@teacher_id, '現 대성마이맥 아랍어 인터넷 강사'),
    (@teacher_id, '現 하늘고 아랍어 강사'),
    (@teacher_id, '現 강남대성학원 강사'),
    (@teacher_id, '現 새움학원, 명인학원, 예섬학원 강사'),
    (@teacher_id, '現 비전21학원, 현덕학원, 우림학원 강사'),
    (@teacher_id, '前 일산 저동고등학교 강사'),
    (@teacher_id, '前 메가스터디, 청솔학원, 목동종로학원 강사'),
    (@teacher_id, '前 압구정 에이원 아카데미 강사'),
    (@teacher_id, '前 반포 해냄학원, 목동 행복한3월학원 강사');


SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고1');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고2');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = '고3');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);
SET @grade_tag_id = (SELECT grade_tag_id FROM grade_tag WHERE grade = 'N수');
INSERT INTO map_teacher_grade_tag (grade_tag_id, teacher_id) VALUES (@grade_tag_id, @teacher_id);


SET @platform_tag_id = (SELECT platform_tag_id FROM platform_tag WHERE platform = '대성마이맥');
INSERT INTO map_teacher_platform_tag (platform_tag_id, teacher_id) VALUES (@platform_tag_id, @teacher_id);

SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '제2외국어');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);
SET @subject_tag_id = (SELECT subject_tag_id FROM subject_tag WHERE subject = '기타전체');
INSERT INTO map_teacher_subject_tag (subject_tag_id, teacher_id) VALUES (@subject_tag_id, @teacher_id);



