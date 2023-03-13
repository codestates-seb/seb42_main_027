package ynzmz.server.helper;

import ynzmz.server.comment.lecturereview.dto.LectureReviewCommentDto;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecturereview.dto.LectureReviewDto;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;
import ynzmz.server.vote.lecturereview.comment.dto.LectureReviewCommentVoteDto;
import ynzmz.server.vote.lecturereview.comment.entity.LectureReviewCommentVote;
import ynzmz.server.vote.lecturereview.dto.LectureReviewVoteDto;
import ynzmz.server.vote.lecturereview.entity.LectureReviewVote;

import java.util.ArrayList;
import java.util.List;

public class StubData {
    public static final List<String> tagsSample = new ArrayList<>();
    public static final List<Tag.Type> tagTypesSample = new ArrayList<>();
    public static final List<TeacherTag> teacherTagsSample = new ArrayList<>();
    public static final List<LectureTag> LECTURE_LECTURE_TAGS_SAMPLE = new ArrayList<>();
    public static final Member member = new Member();
    public static final Teacher teacher = new Teacher();
    public static final Lecture lecture = new Lecture();
    public static final LectureReview LECTURE_REVIEW = new LectureReview();
    public static final TeacherDto.SimpleInfoResponse TEACHER_SIMPLE_INFO_RESPONSE = new TeacherDto.SimpleInfoResponse();
    public static final List<TeacherDto.SimpleInfoResponse> TEACHER_SIMPLE_INFO_RESPONS = new ArrayList<>();
    public static final LectureDto.SimpleInfoResponse LECTURE_SIMPLE_INFO_RESPONSE = new LectureDto.SimpleInfoResponse();
    public static final List<LectureDto.SimpleInfoResponse> LECTURE_SIMPLE_INFO_RESPONS = new ArrayList<>();
    public static final LectureReviewDto.InfoResponse lectureReviewPostInfoResponse = new LectureReviewDto.InfoResponse();
    public static final List<LectureReviewDto.InfoResponse> lectureReviewPostInfoResponses = new ArrayList<>();
    public static final LectureReviewCommentDto.Response lectureReviewPostCommentResponse = new LectureReviewCommentDto.Response();
    public static final List<LectureReviewCommentDto.Response> lectureReviewPostCommentResponses = new ArrayList<>();
    public static final LectureReviewVote LECTURE_REVIEW_VOTE = new LectureReviewVote();
    public static final LectureReviewVoteDto.Response lectureReviewPostVoteResponse = new LectureReviewVoteDto.Response();
    public static final LectureReviewComment LECTURE_REVIEW_COMMENT = new LectureReviewComment();
    public static final LectureReviewCommentVote LECTURE_REVIEW_COMMENT_VOTE = new LectureReviewCommentVote();
    public static final LectureReviewCommentVoteDto.Response lectureReviewPostCommentVoteResponse = new LectureReviewCommentVoteDto.Response();
    public static void init(){

        tagsSample.add("국어");
        tagsSample.add("고3");
        tagsSample.add("메가스터디");

        tagTypesSample.add(Tag.Type.국어);
        tagTypesSample.add(Tag.Type.고3);
        tagTypesSample.add(Tag.Type.메가스터디);

        teacherTagsSample.add(new TeacherTag(1L,teacher,new Tag(Tag.Type.국어)));
        teacherTagsSample.add(new TeacherTag(1L,teacher,new Tag(Tag.Type.고3)));
        teacherTagsSample.add(new TeacherTag(1L,teacher,new Tag(Tag.Type.메가스터디)));

        LECTURE_LECTURE_TAGS_SAMPLE.add(new LectureTag(1L,lecture,new Tag(Tag.Type.국어)));
        LECTURE_LECTURE_TAGS_SAMPLE.add(new LectureTag(1L,lecture,new Tag(Tag.Type.고3)));
        LECTURE_LECTURE_TAGS_SAMPLE.add(new LectureTag(1L,lecture,new Tag(Tag.Type.메가스터디)));

        member.setMemberId(1L);
        member.setDisplayName("홍길동");
        member.setEmail("ghdrlfehd@gmail.com");
        member.setPassword("1111");
        member.setCreatedAt("2023.03.10.18:52:36");
        member.setIconImageUrl("IconUrl");

        teacher.setTeacherId(1L);
        teacher.setName("홍길동");
        teacher.setIntroduction("국어 최고의 강사!");
        teacher.setTeacherTags(teacherTagsSample);

        lecture.setTitle("수능 국어 완전 정복!");
        lecture.setLectureId(1L);
        lecture.setIntroduction("3개월 만에 완전정복해보세요!");
        lecture.setTeacher(teacher);
        lecture.setLectureTags(LECTURE_LECTURE_TAGS_SAMPLE);

        LECTURE_REVIEW.setLectureReviewId(1L);
        LECTURE_REVIEW.setTitle("이 강의 추천합니다!");
        LECTURE_REVIEW.setStarPoint(4.5);
        LECTURE_REVIEW.setContent("강의하시는데 ~~궁 ~~궁 해서 추천합니다");
        LECTURE_REVIEW.setCreatedAt("2023.03.10.18:52:36");
        LECTURE_REVIEW.setModifiedAt("2023.03.10.18:52:36");
        LECTURE_REVIEW.setViewCount(1);
        LECTURE_REVIEW.setVoteCount(0);
        LECTURE_REVIEW.setLecture(lecture);
        LECTURE_REVIEW.setMember(member);

        TEACHER_SIMPLE_INFO_RESPONSE.setTeacherId(1L);
        TEACHER_SIMPLE_INFO_RESPONSE.setName("홍길동");
        TEACHER_SIMPLE_INFO_RESPONSE.setIntroduction("국어 최고의 강사!");
        TEACHER_SIMPLE_INFO_RESPONSE.setTags(tagTypesSample);

        TEACHER_SIMPLE_INFO_RESPONS.add(TEACHER_SIMPLE_INFO_RESPONSE);
        TEACHER_SIMPLE_INFO_RESPONS.add(TEACHER_SIMPLE_INFO_RESPONSE);

        LECTURE_SIMPLE_INFO_RESPONSE.setLectureId(1L);
        LECTURE_SIMPLE_INFO_RESPONSE.setName("수능 국어 완전정복");
        LECTURE_SIMPLE_INFO_RESPONSE.setIntroduction("3개월 만에 완전정복해보세요!");
        LECTURE_SIMPLE_INFO_RESPONSE.setStarPointAverage(4.2);
        LECTURE_SIMPLE_INFO_RESPONSE.setTags(tagTypesSample);
        LECTURE_SIMPLE_INFO_RESPONSE.setTeacher(TEACHER_SIMPLE_INFO_RESPONSE);

        LECTURE_SIMPLE_INFO_RESPONS.add(LECTURE_SIMPLE_INFO_RESPONSE);
        LECTURE_SIMPLE_INFO_RESPONS.add(LECTURE_SIMPLE_INFO_RESPONSE);


        lectureReviewPostInfoResponse.setLectureReviewId(1L);
        lectureReviewPostInfoResponse.setTitle("이 강의 추천합니다!");
        lectureReviewPostInfoResponse.setStarPoint(4.5);
        lectureReviewPostInfoResponse.setContent("강의하시는데 ~~궁 ~~궁 해서 추천합니다");
        lectureReviewPostInfoResponse.setCreatedAt("2023.03.10.18:52:36");
        lectureReviewPostInfoResponse.setModifiedAt("2023.03.10.18:52:36");
        lectureReviewPostInfoResponse.setViewCount(1);
        lectureReviewPostInfoResponse.setVoteCount(0);
        lectureReviewPostInfoResponse.setLecture(LECTURE_SIMPLE_INFO_RESPONSE);
        lectureReviewPostInfoResponse.setMember(member);

        lectureReviewPostInfoResponses.add(lectureReviewPostInfoResponse);
        lectureReviewPostInfoResponses.add(lectureReviewPostInfoResponse);

        lectureReviewPostCommentResponse.setLectureReviewCommentId(1L);
        lectureReviewPostCommentResponse.setContent("이사람 재대로 들은거 맞음?");
        lectureReviewPostCommentResponse.setVoteCount(0);
        lectureReviewPostCommentResponse.setCreatedAt("2023.03.10.18:52:36");
        lectureReviewPostCommentResponse.setModifiedAt("2023.03.10.18:52:36");
        lectureReviewPostCommentResponse.setMember(member);

        lectureReviewPostCommentResponses.add(lectureReviewPostCommentResponse);
        lectureReviewPostCommentResponses.add(lectureReviewPostCommentResponse);

        LECTURE_REVIEW_VOTE.setLectureReviewVoteId(1L);
        LECTURE_REVIEW_VOTE.setMember(member);
        LECTURE_REVIEW_VOTE.setVoteStatus(LectureReviewVote.VoteStatus.UP);
        LECTURE_REVIEW_VOTE.setLectureReview(LECTURE_REVIEW);

        lectureReviewPostVoteResponse.setVoteStatus(LectureReviewVote.VoteStatus.UP);
        lectureReviewPostVoteResponse.setLectureReviewTotalCount(5);
        lectureReviewPostVoteResponse.setMemberId(1L);
        lectureReviewPostVoteResponse.setLectureReviewId(1L);

        LECTURE_REVIEW_COMMENT.setLectureReviewCommentId(1L);
        LECTURE_REVIEW_COMMENT.setMember(member);
        LECTURE_REVIEW_COMMENT.setContent("이사람 재대로 들은거 맞음?");
        LECTURE_REVIEW_COMMENT.setVoteCount(0);
        LECTURE_REVIEW_COMMENT.setCreatedAt("2023.03.10.18:52:36");
        LECTURE_REVIEW_COMMENT.setModifiedAt("2023.03.10.18:52:36");
        LECTURE_REVIEW_COMMENT.setLectureReview(LECTURE_REVIEW);
        LECTURE_REVIEW_COMMENT.setMember(member);


        LECTURE_REVIEW_COMMENT_VOTE.setLectureReviewCommentVoteId(1L);
        LECTURE_REVIEW_COMMENT_VOTE.setVoteStatus(LectureReviewCommentVote.VoteStatus.UP);
        LECTURE_REVIEW_COMMENT_VOTE.setMember(member);
        LECTURE_REVIEW_COMMENT_VOTE.setLectureReviewComment(LECTURE_REVIEW_COMMENT);

        lectureReviewPostCommentVoteResponse.setVoteStatus(LectureReviewCommentVote.VoteStatus.UP);
        lectureReviewPostCommentVoteResponse.setLectureReviewCommentId(1L);
        lectureReviewPostCommentVoteResponse.setMemberId(1L);
        lectureReviewPostCommentVoteResponse.setLectureReviewCommentTotalCount(5);
    }
}
