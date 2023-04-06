package ynzmz.server.board.teacher.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.board.lecture.dto.LectureDto;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.board.review.lecture.dto.LectureReviewDto;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.board.teacher.dto.TeacherDto;
import ynzmz.server.board.teacher.entity.Teacher;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.tag.dto.GradeTagDto;
import ynzmz.server.tag.dto.PlatformTagDto;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.entity.lecture.LectureGradeTag;
import ynzmz.server.tag.entity.lecture.LecturePlatformTag;
import ynzmz.server.tag.entity.lecture.LectureSubjectTag;
import ynzmz.server.tag.entity.teacher.TeacherGradeTag;
import ynzmz.server.tag.entity.teacher.TeacherPlatformTag;
import ynzmz.server.tag.entity.teacher.TeacherSubjectTag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-06T14:51:10+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher teacherPostToTeacher(TeacherDto.Post teacherPost) {
        if ( teacherPost == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setName( teacherPost.getName() );
        teacher.setIntroduction( teacherPost.getIntroduction() );
        teacher.setProfileImageUrl( teacherPost.getProfileImageUrl() );
        teacher.setRealImageUrl( teacherPost.getRealImageUrl() );
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
    public Teacher teacherPatchToTeacher(TeacherDto.Patch teacherPatch) {
        if ( teacherPatch == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setName( teacherPatch.getName() );
        teacher.setIntroduction( teacherPatch.getIntroduction() );
        teacher.setProfileImageUrl( teacherPatch.getProfileImageUrl() );
        teacher.setRealImageUrl( teacherPatch.getRealImageUrl() );
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
    public TeacherDto.SimpleInfoResponse teacherToTeacherSimpleInfoResponse(Teacher teacher) {
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
    public TeacherDto.ListPageResponse teacherToTeacherListPageResponse(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.ListPageResponse listPageResponse = new TeacherDto.ListPageResponse();

        listPageResponse.setTeacherId( teacher.getTeacherId() );
        listPageResponse.setName( teacher.getName() );
        listPageResponse.setIntroduction( teacher.getIntroduction() );
        listPageResponse.setProfileImageUrl( teacher.getProfileImageUrl() );
        listPageResponse.setRealImageUrl( teacher.getRealImageUrl() );
        listPageResponse.setStarPointAverage( teacher.getStarPointAverage() );
        listPageResponse.setTotalReviewCount( teacher.getTotalReviewCount() );
        listPageResponse.setGradeTags( teacherGradeTagListToResponseList( teacher.getGradeTags() ) );
        listPageResponse.setSubjectTags( teacherSubjectTagListToResponseList( teacher.getSubjectTags() ) );
        listPageResponse.setPlatformTags( teacherPlatformTagListToResponseList( teacher.getPlatformTags() ) );

        return listPageResponse;
    }

    @Override
    public List<TeacherDto.ListPageResponse> teachersToTeacherListPageResponses(List<Teacher> teachers) {
        if ( teachers == null ) {
            return null;
        }

        List<TeacherDto.ListPageResponse> list = new ArrayList<TeacherDto.ListPageResponse>( teachers.size() );
        for ( Teacher teacher : teachers ) {
            list.add( teacherToTeacherListPageResponse( teacher ) );
        }

        return list;
    }

    @Override
    public TeacherDto.DetailPageResponse teacherToTeacherDetailPageResponse(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.DetailPageResponse detailPageResponse = new TeacherDto.DetailPageResponse();

        detailPageResponse.setTeacherId( teacher.getTeacherId() );
        detailPageResponse.setName( teacher.getName() );
        detailPageResponse.setIntroduction( teacher.getIntroduction() );
        detailPageResponse.setProfileImageUrl( teacher.getProfileImageUrl() );
        detailPageResponse.setRealImageUrl( teacher.getRealImageUrl() );
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
        detailPageResponse.setGradeTags( teacherGradeTagListToResponseList( teacher.getGradeTags() ) );
        detailPageResponse.setSubjectTags( teacherSubjectTagListToResponseList( teacher.getSubjectTags() ) );
        detailPageResponse.setPlatformTags( teacherPlatformTagListToResponseList( teacher.getPlatformTags() ) );
        detailPageResponse.setLectures( lectureListToListPageResponseList( teacher.getLectures() ) );

        return detailPageResponse;
    }

    @Override
    public TeacherDto.ReviewDetailPageResponse teacherToTeacherReviewDetailPageResponse(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.ReviewDetailPageResponse reviewDetailPageResponse = new TeacherDto.ReviewDetailPageResponse();

        reviewDetailPageResponse.setTeacherId( teacher.getTeacherId() );
        reviewDetailPageResponse.setName( teacher.getName() );
        reviewDetailPageResponse.setIntroduction( teacher.getIntroduction() );
        reviewDetailPageResponse.setProfileImageUrl( teacher.getProfileImageUrl() );
        reviewDetailPageResponse.setRealImageUrl( teacher.getRealImageUrl() );
        List<String> list = teacher.getProfile();
        if ( list != null ) {
            reviewDetailPageResponse.setProfile( new ArrayList<String>( list ) );
        }
        List<String> list1 = teacher.getAnalects();
        if ( list1 != null ) {
            reviewDetailPageResponse.setAnalects( new ArrayList<String>( list1 ) );
        }
        reviewDetailPageResponse.setStarPointAverage( teacher.getStarPointAverage() );
        reviewDetailPageResponse.setTotalReviewCount( teacher.getTotalReviewCount() );
        reviewDetailPageResponse.setGradeTags( teacherGradeTagListToResponseList( teacher.getGradeTags() ) );
        reviewDetailPageResponse.setSubjectTags( teacherSubjectTagListToResponseList( teacher.getSubjectTags() ) );
        reviewDetailPageResponse.setPlatformTags( teacherPlatformTagListToResponseList( teacher.getPlatformTags() ) );
        reviewDetailPageResponse.setLectures( lectureListToTeacherReviewDetailPageResponseList( teacher.getLectures() ) );

        return reviewDetailPageResponse;
    }

    protected List<GradeTagDto.Response> teacherGradeTagListToResponseList(List<TeacherGradeTag> list) {
        if ( list == null ) {
            return null;
        }

        List<GradeTagDto.Response> list1 = new ArrayList<GradeTagDto.Response>( list.size() );
        for ( TeacherGradeTag teacherGradeTag : list ) {
            list1.add( teacherGradeTagToGradeTagResponse( teacherGradeTag ) );
        }

        return list1;
    }

    protected List<SubjectTagDto.Response> teacherSubjectTagListToResponseList(List<TeacherSubjectTag> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectTagDto.Response> list1 = new ArrayList<SubjectTagDto.Response>( list.size() );
        for ( TeacherSubjectTag teacherSubjectTag : list ) {
            list1.add( teacherSubjectTagToSubjectTagResponse( teacherSubjectTag ) );
        }

        return list1;
    }

    protected List<PlatformTagDto.Response> teacherPlatformTagListToResponseList(List<TeacherPlatformTag> list) {
        if ( list == null ) {
            return null;
        }

        List<PlatformTagDto.Response> list1 = new ArrayList<PlatformTagDto.Response>( list.size() );
        for ( TeacherPlatformTag teacherPlatformTag : list ) {
            list1.add( teacherPlatformTagToPlatformTagResponse( teacherPlatformTag ) );
        }

        return list1;
    }

    protected GradeTagDto.Response lectureGradeTagToResponse(LectureGradeTag lectureGradeTag) {
        if ( lectureGradeTag == null ) {
            return null;
        }

        GradeTagDto.Response response = new GradeTagDto.Response();

        response.setGradeTag( map( lectureGradeTag.getGradeTag() ) );

        return response;
    }

    protected List<GradeTagDto.Response> lectureGradeTagListToResponseList(List<LectureGradeTag> list) {
        if ( list == null ) {
            return null;
        }

        List<GradeTagDto.Response> list1 = new ArrayList<GradeTagDto.Response>( list.size() );
        for ( LectureGradeTag lectureGradeTag : list ) {
            list1.add( lectureGradeTagToResponse( lectureGradeTag ) );
        }

        return list1;
    }

    protected SubjectTagDto.Response lectureSubjectTagToResponse(LectureSubjectTag lectureSubjectTag) {
        if ( lectureSubjectTag == null ) {
            return null;
        }

        SubjectTagDto.Response response = new SubjectTagDto.Response();

        response.setSubjectTag( map( lectureSubjectTag.getSubjectTag() ) );

        return response;
    }

    protected List<SubjectTagDto.Response> lectureSubjectTagListToResponseList(List<LectureSubjectTag> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectTagDto.Response> list1 = new ArrayList<SubjectTagDto.Response>( list.size() );
        for ( LectureSubjectTag lectureSubjectTag : list ) {
            list1.add( lectureSubjectTagToResponse( lectureSubjectTag ) );
        }

        return list1;
    }

    protected PlatformTagDto.Response lecturePlatformTagToResponse(LecturePlatformTag lecturePlatformTag) {
        if ( lecturePlatformTag == null ) {
            return null;
        }

        PlatformTagDto.Response response = new PlatformTagDto.Response();

        response.setPlatformTag( map( lecturePlatformTag.getPlatformTag() ) );

        return response;
    }

    protected List<PlatformTagDto.Response> lecturePlatformTagListToResponseList(List<LecturePlatformTag> list) {
        if ( list == null ) {
            return null;
        }

        List<PlatformTagDto.Response> list1 = new ArrayList<PlatformTagDto.Response>( list.size() );
        for ( LecturePlatformTag lecturePlatformTag : list ) {
            list1.add( lecturePlatformTagToResponse( lecturePlatformTag ) );
        }

        return list1;
    }

    protected LectureDto.ListPageResponse lectureToListPageResponse(Lecture lecture) {
        if ( lecture == null ) {
            return null;
        }

        LectureDto.ListPageResponse listPageResponse = new LectureDto.ListPageResponse();

        if ( lecture.getLectureId() != null ) {
            listPageResponse.setLectureId( lecture.getLectureId() );
        }
        listPageResponse.setTitle( lecture.getTitle() );
        listPageResponse.setIntroduction( lecture.getIntroduction() );
        listPageResponse.setStatus( lecture.getStatus() );
        listPageResponse.setStarPointAverage( lecture.getStarPointAverage() );
        listPageResponse.setTotalReviewCount( lecture.getTotalReviewCount() );
        listPageResponse.setGradeTags( lectureGradeTagListToResponseList( lecture.getGradeTags() ) );
        listPageResponse.setSubjectTags( lectureSubjectTagListToResponseList( lecture.getSubjectTags() ) );
        listPageResponse.setPlatformTags( lecturePlatformTagListToResponseList( lecture.getPlatformTags() ) );
        listPageResponse.setTeacher( teacherToTeacherSimpleInfoResponse( lecture.getTeacher() ) );

        return listPageResponse;
    }

    protected List<LectureDto.ListPageResponse> lectureListToListPageResponseList(List<Lecture> list) {
        if ( list == null ) {
            return null;
        }

        List<LectureDto.ListPageResponse> list1 = new ArrayList<LectureDto.ListPageResponse>( list.size() );
        for ( Lecture lecture : list ) {
            list1.add( lectureToListPageResponse( lecture ) );
        }

        return list1;
    }

    protected LectureDto.SimpleInfoResponse lectureToSimpleInfoResponse(Lecture lecture) {
        if ( lecture == null ) {
            return null;
        }

        LectureDto.SimpleInfoResponse simpleInfoResponse = new LectureDto.SimpleInfoResponse();

        if ( lecture.getLectureId() != null ) {
            simpleInfoResponse.setLectureId( lecture.getLectureId() );
        }
        simpleInfoResponse.setTitle( lecture.getTitle() );
        simpleInfoResponse.setStarPointAverage( lecture.getStarPointAverage() );

        return simpleInfoResponse;
    }

    protected MemberDto.SimpleInfoResponse memberToSimpleInfoResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        Long memberId = null;
        String displayName = null;
        String iconImageUrl = null;
        String state = null;

        memberId = member.getMemberId();
        displayName = member.getDisplayName();
        iconImageUrl = member.getIconImageUrl();
        if ( member.getState() != null ) {
            state = member.getState().name();
        }

        MemberDto.SimpleInfoResponse simpleInfoResponse = new MemberDto.SimpleInfoResponse( memberId, displayName, iconImageUrl, state );

        return simpleInfoResponse;
    }

    protected LectureReviewDto.ListPageResponse lectureReviewToListPageResponse(LectureReview lectureReview) {
        if ( lectureReview == null ) {
            return null;
        }

        LectureReviewDto.ListPageResponse listPageResponse = new LectureReviewDto.ListPageResponse();

        if ( lectureReview.getLectureReviewId() != null ) {
            listPageResponse.setLectureReviewId( lectureReview.getLectureReviewId() );
        }
        listPageResponse.setTitle( lectureReview.getTitle() );
        listPageResponse.setStarPoint( lectureReview.getStarPoint() );
        listPageResponse.setContent( lectureReview.getContent() );
        listPageResponse.setCreatedAt( lectureReview.getCreatedAt() );
        listPageResponse.setModifiedAt( lectureReview.getModifiedAt() );
        listPageResponse.setViewCount( lectureReview.getViewCount() );
        listPageResponse.setVoteCount( lectureReview.getVoteCount() );
        listPageResponse.setTotalCommentCount( lectureReview.getTotalCommentCount() );
        listPageResponse.setLecture( lectureToSimpleInfoResponse( lectureReview.getLecture() ) );
        listPageResponse.setMember( memberToSimpleInfoResponse( lectureReview.getMember() ) );

        return listPageResponse;
    }

    protected List<LectureReviewDto.ListPageResponse> lectureReviewListToListPageResponseList(List<LectureReview> list) {
        if ( list == null ) {
            return null;
        }

        List<LectureReviewDto.ListPageResponse> list1 = new ArrayList<LectureReviewDto.ListPageResponse>( list.size() );
        for ( LectureReview lectureReview : list ) {
            list1.add( lectureReviewToListPageResponse( lectureReview ) );
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
        teacherReviewDetailPageResponse.setLectureReviews( lectureReviewListToListPageResponseList( lecture.getLectureReviews() ) );

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
