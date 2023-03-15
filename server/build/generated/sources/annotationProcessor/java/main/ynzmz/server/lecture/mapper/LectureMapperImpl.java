package ynzmz.server.lecture.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.review.lecture.dto.LectureReviewDto;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.tag.dto.GradeTagDto;
import ynzmz.server.tag.dto.PlatformTagDto;
import ynzmz.server.tag.dto.SubjectTagDto;
import ynzmz.server.tag.mappingtable.lecture.LectureGradeTag;
import ynzmz.server.tag.mappingtable.lecture.LecturePlatformTag;
import ynzmz.server.tag.mappingtable.lecture.LectureSubjectTag;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T02:57:04+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class LectureMapperImpl implements LectureMapper {

    @Override
    public Lecture lectureToLecturePost(LectureDto.Post lecturePost) {
        if ( lecturePost == null ) {
            return null;
        }

        Lecture lecture = new Lecture();

        lecture.setTitle( lecturePost.getTitle() );
        lecture.setIntroduction( lecturePost.getIntroduction() );
        if ( lecturePost.getStatus() != null ) {
            lecture.setStatus( Enum.valueOf( Lecture.Status.class, lecturePost.getStatus() ) );
        }

        return lecture;
    }

    @Override
    public Lecture lectureToLecturePatch(LectureDto.Patch lecurePatch) {
        if ( lecurePatch == null ) {
            return null;
        }

        Lecture lecture = new Lecture();

        lecture.setTitle( lecurePatch.getTitle() );
        lecture.setIntroduction( lecurePatch.getIntroduction() );
        if ( lecurePatch.getStatus() != null ) {
            lecture.setStatus( Enum.valueOf( Lecture.Status.class, lecurePatch.getStatus() ) );
        }

        return lecture;
    }

    @Override
    public LectureDto.SimpleInfoResponse lectureInfoResponseToLecture(Lecture lecture) {
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

    @Override
    public LectureDto.ListPageResponse lectureListPageResponseToLecture(Lecture lecture) {
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
        listPageResponse.setTeacher( teacherToSimpleInfoResponse( lecture.getTeacher() ) );

        return listPageResponse;
    }

    @Override
    public List<LectureDto.ListPageResponse> lectureListPageResponsesToLectures(List<Lecture> lectures) {
        if ( lectures == null ) {
            return null;
        }

        List<LectureDto.ListPageResponse> list = new ArrayList<LectureDto.ListPageResponse>( lectures.size() );
        for ( Lecture lecture : lectures ) {
            list.add( lectureListPageResponseToLecture( lecture ) );
        }

        return list;
    }

    @Override
    public LectureDto.DetailPageResponse lectureDetailPageResponseToLecture(Lecture lecture) {
        if ( lecture == null ) {
            return null;
        }

        LectureDto.DetailPageResponse detailPageResponse = new LectureDto.DetailPageResponse();

        if ( lecture.getLectureId() != null ) {
            detailPageResponse.setLectureId( lecture.getLectureId() );
        }
        detailPageResponse.setTitle( lecture.getTitle() );
        detailPageResponse.setIntroduction( lecture.getIntroduction() );
        detailPageResponse.setStatus( lecture.getStatus() );
        detailPageResponse.setTeacher( teacherToSimpleInfoResponse( lecture.getTeacher() ) );
        detailPageResponse.setStarPointAverage( lecture.getStarPointAverage() );
        detailPageResponse.setTotalReviewCount( lecture.getTotalReviewCount() );
        detailPageResponse.setGradeTags( lectureGradeTagListToResponseList( lecture.getGradeTags() ) );
        detailPageResponse.setSubjectTags( lectureSubjectTagListToResponseList( lecture.getSubjectTags() ) );
        detailPageResponse.setPlatformTags( lecturePlatformTagListToResponseList( lecture.getPlatformTags() ) );
        detailPageResponse.setLectureReviews( lectureReviewListToListPageResponseList( lecture.getLectureReviews() ) );

        return detailPageResponse;
    }

    @Override
    public LectureDto.TeacherReviewDetailPageResponse lectureTeacherReviewDetailPageResponseToLecture(Lecture lecture) {
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

    protected List<GradeTagDto.Response> lectureGradeTagListToResponseList(List<LectureGradeTag> list) {
        if ( list == null ) {
            return null;
        }

        List<GradeTagDto.Response> list1 = new ArrayList<GradeTagDto.Response>( list.size() );
        for ( LectureGradeTag lectureGradeTag : list ) {
            list1.add( LectureGradeTagResponseToLectureGradeTag( lectureGradeTag ) );
        }

        return list1;
    }

    protected List<SubjectTagDto.Response> lectureSubjectTagListToResponseList(List<LectureSubjectTag> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectTagDto.Response> list1 = new ArrayList<SubjectTagDto.Response>( list.size() );
        for ( LectureSubjectTag lectureSubjectTag : list ) {
            list1.add( LectureSubjectTagResponseToLectureSubjectTag( lectureSubjectTag ) );
        }

        return list1;
    }

    protected List<PlatformTagDto.Response> lecturePlatformTagListToResponseList(List<LecturePlatformTag> list) {
        if ( list == null ) {
            return null;
        }

        List<PlatformTagDto.Response> list1 = new ArrayList<PlatformTagDto.Response>( list.size() );
        for ( LecturePlatformTag lecturePlatformTag : list ) {
            list1.add( LecturePlatformTagResponseToLecturePlatformTag( lecturePlatformTag ) );
        }

        return list1;
    }

    protected TeacherDto.SimpleInfoResponse teacherToSimpleInfoResponse(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDto.SimpleInfoResponse simpleInfoResponse = new TeacherDto.SimpleInfoResponse();

        simpleInfoResponse.setTeacherId( teacher.getTeacherId() );
        simpleInfoResponse.setName( teacher.getName() );
        simpleInfoResponse.setStarPointAverage( teacher.getStarPointAverage() );

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
        listPageResponse.setLecture( lectureInfoResponseToLecture( lectureReview.getLecture() ) );
        listPageResponse.setMember( lectureReview.getMember() );

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
}