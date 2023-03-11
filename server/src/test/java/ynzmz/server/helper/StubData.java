package ynzmz.server.helper;

import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecturereviewpost.dto.LectureReviewPostDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.tag.entity.Tag;
import ynzmz.server.tag.entity.TeacherTag;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class StubData {
    public static final List<String> tagsSample = new ArrayList<>();
    public static final List<Tag.Type> tagTypesSample = new ArrayList<>();

    public static final List<TeacherTag> teacherTags = new ArrayList<>();
    public static final Member member = new Member();
    public static final Teacher teacher = new Teacher();
    public static final TeacherDto.InfoResponse teacherInfoResponse = new TeacherDto.InfoResponse();
    public static final List<TeacherDto.InfoResponse> teacherInfoResponses = new ArrayList<>();
    public static final LectureDto.InfoResponse lectureInfoResponse = new LectureDto.InfoResponse();
    public static final List<LectureDto.InfoResponse> lectureInfoResponses = new ArrayList<>();
    public static final LectureReviewPostDto.InfoResponse lectureReviewPostInfoResponse = new LectureReviewPostDto.InfoResponse();
    public static void init(){

        tagsSample.add("국어");
        tagsSample.add("고3");
        tagsSample.add("메가스터디");

        tagTypesSample.add(Tag.Type.국어);
        tagTypesSample.add(Tag.Type.고3);
        tagTypesSample.add(Tag.Type.메가스터디);

        teacherTags.add(new TeacherTag(1L,teacher,new Tag(Tag.Type.국어)));
        teacherTags.add(new TeacherTag(1L,teacher,new Tag(Tag.Type.고3)));
        teacherTags.add(new TeacherTag(1L,teacher,new Tag(Tag.Type.메가스터디)));

        teacher.setTeacherId(1L);
        teacher.setName("홍길동");
        teacher.setIntroduction("국어 최고의 강사!");
        teacher.setTeacherTags(teacherTags);

        teacherInfoResponse.setTeacherId(1L);
        teacherInfoResponse.setName("홍길동");
        teacherInfoResponse.setIntroduction("국어 최고의 강사!");
        teacherInfoResponse.setTags(tagTypesSample);

        lectureReviewPostInfoResponse.setLectureReviewPostId(1L);

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

    }
}
