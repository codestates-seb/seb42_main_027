package ynzmz.server.lecture.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.tag.mappingtable.lecture.LectureGradeTag;
import ynzmz.server.tag.mappingtable.lecture.LecturePlatformTag;
import ynzmz.server.tag.mappingtable.lecture.LectureSubjectTag;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-14T18:21:22+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16.1 (Oracle Corporation)"
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
    public List<LectureDto.SimpleInfoResponse> lectureInfoResponsesToLectures(List<Lecture> lectures) {
        if ( lectures == null ) {
            return null;
        }

        List<LectureDto.SimpleInfoResponse> list = new ArrayList<LectureDto.SimpleInfoResponse>( lectures.size() );
        for ( Lecture lecture : lectures ) {
            list.add( lectureInfoResponseToLecture( lecture ) );
        }

        return list;
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
        List<LectureGradeTag> list = lecture.getGradeTags();
        if ( list != null ) {
            listPageResponse.setGradeTags( new ArrayList<LectureGradeTag>( list ) );
        }
        List<LectureSubjectTag> list1 = lecture.getSubjectTags();
        if ( list1 != null ) {
            listPageResponse.setSubjectTags( new ArrayList<LectureSubjectTag>( list1 ) );
        }
        List<LecturePlatformTag> list2 = lecture.getPlatformTags();
        if ( list2 != null ) {
            listPageResponse.setPlatformTags( new ArrayList<LecturePlatformTag>( list2 ) );
        }
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
        List<LectureGradeTag> list = lecture.getGradeTags();
        if ( list != null ) {
            detailPageResponse.setGradeTags( new ArrayList<LectureGradeTag>( list ) );
        }
        List<LectureSubjectTag> list1 = lecture.getSubjectTags();
        if ( list1 != null ) {
            detailPageResponse.setSubjectTags( new ArrayList<LectureSubjectTag>( list1 ) );
        }
        List<LecturePlatformTag> list2 = lecture.getPlatformTags();
        if ( list2 != null ) {
            detailPageResponse.setPlatformTags( new ArrayList<LecturePlatformTag>( list2 ) );
        }
        List<LectureReview> list3 = lecture.getLectureReviews();
        if ( list3 != null ) {
            detailPageResponse.setLectureReviews( new ArrayList<LectureReview>( list3 ) );
        }

        return detailPageResponse;
    }

    @Override
    public List<LectureDto.DetailPageResponse> lectureDetailPageResponsesToLectures(List<Lecture> lectures) {
        if ( lectures == null ) {
            return null;
        }

        List<LectureDto.DetailPageResponse> list = new ArrayList<LectureDto.DetailPageResponse>( lectures.size() );
        for ( Lecture lecture : lectures ) {
            list.add( lectureDetailPageResponseToLecture( lecture ) );
        }

        return list;
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
        List<LectureReview> list = lecture.getLectureReviews();
        if ( list != null ) {
            teacherReviewDetailPageResponse.setLectureReviews( new ArrayList<LectureReview>( list ) );
        }

        return teacherReviewDetailPageResponse;
    }

    @Override
    public List<LectureDto.TeacherReviewDetailPageResponse> lectureTeacherReviewDetailPageResponsesToLectures(List<Lecture> lectures) {
        if ( lectures == null ) {
            return null;
        }

        List<LectureDto.TeacherReviewDetailPageResponse> list = new ArrayList<LectureDto.TeacherReviewDetailPageResponse>( lectures.size() );
        for ( Lecture lecture : lectures ) {
            list.add( lectureTeacherReviewDetailPageResponseToLecture( lecture ) );
        }

        return list;
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
}
