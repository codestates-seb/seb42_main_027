package ynzmz.server.helper;

import ynzmz.server.lecturereviewpost.dto.LectureReviewPostDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.tag.entity.Tag;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class StubData {
    public static final List<String> tagsSample = new ArrayList<>();
    public static final List<Tag.Type> tagTypesSample = new ArrayList<>();
    public static final Member member = new Member();
    public static final Teacher teacher = new Teacher();
    public static final TeacherDto.InfoResponse teacherInfoResponse = new TeacherDto.InfoResponse();
    public static final List<TeacherDto.InfoResponse> teacherInfoResponses = new ArrayList<>();


    public static final LectureReviewPostDto.InfoResponse lectureReviewPostInfoResponse = new LectureReviewPostDto.InfoResponse();
    public static void init(){

        tagsSample.add("국어");
        tagsSample.add("고3");
        tagsSample.add("메가스터디");

        tagTypesSample.add(Tag.Type.국어);
        tagTypesSample.add(Tag.Type.고3);
        tagTypesSample.add(Tag.Type.메가스터디);

        teacherInfoResponse.setTeacherId(1L);
        teacherInfoResponse.setName("홍길동");
        teacherInfoResponse.setIntroduction("국어 최고의 강사!");
        teacherInfoResponse.setTags(tagTypesSample);

        lectureReviewPostInfoResponse.setLectureReviewPostId(1L);

        teacherInfoResponses.add(teacherInfoResponse);
        teacherInfoResponses.add(teacherInfoResponse);


    }
}
