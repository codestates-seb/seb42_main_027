package ynzmz.server.lecture.controller;

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
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecture.mapper.LectureMapper;
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.review.lecture.sevice.LectureReviewService;
import ynzmz.server.tag.service.TagService;
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

@WebMvcTest(controllers = LectureController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@WithMockUser
@Import(SecurityTestConfig.class)
class LectureControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private LectureService lectureService;
    @MockBean
    private LectureMapper lectureMapper;
    @MockBean
    private TagService tagService;
    @MockBean
    private LectureReviewService lectureReviewService;

    @BeforeAll
    public static void init() {
        StubData.init();
    }
    @Test
    @DisplayName("강의 등록 테스트")
    void postLecture() throws Exception {

        LectureDto.Post mockPost = LectureDto.Post.builder()
                .title("강의 이름!")
                .introduction("강의 소개")
                .status("진행중")
                .gradeTag(gradeTagStringSample)
                .subjectTag(subjectTagStringSample)
                .platformTag(platformTagStringSample)
                .teacherId(1L)
                .build();
        String jsonPost = gson.toJson(mockPost);

        when(lectureMapper.lectureToLectureInfoResponse(any())).thenReturn(lectureSimpleInfoResponse);


        ResultActions actions = mockMvc.perform(post("/lectures")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPost)
        );
        actions.andExpect(status().isCreated()).andDo(document("post-lecture",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestFields(
                        List.of(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("강사 진행상태 지정값 = (예정,진행중,완강)"),
                                fieldWithPath("gradeTag").type(JsonFieldType.ARRAY).description("강사 담당 학년 태그 (지정값 List)"),
                                fieldWithPath("subjectTag").type(JsonFieldType.ARRAY).description("강사 담당 과목 태그 (지정값 List)"),
                                fieldWithPath("platformTag").type(JsonFieldType.ARRAY).description("강사 소속 플랫폼 태그 (지정값 List)"),
                                fieldWithPath("teacherId").type(JsonFieldType.NUMBER).description("강의한 강사 식별번호")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점(소수점)")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강사 정보 수정 테스트")
    void patchLecture() throws Exception {
        LectureDto.Patch mockPatch = LectureDto.Patch.builder()
                .title("강의 이름!")
                .introduction("강의 소개")
                .status("진행중")
                .gradeTag(gradeTagStringSample)
                .subjectTag(subjectTagStringSample)
                .platformTag(platformTagStringSample)
                .teacherId(1L)
                .build();
        String jsonPatch = gson.toJson(mockPatch);
        long lectureId = 1L;

        when(lectureMapper.lecturePatchToLecture(any())).thenReturn(new Lecture());
        when(lectureMapper.lectureToLectureInfoResponse(any())).thenReturn(lectureSimpleInfoResponse);

        ResultActions actions = mockMvc.perform(patch("/lectures/{lectures-id}",lectureId)
                .content(jsonPatch)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk())
                .andDo(document("patch-lectures",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lectures-id").description("강의 식별번호")
                ),
                requestFields(
                        List.of(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("강사 진행상태 지정값 = (예정,진행중,완강)"),
                                fieldWithPath("gradeTag").type(JsonFieldType.ARRAY).description("강사 담당 학년 태그 (지정값 List)"),
                                fieldWithPath("subjectTag").type(JsonFieldType.ARRAY).description("강사 담당 과목 태그 (지정값 List)"),
                                fieldWithPath("platformTag").type(JsonFieldType.ARRAY).description("강사 소속 플랫폼 태그 (지정값 List)"),
                                fieldWithPath("teacherId").type(JsonFieldType.NUMBER).description("강의한 강사 식별번호")
                        )
                ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("강의 이름"),
                                        fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점(소수점)")
                        )
                )
                ));
    }


    @Test
    @DisplayName("강의 전체 조회 테스트")
    void getAllLecture() throws Exception {

        when(lectureService.findLectures(any(),any(),any(),anyString(),anyString(),anyString(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new Lecture())), PageRequest.of(1,1),1));
        when(lectureService.findLectures(any(),any(),any(),anyString(),anyString(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new Lecture())), PageRequest.of(1,1),1));
        when(lectureMapper.lecturesToLectureListPageResponses(any())).thenReturn(lectureListPageResponses);

        ResultActions actions =
                mockMvc.perform(
                        get("/lectures/")
                                .param("grade", "고3")
                                .param("platform", "이투스")
                                .param("subject", "국어")
                                .param("title", "강의제목")
                                .param("sort", "starPointAverage")
                                .param("reverse", "on")
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-all-lectures",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestParameters(
                        parameterWithName("grade").description("강의 학년 태그 (지정값) (Optional)"),
                        parameterWithName("platform").description("강의 플랫폼 태그 (지정값) (Optional)"),
                        parameterWithName("subject").description("강의 과목 태그 (지정값) (Optional)"),
                        parameterWithName("title").description("강의 (유사값 검색 '홍' 검색시 홍길동 나옴)"),
                        parameterWithName("sort").description("정렬 지정값 (Optional) = [최신순:lectureId],[별점순:starPointAverage],[이름순:title]"),
                        parameterWithName("reverse").description("쿼리 없으면 정순, 쿼리 있으면 역순 (Optional)"),
                        parameterWithName("page").description("요청 페이지"),
                        parameterWithName("size").description("요청 페이지당 출력개수")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.[].lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.[].title").type(JsonFieldType.STRING).description("강의 타이틀명"),
                                fieldWithPath("data.[].introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.[].status").type(JsonFieldType.STRING).description("강의 진행상태 (예정,진행중,완강)"),
                                fieldWithPath("data.[].starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균 평점"),
                                fieldWithPath("data.[].totalReviewCount").type(JsonFieldType.NUMBER).description("강의 리뷰 총 갯수"),
                                fieldWithPath("data.[].gradeTags.[].gradeTag").type(JsonFieldType.STRING).description("강의 학년 태그 (지정값 List)"),
                                fieldWithPath("data.[].subjectTags.[].subjectTag").type(JsonFieldType.STRING).description("강의 과목 태그 (지정값 List)"),
                                fieldWithPath("data.[].platformTags.[].platformTag").type(JsonFieldType.STRING).description("강의 플랫폼 태그 (지정값 List)"),
                                fieldWithPath("data.[].teacher.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.[].teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.[].teacher.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 평점"),
                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지정보 - 현재 페이지"),
                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지정보 - 페이지당 출력 갯수"),
                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 질문글수"),
                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 페이지수")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강의 상세 조회 테스트")
    void getLecturesDetail() throws Exception {
        long lectureId = 1L;

        when(lectureMapper.lectureToLectureDetailPageResponse(any())).thenReturn(lectureDetailPageResponse);
        when(lectureReviewService.findStarPointCountByLecture(any())).thenReturn(starPointCount);


        ResultActions actions =
                mockMvc.perform(
                        get("/lectures/{lecture-id}",lectureId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-lecture",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-id").description("강의 식별번호")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("강의 타이틀명"),
                                fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.status").type(JsonFieldType.STRING).description("강의 진행상태 (예정,진행중,완강)"),
                                fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균 평점"),
                                fieldWithPath("data.totalReviewCount").type(JsonFieldType.NUMBER).description("강의 리뷰 총 갯수"),
                                fieldWithPath("data.starPointCount.1점갯수").type(JsonFieldType.NUMBER).description("강의의 전체 1점 갯수"),
                                fieldWithPath("data.starPointCount.2점갯수").type(JsonFieldType.NUMBER).description("강의의 전체 2점 갯수"),
                                fieldWithPath("data.starPointCount.3점갯수").type(JsonFieldType.NUMBER).description("강의의 전체 3점 갯수"),
                                fieldWithPath("data.starPointCount.4점갯수").type(JsonFieldType.NUMBER).description("강의의 전체 4점 갯수"),
                                fieldWithPath("data.starPointCount.5점갯수").type(JsonFieldType.NUMBER).description("강의의 전체 5점 갯수"),
                                fieldWithPath("data.gradeTags.[].gradeTag").type(JsonFieldType.STRING).description("강의 학년 태그 (지정값 List)"),
                                fieldWithPath("data.subjectTags.[].subjectTag").type(JsonFieldType.STRING).description("강의 과목 태그 (지정값 List)"),
                                fieldWithPath("data.platformTags.[].platformTag").type(JsonFieldType.STRING).description("강의 플랫폼 태그 (지정값 List)"),
                                fieldWithPath("data.teacher.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.teacher.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 평점"),
                                fieldWithPath("data.lectureReviews.[].lectureReviewId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
                                fieldWithPath("data.lectureReviews.[].title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("data.lectureReviews.[].starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("data.lectureReviews.[].content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("data.lectureReviews.[].createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.lectureReviews.[].modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.lectureReviews.[].viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
                                fieldWithPath("data.lectureReviews.[].voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),
                                fieldWithPath("data.lectureReviews.[].teacher.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.lectureReviews.[].teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.lectureReviews.[].teacher.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 평점"),
                                fieldWithPath("data.lectureReviews.[].lecture.lectureId").type(JsonFieldType.NUMBER).description("리뷰 작성 강의 식별번호"),
                                fieldWithPath("data.lectureReviews.[].lecture.title").type(JsonFieldType.STRING).description("리뷰 작성 강의 이름"),
                                fieldWithPath("data.lectureReviews.[].lecture.starPointAverage").type(JsonFieldType.NUMBER).description("리뷰 작성 강의 평균 평점"),
                                fieldWithPath("data.lectureReviews.[].member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
                                fieldWithPath("data.lectureReviews.[].member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                                fieldWithPath("data.lectureReviews.[].member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
                                fieldWithPath("data.lectureReviews.[].member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.lectureReviews.[].member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
                                fieldWithPath("data.lectureReviews.[].member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.lectureReviews.[].member.roles").type(JsonFieldType.ARRAY).description("작성자 권한"),
                                fieldWithPath("data.lectureReviews.[].member.memberStatus").type(JsonFieldType.STRING).description("작성자 상태값")

                        )
                ))
        );
    }

    @Test
    @DisplayName("강의 삭제 테스트")
    void deleteLecture() throws Exception {
        long lectureId = 1L;

        ResultActions actions =
                mockMvc.perform(
                        delete("/lectures/{lecture-id}",lectureId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("delete-lecture",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-id").description("강사 식별번호")
                ))
        );
    }
}