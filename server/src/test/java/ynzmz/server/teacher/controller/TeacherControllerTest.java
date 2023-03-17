package ynzmz.server.teacher.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ynzmz.server.helper.StubData;
import ynzmz.server.board.review.lecture.sevice.LectureReviewService;
import ynzmz.server.tag.service.TagService;
import ynzmz.server.teacher.dto.TeacherDto;
import ynzmz.server.teacher.entity.Teacher;
import ynzmz.server.teacher.mapper.TeacherMapper;
import ynzmz.server.teacher.service.TeacherService;
import ynzmz.server.utils.SecurityTestConfig;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ynzmz.server.helper.StubData.*;
import static ynzmz.server.utils.ApiDocumentUtils.getRequestPreProcessor;
import static ynzmz.server.utils.ApiDocumentUtils.getResponsePreProcessor;

@WebMvcTest(controllers = TeacherController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@WithMockUser
@Import(SecurityTestConfig.class)
class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private TeacherService teacherService;
    @MockBean
    private TeacherMapper teacherMapper;
    @MockBean
    private TagService tagService;
    @MockBean
    private LectureReviewService lectureReviewService;

    @BeforeAll
    public static void init() {
        StubData.init();
    }
    @Test
    @DisplayName("강사 등록 테스트")
    void postTeacher() throws Exception {
        TeacherDto.Post mockPost = TeacherDto.Post.builder()
                .name("홍길동")
                .introduction("강사 간단 소개!")
                .profile(profileSample)
                .analects(analectsSample)
                .imageUrl("이미지경로Url")
                .gradeTag(gradeTagStringSample)
                .subjectTag(subjectTagStringSample)
                .platformTag(platformTagStringSample)
                .build();
        String jsonPost = gson.toJson(mockPost);

        when(teacherMapper.teacherToTeacherSimpleInfoResponse(any())).thenReturn(teacherSimpleInfoResponse);


        ResultActions actions = mockMvc.perform(post("/teachers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPost)
        );
        actions.andExpect(status().isCreated()).andDo(document("post-teacher",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestFields(
                        List.of(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("profile").type(JsonFieldType.ARRAY).description("강사 약력 (List)"),
                                fieldWithPath("analects").type(JsonFieldType.ARRAY).description("강사 어록 (List)"),
                                fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("강사 이미지 url 경로"),
                                fieldWithPath("gradeTag").type(JsonFieldType.ARRAY).description("강사 담당 학년 태그 (지정값 List)"),
                                fieldWithPath("subjectTag").type(JsonFieldType.ARRAY).description("강사 담당 과목 태그 (지정값 List)"),
                                fieldWithPath("platformTag").type(JsonFieldType.ARRAY).description("강사 소속 플랫폼 태그 (지정값 List)")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 별점")
                        )
                ))
        );

    }


    @Test
    @DisplayName("강사 정보 수정 테스트")
    void patchTeacher() throws Exception {
        TeacherDto.Patch mockPatch = TeacherDto.Patch.builder()
                .name("홍길동")
                .introduction("강사 간단 소개!")
                .profile(profileSample)
                .analects(analectsSample)
                .imageUrl("이미지경로Url")
                .gradeTag(gradeTagStringSample)
                .subjectTag(subjectTagStringSample)
                .platformTag(platformTagStringSample)
                .build();
        String jsonPatch = gson.toJson(mockPatch);
        long teacherId = 1L;

        when(teacherMapper.teacherPatchToTeacher(any())).thenReturn(new Teacher());
        when(teacherMapper.teacherToTeacherSimpleInfoResponse(any())).thenReturn(teacherSimpleInfoResponse);

        ResultActions actions = mockMvc.perform(patch("/teachers/{teacher-id}",teacherId)
                .content(jsonPatch)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("patch-teacher",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("teacher-id").description("강사 식별번호")
                ),
                requestFields(
                        List.of(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("profile").type(JsonFieldType.ARRAY).description("강사 약력 (List)"),
                                fieldWithPath("analects").type(JsonFieldType.ARRAY).description("강사 어록 (List)"),
                                fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("강사 이미지 url 경로"),
                                fieldWithPath("gradeTag").type(JsonFieldType.ARRAY).description("강사 담당 학년 태그 (지정값 List)"),
                                fieldWithPath("subjectTag").type(JsonFieldType.ARRAY).description("강사 담당 과목 태그 (지정값 List)"),
                                fieldWithPath("platformTag").type(JsonFieldType.ARRAY).description("강사 소속 플랫폼 태그 (지정값 List)")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 별점")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강사 전체 조회")
    void getAllTeachers() throws Exception {
        when(teacherService.findTeachers(any(),any(),any(),anyString(),anyString(),anyString(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new Teacher())), PageRequest.of(1,1),1));
        when(teacherService.findTeachers(any(),any(),any(),anyString(),anyString(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new Teacher())), PageRequest.of(1,1),1));
        when(teacherMapper.teachersToTeacherListPageResponses(any())).thenReturn(teacherListPageResponses);

        ResultActions actions =
                mockMvc.perform(
                        get("/teachers/")
                                .param("grade", "고3")
                                .param("platform", "이투스")
                                .param("subject", "국어")
                                .param("name", "홍길동")
                                .param("sort", "starPointAverage")
                                .param("reverse", "on")
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-all-teachers",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestParameters(
                        parameterWithName("grade").description("강사 학년 태그 (지정값) (Optional)"),
                        parameterWithName("platform").description("강사 플랫폼 태그 (지정값) (Optional)"),
                        parameterWithName("subject").description("강사 과목 태그 (지정값) (Optional)"),
                        parameterWithName("name").description("강사 이름 (유사값 검색 '홍' 검색시 홍길동 나옴)"),
                        parameterWithName("sort").description("정렬 지정값 (Optional) = [최신순:teacherId],[별점순:starPointAverage],[이름순:name]"),
                        parameterWithName("reverse").description("쿼리 없으면 정순, 쿼리 있으면 역순 (Optional)"),
                        parameterWithName("page").description("요청 페이지"),
                        parameterWithName("size").description("요청 페이지당 출력개수")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.[].teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.[].introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.[].imageUrl").type(JsonFieldType.STRING).description("강사 이미지 url"),
                                fieldWithPath("data.[].starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 평점"),
                                fieldWithPath("data.[].totalReviewCount").type(JsonFieldType.NUMBER).description("강사 리뷰 총 갯수"),
                                fieldWithPath("data.[].gradeTags.[].gradeTag").type(JsonFieldType.STRING).description("강사 담당 학년 태그 (지정값 List)"),
                                fieldWithPath("data.[].subjectTags.[].subjectTag").type(JsonFieldType.STRING).description("강사 담당 과목 태그 (지정값 List)"),
                                fieldWithPath("data.[].platformTags.[].platformTag").type(JsonFieldType.STRING).description("강사 소속 플랫폼 태그 (지정값 List)"),
                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지정보 - 현재 페이지"),
                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지정보 - 페이지당 출력 갯수"),
                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 질문글수"),
                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 페이지수")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강사 디테일 페이지 조회")
    void getTeacherDetail() throws Exception {

        long teacherId = 1L;

        when(teacherService.findTeacherById(anyInt())).thenReturn(new Teacher());
        when(teacherMapper.teacherToTeacherDetailPageResponse(any())).thenReturn(teacherDetailPageResponse);

        ResultActions actions = mockMvc.perform(get("/teachers/{teacher-id}",teacherId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("get-teacher",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("teacher-id").description("강사 식별번호")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.imageUrl").type(JsonFieldType.STRING).description("강사 이미지 url"),
                                fieldWithPath("data.profile").type(JsonFieldType.ARRAY).description("강사 약력 (List)"),
                                fieldWithPath("data.analects").type(JsonFieldType.ARRAY).description("강사 어록 (List)"),
                                fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 평점"),
                                fieldWithPath("data.totalReviewCount").type(JsonFieldType.NUMBER).description("강사 리뷰 총 갯수"),
                                fieldWithPath("data.gradeTags.[].gradeTag").type(JsonFieldType.STRING).description("강사 담당 학년 태그 (지정값 List)"),
                                fieldWithPath("data.subjectTags.[].subjectTag").type(JsonFieldType.STRING).description("강사 담당 과목 태그 (지정값 List)"),
                                fieldWithPath("data.platformTags.[].platformTag").type(JsonFieldType.STRING).description("강사 소속 플랫폼 태그 (지정값 List)"),
                                fieldWithPath("data.lectures.[].lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.lectures.[].title").type(JsonFieldType.STRING).description("강의 타이틀명"),
                                fieldWithPath("data.lectures.[].introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.lectures.[].status").type(JsonFieldType.STRING).description("강의 진행상태 (예정,진행중,완강)"),
                                fieldWithPath("data.lectures.[].starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균 평점"),
                                fieldWithPath("data.lectures.[].totalReviewCount").type(JsonFieldType.NUMBER).description("강의 리뷰 총 갯수"),
                                fieldWithPath("data.lectures.[].gradeTags.[].gradeTag").type(JsonFieldType.STRING).description("강의 학년 태그 (지정값 List)"),
                                fieldWithPath("data.lectures.[].subjectTags.[].subjectTag").type(JsonFieldType.STRING).description("강의 과목 태그 (지정값 List)"),
                                fieldWithPath("data.lectures.[].platformTags.[].platformTag").type(JsonFieldType.STRING).description("강의 플랫폼 태그 (지정값 List)"),
                                fieldWithPath("data.lectures.[].teacher.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.lectures.[].teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.lectures.[].teacher.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 평점")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강사 리뷰 디테일 페이지 조회")
    void getTeacherReviewDetail() throws Exception {

        long teacherId = 1L;

        when(teacherService.findTeacherById(anyInt())).thenReturn(new Teacher());
        when(teacherMapper.teacherToTeacherReviewDetailPageResponse(any())).thenReturn(teacherReviewDetailPageResponse);
        when(lectureReviewService.findStarPointCountByTeacher(any())).thenReturn(starPointCount);

        ResultActions actions = mockMvc.perform(get("/teachers/{teacher-id}/review",teacherId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("get-teacher-review",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("teacher-id").description("강사 식별번호")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.imageUrl").type(JsonFieldType.STRING).description("강사 이미지 url"),
                                fieldWithPath("data.profile").type(JsonFieldType.ARRAY).description("강사 약력 (List)"),
                                fieldWithPath("data.analects").type(JsonFieldType.ARRAY).description("강사 어록 (List)"),
                                fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 평점"),
                                fieldWithPath("data.totalReviewCount").type(JsonFieldType.NUMBER).description("강사 리뷰 총 갯수"),
                                fieldWithPath("data.starPointCount.1점갯수").type(JsonFieldType.NUMBER).description("강사의 전체 1점 갯수"),
                                fieldWithPath("data.starPointCount.2점갯수").type(JsonFieldType.NUMBER).description("강사의 전체 2점 갯수"),
                                fieldWithPath("data.starPointCount.3점갯수").type(JsonFieldType.NUMBER).description("강사의 전체 3점 갯수"),
                                fieldWithPath("data.starPointCount.4점갯수").type(JsonFieldType.NUMBER).description("강사의 전체 4점 갯수"),
                                fieldWithPath("data.starPointCount.5점갯수").type(JsonFieldType.NUMBER).description("강사의 전체 5점 갯수"),
                                fieldWithPath("data.gradeTags.[].gradeTag").type(JsonFieldType.STRING).description("강사 담당 학년 태그 (지정값 List)"),
                                fieldWithPath("data.subjectTags.[].subjectTag").type(JsonFieldType.STRING).description("강사 담당 과목 태그 (지정값 List)"),
                                fieldWithPath("data.platformTags.[].platformTag").type(JsonFieldType.STRING).description("강사 소속 플랫폼 태그 (지정값 List)"),
                                fieldWithPath("data.lectures.[].lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.lectures.[].title").type(JsonFieldType.STRING).description("강의 타이틀명"),
                                fieldWithPath("data.lectures.[].status").type(JsonFieldType.STRING).description("강의 진행상태 (예정,진행중,완강)"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].lectureReviewId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].teacher.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].teacher.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 평점"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].lecture.lectureId").type(JsonFieldType.NUMBER).description("리뷰 작성 강의 식별번호"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].lecture.title").type(JsonFieldType.STRING).description("리뷰 작성 강의 이름"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].lecture.starPointAverage").type(JsonFieldType.NUMBER).description("리뷰 작성 강의 평균 평점"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].member.roles").type(JsonFieldType.ARRAY).description("작성자 권한"),
                                fieldWithPath("data.lectures.[].lectureReviews.[].member.memberStatus").type(JsonFieldType.STRING).description("작성자 상태값")
                        )
                ))
        );
    }
    @Test
    @DisplayName("강사 삭제")
    void deleteTeacher() throws Exception {
        long teacherId = 1L;

        ResultActions actions = mockMvc.perform(delete("/teachers/{teacher-id}",teacherId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("delete-teacher",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("teacher-id").description("강사 식별번호")
                )
                ));
    }
}