package ynzmz.server.lecturereview.controller;

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
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.lecturereview.dto.LectureReviewDto;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.lecturereview.mapper.LectureReviewMapper;
import ynzmz.server.lecturereview.sevice.LectureReviewService;

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
    private LectureReviewMapper lectureReviewMapper;
    @BeforeAll
    public static void init() {
        StubData.init();
    }

    @Test
    @DisplayName("강의 리뷰 등록 테스트")
    void postReviewPost() throws Exception {

        LectureReviewDto.Post mockPost = new LectureReviewDto.Post("이 강의 추천합니다!",
                4.5, "꼼꼼하게 설명해주시는게 좋네요",
                1L,
                1L,
                "2023.03.10.18:52:36");
        String jsonPost = gson.toJson(mockPost);

        when(lectureReviewService.setLectureReviewAverageStarPoint(any())).thenReturn(4.5);
        when(lectureReviewService.createLectureReview(any())).thenReturn(LECTURE_REVIEW);
        when(lectureReviewMapper.lectureReviewInfoResponseToLectureReview(any())).thenReturn(lectureReviewPostInfoResponse);

        ResultActions actions = mockMvc.perform(post("/lecture-review-posts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPost)
        );
        actions.andExpect(status().isCreated()).andDo(document("post-lecture-review-posts",
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
                                fieldWithPath("data.lectureReviewPostId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("data.starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
                                fieldWithPath("data.voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),

                                fieldWithPath("data.lecture.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.lecture.name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.lecture.introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.lecture.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),
                                fieldWithPath("data.lecture.tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.lecture.teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
                                fieldWithPath("data.lecture.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.lecture.teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.lecture.teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),

                                fieldWithPath("data.member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
                                fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                                fieldWithPath("data.member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
                                fieldWithPath("data.member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
                                fieldWithPath("data.member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]")
                        )
                ))
        );
    }

    @Test
    @DisplayName("강의 리뷰 수정 테스트")
    void patchReviewPost() throws Exception {

        LectureReviewDto.Patch mockPatch = new LectureReviewDto.Patch("이 강의 추천합니다!",
                4.5,
                "꼼꼼하게 설명해주시는게 좋네요",
                "2023.03.10.18:52:36");
        String jsonPatch = gson.toJson(mockPatch);
        long lectureReviewPostId = 1L;

        when(lectureReviewMapper.lectureReviewToLectureReviewPatch(any())).thenReturn(new LectureReview());
        when(lectureReviewService.updateLectureReview(any())).thenReturn(LECTURE_REVIEW);
        when(lectureReviewService.setLectureReviewAverageStarPoint(any())).thenReturn(4.5);
        when(lectureReviewMapper.lectureReviewInfoResponseToLectureReview(any())).thenReturn(lectureReviewPostInfoResponse);

        ResultActions actions = mockMvc
                .perform(patch("/lecture-review-posts/{lecture-review-post-id}",lectureReviewPostId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPatch)
        );
        actions.andExpect(status().isOk()).andDo(document("patch-lecture-review-posts",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-post-id").description("리뷰글 식별번호")
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
                                fieldWithPath("data.lectureReviewPostId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("data.starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
                                fieldWithPath("data.voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),

                                fieldWithPath("data.lecture.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.lecture.name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.lecture.introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.lecture.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),
                                fieldWithPath("data.lecture.tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.lecture.teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
                                fieldWithPath("data.lecture.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.lecture.teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.lecture.teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),

                                fieldWithPath("data.member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
                                fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                                fieldWithPath("data.member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
                                fieldWithPath("data.member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
                                fieldWithPath("data.member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]")
                        )
                ))
        );
    }

    @Test
    @DisplayName("리뷰글 강사별+강의별+전체 조회")
    void getLectureReviewPostsByTeacher() throws Exception {

        when(lectureReviewService.findLectureReviewsByLecture(anyLong(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new LectureReview())), PageRequest.of(1,1),1));
        when(lectureReviewService.findLectureReviewsByTeacher(anyLong(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new LectureReview())), PageRequest.of(1,1),1));
        when(lectureReviewMapper.lectureReviewInfoResponsesToLectureReviews(any())).thenReturn(lectureReviewPostInfoResponses);

        ResultActions actions =
                mockMvc.perform(
                        get("/lecture-review-posts")
                                .param("teacher", "1")
                                .param("lecture", "0")
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-lecture-review-posts",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestParameters(
                        parameterWithName("teacher").description("강사 식별번호 (강사별 조회안할때는 0 입력)"),
                        parameterWithName("lecture").description("강의 식별번호 (강의별 조회안할때는 0 입력)"),
                        parameterWithName("page").description("요청 페이지"),
                        parameterWithName("size").description("요청 페이지당 출력개수")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.[].lectureReviewPostId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
                                fieldWithPath("data.[].title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("data.[].starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("data.[].content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("data.[].createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.[].modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.[].viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
                                fieldWithPath("data.[].voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),

                                fieldWithPath("data.[].lecture.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.[].lecture.name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.[].lecture.introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.[].lecture.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),
                                fieldWithPath("data.[].lecture.tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.[].lecture.teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
                                fieldWithPath("data.[].lecture.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.[].lecture.teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.[].lecture.teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),

                                fieldWithPath("data.[].member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
                                fieldWithPath("data.[].member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                                fieldWithPath("data.[].member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
                                fieldWithPath("data.[].member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.[].member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
                                fieldWithPath("data.[].member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]"),
                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지정보 - 현재 페이지"),
                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지정보 - 페이지당 출력 갯수"),
                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 질문글수"),
                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 페이지수")
                        )
                ))
        );

    }

    @Test
    @DisplayName("리뷰글 상세 조회 테스트")
    void getReviewPostDetail() throws Exception {
        long lectureReviewPostId = 1L;

        when(lectureReviewService.findLectureReviewById(anyInt())).thenReturn(new LectureReview());
        when(lectureReviewMapper.lectureReviewInfoResponseToLectureReview(any())).thenReturn(lectureReviewPostInfoResponse);

        ResultActions actions = mockMvc.perform(get("/lecture-review-posts/{lecture-review-post-id}",lectureReviewPostId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("get-lecture-review-post",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-post-id").description("리뷰 식별번호")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureReviewPostId").type(JsonFieldType.NUMBER).description("리뷰글 식별번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("리뷰글 제목"),
                                fieldWithPath("data.starPoint").type(JsonFieldType.NUMBER).description("리뷰글 평점"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("리뷰글 내용"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정된시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.viewCount").type(JsonFieldType.NUMBER).description("리뷰글 조회수"),
                                fieldWithPath("data.voteCount").type(JsonFieldType.NUMBER).description("리뷰글 추천수"),

                                fieldWithPath("data.lecture.lectureId").type(JsonFieldType.NUMBER).description("강의 식별번호"),
                                fieldWithPath("data.lecture.name").type(JsonFieldType.STRING).description("강의 이름"),
                                fieldWithPath("data.lecture.introduction").type(JsonFieldType.STRING).description("강의 소개"),
                                fieldWithPath("data.lecture.starPointAverage").type(JsonFieldType.NUMBER).description("강의 평균평점"),
                                fieldWithPath("data.lecture.tags").type(JsonFieldType.ARRAY).description("강의 키워드 태그"),
                                fieldWithPath("data.lecture.teacher.teacherId").type(JsonFieldType.NUMBER).description("강의의 강사 식별번호"),
                                fieldWithPath("data.lecture.teacher.name").type(JsonFieldType.STRING).description("강사 이름"),
                                fieldWithPath("data.lecture.teacher.introduction").type(JsonFieldType.STRING).description("강사 소개"),
                                fieldWithPath("data.lecture.teacher.tags").type(JsonFieldType.ARRAY).description("강사 키워드 태그"),

                                fieldWithPath("data.member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
                                fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                                fieldWithPath("data.member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
                                fieldWithPath("data.member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
                                fieldWithPath("data.member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
                                fieldWithPath("data.member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]")
                        )
                ))
        );
    }

    @Test
    @DisplayName("리뷰글 삭제 테스트")
    void deleteReview() throws Exception {
        long lectureReviewPostId = 1L;

        ResultActions actions = mockMvc.perform(delete("/lecture-review-posts/{lecture-review-post-id}",lectureReviewPostId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("delete-lecture-review-post",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-post-id").description("리뷰글 식별번호")
                )
        ));
    }
}