package ynzmz.server.lecture.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ynzmz.server.helper.StubData;
import ynzmz.server.lecture.dto.LectureDto;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.lecture.mapper.LectureMapper;
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.tag.service.TagService;

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
    private LectureTagService lectureTagService;

    @BeforeAll
    public static void init() {
        StubData.init();
    }
    @Test
    @DisplayName("강의 등록 테스트")
    void postLecture() throws Exception {

        LectureDto.Post mockPost = new LectureDto.Post("수능 국어 완전정복", "3개월 만에 완전정복해보세요!", tagsSample,1L);
        String jsonPost = gson.toJson(mockPost);

        when(lectureMapper.lectureInfoResponseToLecture(any())).thenReturn(LECTURE_SIMPLE_INFO_RESPONSE);


        ResultActions actions = mockMvc.perform(post("/lectures")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPost)
        );
        actions.andExpect(status().isCreated()).andDo(document("post-lectures",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestFields(
                        List.of(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그 (고정값 List)"),
                                fieldWithPath("teacherId").type(JsonFieldType.NUMBER).description("강의한 강사 식별번호")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점(소수점)"),
                                fieldWithPath("data.teacher.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강사 정보 수정 테스트")
    void patchLecture() throws Exception {
        LectureDto.Patch mockPatch = new LectureDto.Patch("수능 국어 완전정복(수정)", "3개월 만에 완전정복해보세요(수정)", tagsSample,1L);
        String jsonPatch = gson.toJson(mockPatch);
        LECTURE_SIMPLE_INFO_RESPONSE.setName("수능 국어 완전정복(수정)");
        LECTURE_SIMPLE_INFO_RESPONSE.setIntroduction("3개월 만에 완전정복해보세요(수정)");
        long lectureId = 1L;
        
        when(lectureMapper.lectureToLecturePatch(any())).thenReturn(new Lecture());
        when(lectureMapper.lectureInfoResponseToLecture(any())).thenReturn(LECTURE_SIMPLE_INFO_RESPONSE);

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
                                fieldWithPath("name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그 (고정값 List)"),
                                fieldWithPath("teacherId").type(JsonFieldType.NUMBER).description("강의한 강사 식별번호")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점(소수점)"),
                                fieldWithPath("data.teacher.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그")
                        )
                )
                ));
    }


    @Test
    @DisplayName("강의 전체 조회 테스트")
    void getAllLecture() throws Exception {

        when(lectureService.findLectures(anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new Lecture())), PageRequest.of(1,1),1));
        when(lectureMapper.lectureInfoResponsesToLectures(any())).thenReturn(LECTURE_SIMPLE_INFO_RESPONS);

        ResultActions actions =
                mockMvc.perform(
                        get("/lectures/")
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-all-lectures",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestParameters(
                        parameterWithName("page").description("요청 페이지"),
                        parameterWithName("size").description("요청 페이지당 출력개수")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.[].lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.[].introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.[].starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),
                                fieldWithPath("data.[].tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.[].teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
                                fieldWithPath("data.[].teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.[].teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.[].teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),
                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지정보 - 현재 페이지"),
                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지정보 - 페이지당 출력 갯수"),
                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 질문글수"),
                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 페이지수")
                        )
                ))
        );
    }
    @Test
    @DisplayName("강의 태그별 조회 테스트")
    void getLectureByTag() throws Exception {

        when(lectureService.findLectures(anyString(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new Lecture())), PageRequest.of(1,1),1));
        when(lectureMapper.lectureInfoResponsesToLectures(any())).thenReturn(LECTURE_SIMPLE_INFO_RESPONS);

        ResultActions actions =
                mockMvc.perform(
                        get("/lectures/")
                                .param("tag", "국어")
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-lectures-tags",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestParameters(
                        parameterWithName("tag").description("강의 키워드 태그명"),
                        parameterWithName("page").description("요청 페이지"),
                        parameterWithName("size").description("요청 페이지당 출력개수")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.[].lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.[].introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.[].starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),
                                fieldWithPath("data.[].tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.[].teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
                                fieldWithPath("data.[].teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.[].teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.[].teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),
                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지정보 - 현재 페이지"),
                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지정보 - 페이지당 출력 갯수"),
                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 질문글수"),
                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 페이지수")
                        )
                ))
        );
    }
    @Test
    @DisplayName("강의 강사별 조회 테스트")
    void getLecturesByTeacher() throws Exception {

        when(lectureService.findLecturesByTeacher(anyLong(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new Lecture())), PageRequest.of(1,1),1));
        when(lectureMapper.lectureInfoResponsesToLectures(any())).thenReturn(LECTURE_SIMPLE_INFO_RESPONS);

        ResultActions actions =
                mockMvc.perform(
                        get("/lectures/teacher")
                                .param("teacher", "1")
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-lectures-teacher",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestParameters(
                        parameterWithName("teacher").description("강의의 강사 식별번호"),
                        parameterWithName("page").description("요청 페이지"),
                        parameterWithName("size").description("요청 페이지당 출력개수")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.[].lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.[].introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.[].starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),
                                fieldWithPath("data.[].tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.[].teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
                                fieldWithPath("data.[].teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.[].teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.[].teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),
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

        when(lectureMapper.lectureInfoResponseToLecture(any())).thenReturn(LECTURE_SIMPLE_INFO_RESPONSE);

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
                        parameterWithName("lecture-id").description("강사 식별번호")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),
                                fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
                                fieldWithPath("data.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그")
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