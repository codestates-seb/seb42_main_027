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
import ynzmz.server.review.lecture.sevice.LectureReviewService;
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

        when(teacherMapper.teacherInfoResponseToTeacher(any())).thenReturn(TEACHER_SIMPLE_INFO_RESPONSE);


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
                                fieldWithPath("gradeTag").type(JsonFieldType.ARRAY).description("강사 담당 학년 태그 (고정값 List)"),
                                fieldWithPath("subjectTag").type(JsonFieldType.ARRAY).description("강사 담당 과목 태그 (고정값 List)"),
                                fieldWithPath("platformTag").type(JsonFieldType.ARRAY).description("강사 소속 플랫폼 태그 (고정값 List)")
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

        when(teacherMapper.teacherToTeacherPatch(any())).thenReturn(new Teacher());
        when(teacherMapper.teacherInfoResponseToTeacher(any())).thenReturn(TEACHER_SIMPLE_INFO_RESPONSE);

        ResultActions actions = mockMvc.perform(patch("/teachers/{teacher-id}",teacherId)
                .content(jsonPatch)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("patch-teachers",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("teacher-id").description("강사 식별번호")
                ),
                requestFields(
                        List.of(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그 (고정값 List)")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강사 전체 조회")
    void getAllTeachers() throws Exception {

        when(teacherMapper.teacherInfoResponsesToTeachers(any())).thenReturn(TEACHER_SIMPLE_INFO_RESPONS);

        ResultActions actions =
                mockMvc.perform(
                        get("/teachers/")
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-all-teachers",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestParameters(
                        parameterWithName("page").description("요청 페이지"),
                        parameterWithName("size").description("요청 페이지당 출력개수")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.[].teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.[].introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.[].tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),
                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지정보 - 현재 페이지"),
                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지정보 - 페이지당 출력 갯수"),
                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 질문글수"),
                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 페이지수")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강사 태그별 조회")
    void getTeachersByTag() throws Exception {

        when(teacherMapper.teacherInfoResponsesToTeachers(any())).thenReturn(TEACHER_SIMPLE_INFO_RESPONS);

        ResultActions actions =
                mockMvc.perform(
                        get("/teachers/")
                                .param("tag", "국어")
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-teachers-tags",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestParameters(
                        parameterWithName("tag").description("강사 키워드 태그명"),
                        parameterWithName("page").description("요청 페이지"),
                        parameterWithName("size").description("요청 페이지당 출력개수")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.[].teacherId").type(JsonFieldType.NUMBER).description("강사 식별번호"),
                                fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.[].introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.[].tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),
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
        when(teacherMapper.teacherInfoResponseToTeacher(any())).thenReturn(TEACHER_SIMPLE_INFO_RESPONSE);

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
                                fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그")
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