package ynzmz.server.comment.lecturereviewpost.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ynzmz.server.comment.lecturereviewpost.dto.LectureReviewPostCommentDto;
import ynzmz.server.comment.lecturereviewpost.mapper.LectureReviewPostCommentMapper;
import ynzmz.server.comment.lecturereviewpost.service.LectureReviewPostCommentService;
import ynzmz.server.helper.StubData;
import ynzmz.server.member.service.MemberService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ynzmz.server.helper.StubData.lectureReviewPostCommentResponse;
import static ynzmz.server.utils.ApiDocumentUtils.getRequestPreProcessor;
import static ynzmz.server.utils.ApiDocumentUtils.getResponsePreProcessor;

@WebMvcTest(controllers = LectureReviewPostCommentController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
class LectureReviewPostCommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private MemberService memberService;
    @MockBean
    private LectureReviewPostCommentMapper lectureReviewPostCommentMapper;
    @MockBean
    private LectureReviewPostCommentService lectureReviewPostCommentService;

    @BeforeAll
    public static void init() {
        StubData.init();
    }
    @Test
    @DisplayName("리뷰글 댓글 등록 테스트")
    void createLectureReviewPostComment() throws Exception {
        LectureReviewPostCommentDto.Post mockPost = new LectureReviewPostCommentDto.Post("이사람 재대로 들은거 맞음?",
                "2023.03.10.18:52:36",1L,1L);
        String jsonPost = gson.toJson(mockPost);

        when(lectureReviewPostCommentMapper.lectureReviewPostCommentResponseToLectureReviewPostComment(any())).thenReturn(lectureReviewPostCommentResponse);

        ResultActions actions = mockMvc.perform(post("/lecture-review-post-comment")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPost)
        );
        actions.andExpect(status().isCreated()).andDo(document("post-lecture-review-post-comment",
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
    void updateLectureReviewPostComment() {
    }

    @Test
    void getLectureReviewPostComments() {
    }

    @Test
    void deleteLectureReviewPostComment() {
    }
}