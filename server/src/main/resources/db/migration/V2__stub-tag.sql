INSERT INTO platform_tag (platform)
SELECT * FROM (
  SELECT '메가스터디' AS platform
  UNION SELECT '대성마이맥'
  UNION SELECT '이투스'
  UNION SELECT 'EBS'
  UNION SELECT '에듀윌'
  UNION SELECT '기타'
) p
WHERE NOT EXISTS (SELECT 1 FROM platform_tag WHERE platform_tag.platform = p.platform);

INSERT INTO subject_tag (subject)
SELECT * FROM (
                  SELECT '국어' AS subject
                  UNION SELECT '영어'
                  UNION SELECT '수학'
                  UNION SELECT '한국사'
                  UNION SELECT '한문'

                  UNION SELECT '사탐전체'
                  UNION SELECT '일반사회'
                  UNION SELECT '윤리'
                  UNION SELECT '지리'
                  UNION SELECT '역사'
                  UNION SELECT '경제'
                  UNION SELECT '정치와법'

                  UNION SELECT '과탐전체'
                  UNION SELECT '물리학'
                  UNION SELECT '화학'
                  UNION SELECT '생명과학'
                  UNION SELECT '지구과학'
                  UNION SELECT '일반과학'

                  UNION SELECT '기타전체'
                  UNION SELECT '제2외국어'
                  UNION SELECT '대학별고사'
                  UNION SELECT '그외'
              ) s
WHERE NOT EXISTS (SELECT 1 FROM subject_tag WHERE subject_tag.subject = s.subject);

INSERT INTO grade_tag (grade)
SELECT * FROM (
                  SELECT '중1' AS grade
                  UNION SELECT '중2'
                  UNION SELECT '중3'
                  UNION SELECT '고1'
                  UNION SELECT '고2'
                  UNION SELECT '고3'
                  UNION SELECT 'N수'
              ) g
WHERE NOT EXISTS (SELECT 1 FROM grade_tag WHERE grade_tag.grade = g.grade);


