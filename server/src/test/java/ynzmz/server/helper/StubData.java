package ynzmz.server.helper;

import ynzmz.server.comment.lecturereviewpost.dto.LectureReviewPostCommentDto;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecturereviewpost.dto.LectureReviewPostDto;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.member.entity.Member;
import ynzmz.server.tag.entity.LectureTag;
import ynzmz.server.tag.entity.Tag;
import ynzmz.server.tag.entity.TeacherTag;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class StubData {
    public static final List<String> tagsSample = new ArrayList<>();
    public static final List<Tag.Type> tagTypesSample = new ArrayList<>();
    public static final List<TeacherTag> teacherTagsSample = new ArrayList<>();
    public static final List<LectureTag> lectureTagsSample = new ArrayList<>();
    public static final Member member = new Member();
    public static final Teacher teacher = new Teacher();
    public static final Lecture lecture = new Lecture();
    public static final LectureReviewPost lectureReviewPost = new LectureReviewPost();
    public static final TeacherDto.InfoResponse teacherInfoResponse = new TeacherDto.InfoResponse();
    public static final List<TeacherDto.InfoResponse> teacherInfoResponses = new ArrayList<>();
    public static final LectureDto.InfoResponse lectureInfoResponse = new LectureDto.InfoResponse();
    public static final List<LectureDto.InfoResponse> lectureInfoResponses = new ArrayList<>();
    public static final LectureReviewPostDto.InfoResponse lectureReviewPostInfoResponse = new LectureReviewPostDto.InfoResponse();
    public static final List<LectureReviewPostDto.InfoResponse> lectureReviewPostInfoResponses = new ArrayList<>();
    public static final LectureReviewPostCommentDto.Response lectureReviewPostCommentResponse = new LectureReviewPostCommentDto.Response();
    public static final List<LectureReviewPostCommentDto.Response> lectureReviewPostCommentResponses = new ArrayList<>();
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

        lectureTagsSample.add(new LectureTag(1L,lecture,new Tag(Tag.Type.국어)));
        lectureTagsSample.add(new LectureTag(1L,lecture,new Tag(Tag.Type.고3)));
        lectureTagsSample.add(new LectureTag(1L,lecture,new Tag(Tag.Type.메가스터디)));

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

        lecture.setName("수능 국어 완전 정복!");
        lecture.setLectureId(1L);
        lecture.setIntroduction("3개월 만에 완전정복해보세요!");
        lecture.setTeacher(teacher);
        lecture.setLectureTags(lectureTagsSample);

        lectureReviewPost.setLectureReviewPostId(1L);
        lectureReviewPost.setTitle("이 강의 추천합니다!");
        lectureReviewPost.setStarPoint(4.5);
        lectureReviewPost.setContent("강의하시는데 ~~궁 ~~궁 해서 추천합니다");
        lectureReviewPost.setCreatedAt("2023.03.10.18:52:36");
        lectureReviewPost.setModifiedAt("2023.03.10.18:52:36");
        lectureReviewPost.setViewCount(1);
        lectureReviewPost.setVoteCount(0);
        lectureReviewPost.setLecture(lecture);
        lectureReviewPost.setMember(member);

        teacherInfoResponse.setTeacherId(1L);
        teacherInfoResponse.setName("홍길동");
        teacherInfoResponse.setIntroduction("국어 최고의 강사!");
        teacherInfoResponse.setTags(tagTypesSample);

        teacherInfoResponses.add(teacherInfoResponse);
        teacherInfoResponses.add(teacherInfoResponse);

        lectureInfoResponse.setLectureId(1L);
        lectureInfoResponse.setName("수능 국어 완전정복");
        lectureInfoResponse.setIntroduction("3개월 만에 완전정복해보세요!");
        lectureInfoResponse.setStarPointAverage(4.2);
        lectureInfoResponse.setTags(tagTypesSample);
        lectureInfoResponse.setTeacher(teacherInfoResponse);

        lectureInfoResponses.add(lectureInfoResponse);
        lectureInfoResponses.add(lectureInfoResponse);


        lectureReviewPostInfoResponse.setLectureReviewPostId(1L);
        lectureReviewPostInfoResponse.setTitle("이 강의 추천합니다!");
        lectureReviewPostInfoResponse.setStarPoint(4.5);
        lectureReviewPostInfoResponse.setContent("강의하시는데 ~~궁 ~~궁 해서 추천합니다");
        lectureReviewPostInfoResponse.setCreatedAt("2023.03.10.18:52:36");
        lectureReviewPostInfoResponse.setModifiedAt("2023.03.10.18:52:36");
        lectureReviewPostInfoResponse.setViewCount(1);
        lectureReviewPostInfoResponse.setVoteCount(0);
        lectureReviewPostInfoResponse.setLecture(lectureInfoResponse);
        lectureReviewPostInfoResponse.setMember(member);

        lectureReviewPostInfoResponses.add(lectureReviewPostInfoResponse);
        lectureReviewPostInfoResponses.add(lectureReviewPostInfoResponse);

        lectureReviewPostCommentResponse.setLectureReviewPostCommentId(1L);
        lectureReviewPostCommentResponse.setContent("이사람 재대로 들은거 맞음?");
        lectureReviewPostCommentResponse.setVoteCount(0);
        lectureReviewPostCommentResponse.setCreatedAt("2023.03.10.18:52:36");
        lectureReviewPostCommentResponse.setModifiedAt("2023.03.10.18:52:36");
        lectureReviewPostCommentResponse.setLectureReviewPostId(1L);
        lectureReviewPostCommentResponse.setMember(member);

        lectureReviewPostCommentResponses.add(lectureReviewPostCommentResponse);
        lectureReviewPostCommentResponses.add(lectureReviewPostCommentResponse);
    }
}
