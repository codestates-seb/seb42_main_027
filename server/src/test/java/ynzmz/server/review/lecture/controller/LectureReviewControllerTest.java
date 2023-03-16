package ynzmz.server.review.lecture.controller;

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
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.review.lecture.dto.LectureReviewDto;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.review.lecture.mapper.LectureReviewMapper;
import ynzmz.server.review.lecture.sevice.LectureReviewService;
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

@WebMvcTest(controllers = LectureReviewController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@WithMockUser
@Import(SecurityTestConfig.class)
class LectureReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private LectureReviewService lectureReviewService;
    @MockBean
    private LectureService lectureService;
    @MockBean
    private TeacherService teacherService;
    @MockBean
    private LectureReviewMapper lectureReviewMapper;
    @BeforeAll
    public static void init() {
        StubData.init();
    }

    @Test
    @DisplayName("강의 리뷰 등록 테스트")
    void postReviewPost() throws Exception {

        LectureReviewDto.Post mockPost = LectureReviewDto.Post.builder()
                .title("리뷰글 제목")
                .starPoint(5)
                .content("리뷰글 내용")
                .memberId(1L)
                .lectureId(1L)
                .createdAt("2023.03.10.18:52:36")
                .build();
        String jsonPost = gson.toJson(mockPost);

        when(lectureReviewService.createLectureReview(any())).thenReturn(lectureReview);
        when(lectureReviewMapper.lectureReviewInfoResponseToLectureReview(any())).thenReturn(lectureReviewPostInfoResponse);

        ResultActions actions = mockMvc.perform(post("/lectures/reviews")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPost)
        );
        actions.andExpect(status().isCreated()).andDo(document("post-lecture-review",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestFields(
                        List.of(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("리뷰글 작성 회원 식별번호"),
                                fieldWithPath("lectureId").type(JsonFieldType.NUMBER).description("리뷰글 작성하는 강의 식별번호"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureReviewId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("data.starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
                                fieldWithPath("data.voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),

                                fieldWithPath("data.lecture.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.lecture.title").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.lecture.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),

                                fieldWithPath("data.member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
                                fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                                fieldWithPath("data.member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
                                fieldWithPath("data.member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
                                fieldWithPath("data.member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.member.roles").type(JsonFieldType.ARRAY).description("작성자 권한"),
                                fieldWithPath("data.member.memberStatus").type(JsonFieldType.STRING).description("작성자 상태값")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강의 리뷰 수정 테스트")
    void patchReviewPost() throws Exception {

        LectureReviewDto.Patch mockPatch = LectureReviewDto.Patch.builder()
                .title("리뷰글 제목")
                .starPoint(5)
                .content("리뷰글 내용")
                .modifiedAt("2023.03.10.18:52:36")
                .build();

        String jsonPatch = gson.toJson(mockPatch);
        long lectureReviewPostId = 1L;

        when(lectureReviewMapper.lectureReviewToLectureReviewPatch(any())).thenReturn(new LectureReview());
        when(lectureReviewService.updateLectureReview(any())).thenReturn(lectureReview);
        when(lectureReviewMapper.lectureReviewInfoResponseToLectureReview(any())).thenReturn(lectureReviewPostInfoResponse);

        ResultActions actions = mockMvc
                .perform(patch("/lectures/reviews/{lecture-review-id}",lectureReviewPostId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPatch)
        );
        actions.andExpect(status().isOk()).andDo(document("patch-lecture-review",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-id").description("리뷰글 식별번호")
                ),
                requestFields(
                        List.of(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureReviewId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("data.starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
                                fieldWithPath("data.voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),

                                fieldWithPath("data.lecture.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.lecture.title").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.lecture.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),

                                fieldWithPath("data.member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
                                fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                                fieldWithPath("data.member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
                                fieldWithPath("data.member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
                                fieldWithPath("data.member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.member.roles").type(JsonFieldType.ARRAY).description("작성자 권한"),
                                fieldWithPath("data.member.memberStatus").type(JsonFieldType.STRING).description("작성자 상태값")
                        )
                ))
        );
    }

//    @Test
//    @DisplayName("리뷰글 강사별+강의별+전체 조회")
//    void getLectureReviewPostsByTeacher() throws Exception {
//
//        when(lectureReviewService.findLectureReviewsByLecture(anyLong(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new LectureReview())), PageRequest.of(1,1),1));
//        when(lectureReviewService.findLectureReviewsByTeacher(anyLong(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new LectureReview())), PageRequest.of(1,1),1));
//        when(lectureReviewMapper.lectureReviewInfoResponsesToLectureReviews(any())).thenReturn(lectureReviewPostInfoResponses);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        get("/lectures/reviews")
//                                .param("teacher", "1")
//                                .param("lecture", "0")
//                                .param("page", "1")
//                                .param("size", "5")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                );
//        actions.andExpect(status().isOk()).andDo(document("get-lecture-reviews",
//                getRequestPreProcessor(),
//                getResponsePreProcessor(),
//                requestParameters(
//                        parameterWithName("teacher").description("강사 식별번호 (강사별 조회안할때는 0 입력)"),
//                        parameterWithName("lecture").description("강의 식별번호 (강의별 조회안할때는 0 입력)"),
//                        parameterWithName("page").description("요청 페이지"),
//                        parameterWithName("size").description("요청 페이지당 출력개수")
//                ),
//                responseFields(
//                        List.of(
//                                fieldWithPath("data.[].lectureReviewPostId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
//                                fieldWithPath("data.[].title").type(JsonFieldType.STRING).description("리뷰글 제목"),
//                                fieldWithPath("data.[].starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
//                                fieldWithPath("data.[].content").type(JsonFieldType.STRING).description("리뷰글 내용"),
//                                fieldWithPath("data.[].createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
//                                fieldWithPath("data.[].modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
//                                fieldWithPath("data.[].viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
//                                fieldWithPath("data.[].voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),
//
//                                fieldWithPath("data.[].lecture.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
//                                fieldWithPath("data.[].lecture.name").type(JsonFieldType.STRING).description("강의 이름"),
//                                fieldWithPath("data.[].lecture.introduction").type(JsonFieldType.STRING).description("강의 소개"),
//                                fieldWithPath("data.[].lecture.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),
//                                fieldWithPath("data.[].lecture.tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
//                                fieldWithPath("data.[].lecture.teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
//                                fieldWithPath("data.[].lecture.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
//                                fieldWithPath("data.[].lecture.teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
//                                fieldWithPath("data.[].lecture.teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),
//
//                                fieldWithPath("data.[].member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
//                                fieldWithPath("data.[].member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
//                                fieldWithPath("data.[].member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
//                                fieldWithPath("data.[].member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
//                                fieldWithPath("data.[].member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
//                                fieldWithPath("data.[].member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]"),
//                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지정보 - 현재 페이지"),
//                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지정보 - 페이지당 출력 갯수"),
//                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 질문글수"),
//                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 페이지수")
//                        )
//                ))
//        );
//
//    }

    @Test
    @DisplayName("리뷰글 상세 조회 테스트")
    void getReviewPostDetail() throws Exception {
        long lectureReviewPostId = 1L;

        when(lectureReviewService.findLectureReviewById(anyInt())).thenReturn(new LectureReview());
        when(lectureReviewMapper.lectureReviewDetailPageResponseToLectureReview(any())).thenReturn(lectureReviewDetailPageResponse);

        ResultActions actions = mockMvc.perform(get("/lectures/reviews/{lecture-review-id}",lectureReviewPostId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("get-lecture-review",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-id").description("리뷰 식별번호")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureReviewId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("data.starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
                                fieldWithPath("data.voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),

                                fieldWithPath("data.lecture.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.lecture.title").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.lecture.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),

                                fieldWithPath("data.teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
                                fieldWithPath("data.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.teacher.starPointAverage").type(JsonFieldType.NUMBER).description("강사 평균 평점"),

                                fieldWithPath("data.member.memberId").type(JsonFieldType.NUMBER).description("리뷰 작성자 회원 식별번호"),
                                fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("리뷰 작성자 이메일"),
                                fieldWithPath("data.member.displayName").type(JsonFieldType.STRING).description("리뷰 작성자 이름"),
                                fieldWithPath("data.member.password").type(JsonFieldType.STRING).description("리뷰 작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.member.iconImageUrl").type(JsonFieldType.STRING).description("리뷰 작성자 아이콘 Url"),
                                fieldWithPath("data.member.createdAt").type(JsonFieldType.STRING).description("리뷰 작성자 가입시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.member.roles").type(JsonFieldType.ARRAY).description("리뷰 작성자 권한"),
                                fieldWithPath("data.member.memberStatus").type(JsonFieldType.STRING).description("리뷰 작성자 상태값"),

                                fieldWithPath("data.comments.[].lectureReviewCommentId").type(JsonFieldType.NUMBER).description("댓글 식별번호"),
                                fieldWithPath("data.comments.[].content").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("data.comments.[].createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.comments.[].modifiedAt").type(JsonFieldType.STRING).description("수정시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.comments.[].voteCount").type(JsonFieldType.NUMBER).description("댓글 추천수"),

                                fieldWithPath("data.comments.[].member.memberId").type(JsonFieldType.NUMBER).description("댓글 작성자 회원 식별번호"),
                                fieldWithPath("data.comments.[].member.email").type(JsonFieldType.STRING).description("댓글 작성자 이메일"),
                                fieldWithPath("data.comments.[].member.displayName").type(JsonFieldType.STRING).description("댓글 작성자 이름"),
                                fieldWithPath("data.comments.[].member.password").type(JsonFieldType.STRING).description("댓글 작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.comments.[].member.iconImageUrl").type(JsonFieldType.STRING).description("댓글 작성자 아이콘 Url"),
                                fieldWithPath("data.comments.[].member.createdAt").type(JsonFieldType.STRING).description("댓글 작성자 가입시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.comments.[].member.roles").type(JsonFieldType.ARRAY).description("댓글 작성자 권한"),
                                fieldWithPath("data.comments.[].member.memberStatus").type(JsonFieldType.STRING).description("댓글 작성자 상태값")
                        )
                ))
        );
    }

    @Test
    @DisplayName("리뷰글 삭제 테스트")
    void deleteReview() throws Exception {
        long lectureReviewPostId = 1L;

        ResultActions actions = mockMvc.perform(delete("/lectures/reviews/{lecture-review-id}",lectureReviewPostId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("delete-lecture-review",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-id").description("리뷰글 식별번호")
                )
        ));
    }
}