package ynzmz.server.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.review.lecture.dto.LectureReviewDto;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.tag.dto.GradeTagDto;
import ynzmz.server.tag.dto.PlatformTagDto;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;
import ynzmz.server.vote.review.lecture.dto.LectureReviewVoteDto;
import ynzmz.server.vote.review.lecture.entity.LectureReviewVote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ynzmz.server.tag.entity.GradeTag.Grade.*;
import static ynzmz.server.tag.entity.PlatformTag.Platform.*;
import static ynzmz.server.tag.entity.SubjectTag.Subject.*;

public class StubData {
    public static final List<String> tagsSample = new ArrayList<>();
    public static final List<String> profileSample = new ArrayList<>();
    public static final List<String> analectsSample = new ArrayList<>();
    public static final List<String> gradeTagStringSample = new ArrayList<>();
    public static final List<String> subjectTagStringSample = new ArrayList<>();
    public static final List<String> platformTagStringSample = new ArrayList<>();
    @JsonProperty("starPointCount")
    public static final Map<String,Long> starPointCount = new HashMap<>();
    public static final List<GradeTagDto.Response> gradeTagResponses = new ArrayList<>();
    public static final List<SubjectTagDto.Response> subjectTagResponses = new ArrayList<>();
    public static final List<PlatformTagDto.Response> platformTagResponses = new ArrayList<>();
    public static final Member member = new Member();
    public static final Teacher teacher = new Teacher();
    public static final Lecture lecture = new Lecture();
    public static final LectureReview lectureReview = new LectureReview();

    public static final TeacherDto.SimpleInfoResponse teacherSimpleInfoResponse = new TeacherDto.SimpleInfoResponse();
    public static final LectureDto.SimpleInfoResponse lectureSimpleInfoResponse = new LectureDto.SimpleInfoResponse();
    public static final LectureReviewDto.ListPageResponse lectureReviewListPageResponse = new LectureReviewDto.ListPageResponse();
    public static final List<LectureReviewDto.ListPageResponse> lectureReviewListPageResponses = new ArrayList<>();
    public static final LectureDto.ListPageResponse lectureListPageResponse = new LectureDto.ListPageResponse();
    public static final LectureDto.DetailPageResponse lectureDetailPageResponse = new LectureDto.DetailPageResponse();
    public static final LectureDto.TeacherReviewDetailPageResponse lectureTeacherReviewDetailPageResponse = new LectureDto.TeacherReviewDetailPageResponse();
    public static final List<LectureDto.TeacherReviewDetailPageResponse> lectureTeacherReviewDetailPageResponses = new ArrayList<>();
    public static final List<LectureDto.ListPageResponse> lectureListPageResponses = new ArrayList<>();
    public static final TeacherDto.ListPageResponse teacherListPageResponse = new TeacherDto.ListPageResponse();
    public static final TeacherDto.DetailPageResponse teacherDetailPageResponse = new TeacherDto.DetailPageResponse();
    public static final TeacherDto.ReviewDetailPageResponse teacherReviewDetailPageResponse = new TeacherDto.ReviewDetailPageResponse();
    public static final List<TeacherDto.ListPageResponse> teacherListPageResponses = new ArrayList<>();
    public static final LectureReviewDto.InfoResponse lectureReviewPostInfoResponse = new LectureReviewDto.InfoResponse();
    public static final LectureReviewDto.DetailPageResponse lectureReviewDetailPageResponse = new LectureReviewDto.DetailPageResponse();
    public static final LectureReviewCommentDto.Response lectureReviewPostCommentResponse = new LectureReviewCommentDto.Response();
    public static final List<LectureReviewCommentDto.Response> lectureReviewPostCommentResponses = new ArrayList<>();
    public static final LectureReviewVote lectureReviewVote = new LectureReviewVote();
    public static final LectureReviewVoteDto.Response lectureReviewVoteResponse = new LectureReviewVoteDto.Response();
    public static final LectureReviewVoteDto.Response lectureReviewCommentVoteResponse = new LectureReviewVoteDto.Response();
    public static void init(){

        tagsSample.add("국어");
        tagsSample.add("고3");
        tagsSample.add("메가스터디");

        profileSample.add("서울대학교 국어국문학과 학사");
        profileSample.add("전 메가스터디 국어 강사");
        profileSample.add("현 이투스 국어 강사");
        profileSample.add("간단 경력 리스트 입력");

        analectsSample.add("잠은 죽어서 자야한다");
        analectsSample.add("자가진단 빨리하자");
        analectsSample.add("14급 15급 공무원 하면 딱 맞을새끼들 빠가새끼들 빨리때리치워");
        analectsSample.add("어록 리스트 입력");

        gradeTagStringSample.add("고1");
        gradeTagStringSample.add("고2");
        gradeTagStringSample.add("고3");
        gradeTagStringSample.add("예비고1");
        gradeTagStringSample.add("예비고2");
        gradeTagStringSample.add("예비고3");

        subjectTagStringSample.add("국어");
        subjectTagStringSample.add("한국사");

        platformTagStringSample.add("이투스");
        platformTagStringSample.add("EBS");

        starPointCount.put("1점갯수",1L);
        starPointCount.put("2점갯수",1L);
        starPointCount.put("3점갯수",0L);
        starPointCount.put("4점갯수",3L);
        starPointCount.put("5점갯수",12L);


        gradeTagResponses.add(new GradeTagDto.Response(고1));
        gradeTagResponses.add(new GradeTagDto.Response(고2));
        gradeTagResponses.add(new GradeTagDto.Response(고3));
        gradeTagResponses.add(new GradeTagDto.Response(예비고1));
        gradeTagResponses.add(new GradeTagDto.Response(예비고2));
        gradeTagResponses.add(new GradeTagDto.Response(예비고3));

        subjectTagResponses.add(new SubjectTagDto.Response(국어));
        subjectTagResponses.add(new SubjectTagDto.Response(한국사));

        platformTagResponses.add(new PlatformTagDto.Response(이투스));
        platformTagResponses.add(new PlatformTagDto.Response(EBS));



        member.setMemberId(1L);
        member.setDisplayName("홍길동");
        member.setEmail("ghdrlfehd@gmail.com");
        member.setPassword("1111");
        member.setCreatedAt("2023.03.10.18:52:36");
        member.setIconImageUrl("IconUrl");

        teacher.setTeacherId(1L);
        teacher.setName("홍길동");
        teacher.setIntroduction("국어 최고의 강사!");

        lecture.setTitle("수능 국어 완전 정복!");
        lecture.setLectureId(1L);
        lecture.setIntroduction("3개월 만에 완전정복해보세요!");
        lecture.setTeacher(teacher);
        lecture.setStarPointAverage(4.2);

        lectureReview.setLectureReviewId(1L);
        lectureReview.setLecture(lecture);

        teacherSimpleInfoResponse.setTeacherId(1L);
        teacherSimpleInfoResponse.setName("홍길동");
        teacherSimpleInfoResponse.setStarPointAverage(0.0);

        lectureSimpleInfoResponse.setLectureId(1L);
        lectureSimpleInfoResponse.setTitle("강의 이름!");
        lectureSimpleInfoResponse.setStarPointAverage(0);

        lectureReviewListPageResponse.setLectureReviewId(1L);
        lectureReviewListPageResponse.setTitle("이 강의 추천합니다!");
        lectureReviewListPageResponse.setStarPoint(5);
        lectureReviewListPageResponse.setContent("강의하시는데 ~~궁 ~~궁 해서 추천합니다");
        lectureReviewListPageResponse.setCreatedAt("2023.03.10.18:52:36");
        lectureReviewListPageResponse.setModifiedAt("2023.03.10.18:52:36");
        lectureReviewListPageResponse.setViewCount(1);
        lectureReviewListPageResponse.setVoteCount(0);
        lectureReviewListPageResponse.setTeacher(teacherSimpleInfoResponse);
        lectureReviewListPageResponse.setLecture(lectureSimpleInfoResponse);
        lectureReviewListPageResponse.setMember(member);

        lectureReviewListPageResponses.add(lectureReviewListPageResponse);
        lectureReviewListPageResponses.add(lectureReviewListPageResponse);


        lectureListPageResponse.setLectureId(1L);
        lectureListPageResponse.setTitle("강의 타이틀명!");
        lectureListPageResponse.setIntroduction("강의 간단 소개");
        lectureListPageResponse.setStatus(Lecture.Status.진행중);
        lectureListPageResponse.setStarPointAverage(4.7);
        lectureListPageResponse.setTotalReviewCount(9);
        lectureListPageResponse.setGradeTags(gradeTagResponses);
        lectureListPageResponse.setSubjectTags(subjectTagResponses);
        lectureListPageResponse.setPlatformTags(platformTagResponses);
        lectureListPageResponse.setTeacher(teacherSimpleInfoResponse);

        lectureDetailPageResponse.setLectureId(1L);
        lectureDetailPageResponse.setTitle("강의 타이틀명!");
        lectureDetailPageResponse.setIntroduction("강의 간단 소개");
        lectureDetailPageResponse.setStatus(Lecture.Status.진행중);
        lectureDetailPageResponse.setTeacher(teacherSimpleInfoResponse);
        lectureDetailPageResponse.setStarPointAverage(4.7);
        lectureDetailPageResponse.setTotalReviewCount(9);
        lectureDetailPageResponse.setGradeTags(gradeTagResponses);
        lectureDetailPageResponse.setSubjectTags(subjectTagResponses);
        lectureDetailPageResponse.setPlatformTags(platformTagResponses);
        lectureDetailPageResponse.setLectureReviews(lectureReviewListPageResponses);

        lectureTeacherReviewDetailPageResponse.setLectureId(1L);
        lectureTeacherReviewDetailPageResponse.setTitle("강의 타이틀명!");
        lectureTeacherReviewDetailPageResponse.setStatus(Lecture.Status.진행중);
        lectureTeacherReviewDetailPageResponse.setLectureReviews(lectureReviewListPageResponses);

        lectureTeacherReviewDetailPageResponses.add(lectureTeacherReviewDetailPageResponse);
        lectureTeacherReviewDetailPageResponses.add(lectureTeacherReviewDetailPageResponse);

        lectureListPageResponses.add(lectureListPageResponse);
        lectureListPageResponses.add(lectureListPageResponse);

        teacherListPageResponse.setTeacherId(1L);
        teacherListPageResponse.setName("홍길동");
        teacherListPageResponse.setIntroduction("홍길동 강사 간단 소개");
        teacherListPageResponse.setImageUrl("이미지 url");
        teacherListPageResponse.setStarPointAverage(4.2);
        teacherListPageResponse.setTotalReviewCount(17);
        teacherListPageResponse.setGradeTags(gradeTagResponses);
        teacherListPageResponse.setSubjectTags(subjectTagResponses);
        teacherListPageResponse.setPlatformTags(platformTagResponses);

        teacherDetailPageResponse.setTeacherId(1L);
        teacherDetailPageResponse.setName("홍길동");
        teacherDetailPageResponse.setIntroduction("홍길동 강사 간단 소개");
        teacherDetailPageResponse.setImageUrl("이미지 url");
        teacherDetailPageResponse.setProfile(profileSample);
        teacherDetailPageResponse.setAnalects(analectsSample);
        teacherDetailPageResponse.setStarPointAverage(4.2);
        teacherDetailPageResponse.setTotalReviewCount(17);
        teacherDetailPageResponse.setGradeTags(gradeTagResponses);
        teacherDetailPageResponse.setSubjectTags(subjectTagResponses);
        teacherDetailPageResponse.setPlatformTags(platformTagResponses);
        teacherDetailPageResponse.setLectures(lectureListPageResponses);

        teacherReviewDetailPageResponse.setTeacherId(1L);
        teacherReviewDetailPageResponse.setName("홍길동");
        teacherReviewDetailPageResponse.setIntroduction("홍길동 강사 간단 소개");
        teacherReviewDetailPageResponse.setImageUrl("이미지 url");
        teacherReviewDetailPageResponse.setProfile(profileSample);
        teacherReviewDetailPageResponse.setAnalects(analectsSample);
        teacherReviewDetailPageResponse.setStarPointAverage(4.2);
        teacherReviewDetailPageResponse.setTotalReviewCount(17);
        teacherReviewDetailPageResponse.setGradeTags(gradeTagResponses);
        teacherReviewDetailPageResponse.setSubjectTags(subjectTagResponses);
        teacherReviewDetailPageResponse.setPlatformTags(platformTagResponses);
        teacherReviewDetailPageResponse.setLectures(lectureTeacherReviewDetailPageResponses);

        teacherListPageResponses.add(teacherListPageResponse);
        teacherListPageResponses.add(teacherListPageResponse);


//        TEACHER_SIMPLE_INFO_RESPONS.add(TEACHER_SIMPLE_INFO_RESPONSE);
//        TEACHER_SIMPLE_INFO_RESPONS.add(TEACHER_SIMPLE_INFO_RESPONSE);
//
//
//        LECTURE_SIMPLE_INFO_RESPONS.add(LECTURE_SIMPLE_INFO_RESPONSE);
//        LECTURE_SIMPLE_INFO_RESPONS.add(LECTURE_SIMPLE_INFO_RESPONSE);
//
//
        lectureReviewPostInfoResponse.setLectureReviewId(1L);
        lectureReviewPostInfoResponse.setTitle("리뷰글 제목");
        lectureReviewPostInfoResponse.setStarPoint(5);
        lectureReviewPostInfoResponse.setContent("리뷰글 내용");
        lectureReviewPostInfoResponse.setCreatedAt("2023.03.10.18:52:36");
        lectureReviewPostInfoResponse.setModifiedAt("2023.03.10.18:52:36");
        lectureReviewPostInfoResponse.setViewCount(1);
        lectureReviewPostInfoResponse.setVoteCount(0);
        lectureReviewPostInfoResponse.setLecture(lectureSimpleInfoResponse);
        lectureReviewPostInfoResponse.setMember(member);

        lectureReviewPostCommentResponse.setLectureReviewCommentId(1L);
        lectureReviewPostCommentResponse.setContent("이사람 재대로 들은거 맞음?");
        lectureReviewPostCommentResponse.setCreatedAt("2023.03.10.18:52:36");
        lectureReviewPostCommentResponse.setModifiedAt("2023.03.10.18:52:36");
        lectureReviewPostCommentResponse.setVoteCount(0);
        lectureReviewPostCommentResponse.setMember(member);

        lectureReviewPostCommentResponses.add(lectureReviewPostCommentResponse);
        lectureReviewPostCommentResponses.add(lectureReviewPostCommentResponse);

        lectureReviewDetailPageResponse.setLectureReviewId(1L);
        lectureReviewDetailPageResponse.setTitle("리뷰글 제목");
        lectureReviewDetailPageResponse.setStarPoint(5);
        lectureReviewDetailPageResponse.setContent("리뷰글 내용");
        lectureReviewDetailPageResponse.setCreatedAt("2023.03.10.18:52:36");
        lectureReviewDetailPageResponse.setModifiedAt("2023.03.10.18:52:36");
        lectureReviewDetailPageResponse.setViewCount(1);
        lectureReviewDetailPageResponse.setVoteCount(0);
        lectureReviewDetailPageResponse.setLecture(lectureSimpleInfoResponse);
        lectureReviewDetailPageResponse.setTeacher(teacherSimpleInfoResponse);
        lectureReviewDetailPageResponse.setMember(member);
        lectureReviewDetailPageResponse.setComments(lectureReviewPostCommentResponses);
//
//        lectureReviewPostInfoResponses.add(lectureReviewPostInfoResponse);
//        lectureReviewPostInfoResponses.add(lectureReviewPostInfoResponse);
//
//
//
        lectureReviewVote.setLectureReviewVoteId(1L);
        lectureReviewVote.setMember(member);
        lectureReviewVote.setVoteStatus(LectureReviewVote.VoteStatus.UP);
        lectureReviewVote.setLectureReview(lectureReview);

        lectureReviewVoteResponse.setVoteStatus(LectureReviewVote.VoteStatus.UP);
        lectureReviewVoteResponse.setTarget(LectureReviewVote.Target.REVIEW);
        lectureReviewVoteResponse.setLectureReviewId(1L);
        lectureReviewVoteResponse.setLectureReviewTotalCount(5);
        lectureReviewVoteResponse.setMemberId(1L);

        lectureReviewCommentVoteResponse.setVoteStatus(LectureReviewVote.VoteStatus.UP);
        lectureReviewCommentVoteResponse.setTarget(LectureReviewVote.Target.COMMENT);
        lectureReviewCommentVoteResponse.setLectureReviewCommentId(1L);
        lectureReviewCommentVoteResponse.setLectureReviewCommentTotalCount(7);
        lectureReviewCommentVoteResponse.setMemberId(1L);
//
//        LECTURE_REVIEW_COMMENT.setLectureReviewCommentId(1L);
//        LECTURE_REVIEW_COMMENT.setMember(member);
//        LECTURE_REVIEW_COMMENT.setContent("이사람 재대로 들은거 맞음?");
//        LECTURE_REVIEW_COMMENT.setVoteCount(0);
//        LECTURE_REVIEW_COMMENT.setCreatedAt("2023.03.10.18:52:36");
//        LECTURE_REVIEW_COMMENT.setModifiedAt("2023.03.10.18:52:36");
//        LECTURE_REVIEW_COMMENT.setLectureReview(LECTURE_REVIEW);
//        LECTURE_REVIEW_COMMENT.setMember(member);
//
//
//        LECTURE_REVIEW_COMMENT_VOTE.setLectureReviewCommentVoteId(1L);
//        LECTURE_REVIEW_COMMENT_VOTE.setVoteStatus(LectureReviewCommentVote.VoteStatus.UP);
//        LECTURE_REVIEW_COMMENT_VOTE.setMember(member);
//        LECTURE_REVIEW_COMMENT_VOTE.setLectureReviewComment(LECTURE_REVIEW_COMMENT);
//
//        lectureReviewPostCommentVoteResponse.setVoteStatus(LectureReviewCommentVote.VoteStatus.UP);
//        lectureReviewPostCommentVoteResponse.setLectureReviewCommentId(1L);
//        lectureReviewPostCommentVoteResponse.setMemberId(1L);
//        lectureReviewPostCommentVoteResponse.setLectureReviewCommentTotalCount(5);
    }
}
