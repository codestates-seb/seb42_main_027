package ynzmz.server.teacher.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.tag.dto.TeacherGradeTagDto;
import ynzmz.server.tag.dto.TeacherPlatformTagDto;
import ynzmz.server.tag.dto.TeacherSubjectTagDto;
import ynzmz.server.tag.mappingtable.teacher.TeacherGradeTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherPlatformTag;
import ynzmz.server.tag.mappingtable.teacher.TeacherSubjectTag;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-14T18:21:21+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher teacherToTeacherPost(TeacherDto.Post teacherPost) {
        if ( teacherPost == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setName( teacherPost.getName() );
        teacher.setIntroduction( teacherPost.getIntroduction() );
        teacher.setImageUrl( teacherPost.getImageUrl() );
        List<String> list = teacherPost.getProfile();
        if ( list != null ) {
            teacher.setProfile( new ArrayList<String>( list ) );
        }
        List<String> list1 = teacherPost.getAnalects();
        if ( list1 != null ) {
            teacher.setAnalects( new ArrayList<String>( list1 ) );
        }

        return teacher;
    }

    @Override
    public Teacher teacherToTeacherPatch(TeacherDto.Patch teacherPatch) {
        if ( teacherPatch == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setName( teacherPatch.getName() );
        teacher.setIntroduction( teacherPatch.getIntroduction() );
        teacher.setImageUrl( teacherPatch.getImageUrl() );
        List<String> list = teacherPatch.getProfile();
        if ( list != null ) {
            teacher.setProfile( new ArrayList<String>( list ) );
        }
        List<String> list1 = teacherPatch.getAnalects();
        if ( list1 != null ) {
            teacher.setAnalects( new ArrayList<String>( list1 ) );
        }

        return teacher;
    }

    @Override
    public TeacherDto.SimpleInfoResponse teacherInfoResponseToTeacher(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.SimpleInfoResponse simpleInfoResponse = new TeacherDto.SimpleInfoResponse();

        simpleInfoResponse.setTeacherId( teacher.getTeacherId() );
        simpleInfoResponse.setName( teacher.getName() );
        simpleInfoResponse.setStarPointAverage( teacher.getStarPointAverage() );

        return simpleInfoResponse;
    }

    @Override
    public List<TeacherDto.SimpleInfoResponse> teacherInfoResponsesToTeachers(List<Teacher> teachers) {
        if ( teachers == null ) {
            return null;
        }

        List<TeacherDto.SimpleInfoResponse> list = new ArrayList<TeacherDto.SimpleInfoResponse>( teachers.size() );
        for ( Teacher teacher : teachers ) {
            list.add( teacherInfoResponseToTeacher( teacher ) );
        }

        return list;
    }

    @Override
    public TeacherDto.ListPageResponse teacherListPageResponseToTeacher(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.ListPageResponse listPageResponse = new TeacherDto.ListPageResponse();

        listPageResponse.setTeacherId( teacher.getTeacherId() );
        listPageResponse.setName( teacher.getName() );
        listPageResponse.setIntroduction( teacher.getIntroduction() );
        listPageResponse.setImageUrl( teacher.getImageUrl() );
        listPageResponse.setStarPointAverage( teacher.getStarPointAverage() );
        listPageResponse.setTotalReviewCount( teacher.getTotalReviewCount() );
        listPageResponse.setGradeTags( teacherGradeTagListToResponseList( teacher.getGradeTags() ) );
        listPageResponse.setSubjectTags( teacherSubjectTagListToResponseList( teacher.getSubjectTags() ) );
        listPageResponse.setPlatformTags( teacherPlatformTagListToResponseList( teacher.getPlatformTags() ) );

        return listPageResponse;
    }

    @Override
    public List<TeacherDto.ListPageResponse> teacherListPageResponsesToTeachers(List<Teacher> teachers) {
        if ( teachers == null ) {
            return null;
        }

        List<TeacherDto.ListPageResponse> list = new ArrayList<TeacherDto.ListPageResponse>( teachers.size() );
        for ( Teacher teacher : teachers ) {
            list.add( teacherListPageResponseToTeacher( teacher ) );
        }

        return list;
    }

    @Override
    public TeacherDto.DetailPageResponse teacherDetailPageResponseToTeacher(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.DetailPageResponse detailPageResponse = new TeacherDto.DetailPageResponse();

        detailPageResponse.setTeacherId( teacher.getTeacherId() );
        detailPageResponse.setName( teacher.getName() );
        detailPageResponse.setIntroduction( teacher.getIntroduction() );
        detailPageResponse.setImageUrl( teacher.getImageUrl() );
        List<String> list = teacher.getProfile();
        if ( list != null ) {
            detailPageResponse.setProfile( new ArrayList<String>( list ) );
        }
        List<String> list1 = teacher.getAnalects();
        if ( list1 != null ) {
            detailPageResponse.setAnalects( new ArrayList<String>( list1 ) );
        }
        detailPageResponse.setStarPointAverage( teacher.getStarPointAverage() );
        detailPageResponse.setTotalReviewCount( teacher.getTotalReviewCount() );
        List<TeacherGradeTag> list2 = teacher.getGradeTags();
        if ( list2 != null ) {
            detailPageResponse.setGradeTags( new ArrayList<TeacherGradeTag>( list2 ) );
        }
        List<TeacherSubjectTag> list3 = teacher.getSubjectTags();
        if ( list3 != null ) {
            detailPageResponse.setSubjectTags( new ArrayList<TeacherSubjectTag>( list3 ) );
        }
        List<TeacherPlatformTag> list4 = teacher.getPlatformTags();
        if ( list4 != null ) {
            detailPageResponse.setPlatformTags( new ArrayList<TeacherPlatformTag>( list4 ) );
        }
        List<Lecture> list5 = teacher.getLectures();
        if ( list5 != null ) {
            detailPageResponse.setLectures( new ArrayList<Lecture>( list5 ) );
        }

        return detailPageResponse;
    }

    @Override
    public List<TeacherDto.DetailPageResponse> teacherDetailPageResponsesToTeachers(List<Teacher> teachers) {
        if ( teachers == null ) {
            return null;
        }

        List<TeacherDto.DetailPageResponse> list = new ArrayList<TeacherDto.DetailPageResponse>( teachers.size() );
        for ( Teacher teacher : teachers ) {
            list.add( teacherDetailPageResponseToTeacher( teacher ) );
        }

        return list;
    }

    @Override
    public TeacherDto.ReviewDetailPageResponse teacherReviewDetailPageResponseToTeacher(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.ReviewDetailPageResponse reviewDetailPageResponse = new TeacherDto.ReviewDetailPageResponse();

        reviewDetailPageResponse.setTeacherId( teacher.getTeacherId() );
        reviewDetailPageResponse.setName( teacher.getName() );
        reviewDetailPageResponse.setIntroduction( teacher.getIntroduction() );
        reviewDetailPageResponse.setImageUrl( teacher.getImageUrl() );
        List<String> list = teacher.getProfile();
        if ( list != null ) {
            reviewDetailPageResponse.setProfile( new ArrayList<String>( list ) );
        }
        List<String> list1 = teacher.getAnalects();
        if ( list1 != null ) {
            reviewDetailPageResponse.setAnalects( new ArrayList<String>( list1 ) );
        }
        reviewDetailPageResponse.setStarPointAverage( teacher.getStarPointAverage() );
        List<TeacherGradeTag> list2 = teacher.getGradeTags();
        if ( list2 != null ) {
            reviewDetailPageResponse.setGradeTags( new ArrayList<TeacherGradeTag>( list2 ) );
        }
        List<TeacherSubjectTag> list3 = teacher.getSubjectTags();
        if ( list3 != null ) {
            reviewDetailPageResponse.setSubjectTags( new ArrayList<TeacherSubjectTag>( list3 ) );
        }
        List<TeacherPlatformTag> list4 = teacher.getPlatformTags();
        if ( list4 != null ) {
            reviewDetailPageResponse.setPlatformTags( new ArrayList<TeacherPlatformTag>( list4 ) );
        }
        reviewDetailPageResponse.setLectures( lectureListToTeacherReviewDetailPageResponseList( teacher.getLectures() ) );

        return reviewDetailPageResponse;
    }

    @Override
    public List<TeacherDto.ReviewDetailPageResponse> teacherReviewDetailPageResponsesToTeachers(List<Teacher> teachers) {
        if ( teachers == null ) {
            return null;
        }

        List<TeacherDto.ReviewDetailPageResponse> list = new ArrayList<TeacherDto.ReviewDetailPageResponse>( teachers.size() );
        for ( Teacher teacher : teachers ) {
            list.add( teacherReviewDetailPageResponseToTeacher( teacher ) );
        }

        return list;
    }

    protected List<TeacherGradeTagDto.Response> teacherGradeTagListToResponseList(List<TeacherGradeTag> list) {
        if ( list == null ) {
            return null;
        }

        List<TeacherGradeTagDto.Response> list1 = new ArrayList<TeacherGradeTagDto.Response>( list.size() );
        for ( TeacherGradeTag teacherGradeTag : list ) {
            list1.add( TeacherGradeTagResponseToTeacherGradeTag( teacherGradeTag ) );
        }

        return list1;
    }

    protected List<TeacherSubjectTagDto.Response> teacherSubjectTagListToResponseList(List<TeacherSubjectTag> list) {
        if ( list == null ) {
            return null;
        }

        List<TeacherSubjectTagDto.Response> list1 = new ArrayList<TeacherSubjectTagDto.Response>( list.size() );
        for ( TeacherSubjectTag teacherSubjectTag : list ) {
            list1.add( TeacherSubjectTagResponseToTeacherSubjectTag( teacherSubjectTag ) );
        }

        return list1;
    }

    protected List<TeacherPlatformTagDto.Response> teacherPlatformTagListToResponseList(List<TeacherPlatformTag> list) {
        if ( list == null ) {
            return null;
        }

        List<TeacherPlatformTagDto.Response> list1 = new ArrayList<TeacherPlatformTagDto.Response>( list.size() );
        for ( TeacherPlatformTag teacherPlatformTag : list ) {
            list1.add( TeacherPlatformTagResponseToTeacherPlatformTag( teacherPlatformTag ) );
        }

        return list1;
    }

    protected LectureDto.TeacherReviewDetailPageResponse lectureToTeacherReviewDetailPageResponse(Lecture lecture) {
        if ( lecture == null ) {
            return null;
        }

        LectureDto.TeacherReviewDetailPageResponse teacherReviewDetailPageResponse = new LectureDto.TeacherReviewDetailPageResponse();

        if ( lecture.getLectureId() != null ) {
            teacherReviewDetailPageResponse.setLectureId( lecture.getLectureId() );
        }
        teacherReviewDetailPageResponse.setTitle( lecture.getTitle() );
        teacherReviewDetailPageResponse.setStatus( lecture.getStatus() );
        List<LectureReview> list = lecture.getLectureReviews();
        if ( list != null ) {
            teacherReviewDetailPageResponse.setLectureReviews( new ArrayList<LectureReview>( list ) );
        }

        return teacherReviewDetailPageResponse;
    }

    protected List<LectureDto.TeacherReviewDetailPageResponse> lectureListToTeacherReviewDetailPageResponseList(List<Lecture> list) {
        if ( list == null ) {
            return null;
        }

        List<LectureDto.TeacherReviewDetailPageResponse> list1 = new ArrayList<LectureDto.TeacherReviewDetailPageResponse>( list.size() );
        for ( Lecture lecture : list ) {
            list1.add( lectureToTeacherReviewDetailPageResponse( lecture ) );
        }

        return list1;
    }
}
