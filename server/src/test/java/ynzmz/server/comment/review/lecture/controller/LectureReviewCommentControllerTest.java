package ynzmz.server.comment.review.lecture.controller;

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
import ynzmz.server.comment.review.lecture.dto.LectureReviewCommentDto;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.comment.review.lecture.mapper.LectureReviewPostCommentMapper;
import ynzmz.server.comment.review.lecture.service.LectureReviewCommentService;
import ynzmz.server.helper.StubData;
import ynzmz.server.member.service.MemberService;
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

@WebMvcTest(controllers = LectureReviewCommentController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@WithMockUser
@Import(SecurityTestConfig.class)
class LectureReviewCommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private MemberService memberService;
    @MockBean
    private LectureReviewPostCommentMapper lectureReviewPostCommentMapper;
    @MockBean
    private LectureReviewCommentService lectureReviewCommentService;

    @BeforeAll
    public static void init() {
        StubData.init();
    }
    @Test
    @DisplayName("리뷰글 댓글 등록 테스트")
    void createLectureReviewPostComment() throws Exception {
        LectureReviewCommentDto.Post mockPost = new LectureReviewCommentDto.Post("이사람 재대로 들은거 맞음?",
                "2023.03.10.18:52:36",1L,1L);
        String jsonPost = gson.toJson(mockPost);

        when(lectureReviewPostCommentMapper.lectureReviewCommentToLectureReviewCommentPost(any())).thenReturn(new LectureReviewComment());
        when(memberService.findMemberById(anyLong())).thenReturn(member);
        when(lectureReviewPostCommentMapper.lectureReviewCommentResponseToLectureReviewComment(any())).thenReturn(lectureReviewPostCommentResponse);

        ResultActions actions = mockMvc.perform(post("/lectures/reviews/comments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPost)
        );
        actions.andExpect(status().isCreated()).andDo(document("post-lecture-review-comment",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestFields(
                        List.of(
                                fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("lectureReviewId").type(JsonFieldType.NUMBER).description("댓글 작성하는 리뷰글 식별번호"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("댓글 작성 회원 식별번호")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureReviewCommentId").type(JsonFieldType.NUMBER).description("댓글 식별번호"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.voteCount").type(JsonFieldType.NUMBER).description("댓글 추천수"),

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
    @DisplayName("리뷰글 댓글 수정 테스트")
    void updateLectureReviewPostComment() throws Exception {
        LectureReviewCommentDto.Patch mockPatch = new LectureReviewCommentDto.Patch("이사람 재대로 들은거 맞음?",
                "2023.03.10.18:52:36");
        String jsonPatch = gson.toJson(mockPatch);
        long lectureReviewCommentId = 1L;

        when(lectureReviewPostCommentMapper.lectureReviewCommentToLectureReviewCommentPatch(any())).thenReturn(new LectureReviewComment());
        when(lectureReviewPostCommentMapper.lectureReviewCommentResponseToLectureReviewComment(any())).thenReturn(lectureReviewPostCommentResponse);

        ResultActions actions = mockMvc.perform(patch("/lectures/reviews/comments/{lecture-review-comment-id}",lectureReviewCommentId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPatch)
        );
        actions.andExpect(status().isOk()).andDo(document("patch-lecture-review-comment",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-comment-id").description("리뷰 댓글 식별번호")
                ),
                requestFields(
                        List.of(
                                fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정시간 [서버는 string 으로 관리]")
                        )
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.lectureReviewCommentId").type(JsonFieldType.NUMBER).description("댓글 식별번호"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정시간 [서버는 string 으로 관리]"),
                                fieldWithPath("data.voteCount").type(JsonFieldType.NUMBER).description("댓글 추천수"),

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
    @DisplayName("강의별 리뷰 댓글 전체조회 테스트")
    void getLectureReviewPostComments() throws Exception {

        when(lectureReviewCommentService.getLectureReviewComments(anyLong(),anyString(),anyInt(),anyInt())).thenReturn(new PageImpl<>(new ArrayList<>(List.of(new LectureReviewComment())), PageRequest.of(1,1),1));
        when(lectureReviewPostCommentMapper.lectureReviewCommentResponsesToLectureReviewComments(any())).thenReturn(lectureReviewPostCommentResponses);

        ResultActions actions =
                mockMvc.perform(
                        get("/lectures/reviews/comments")
                                .param("filter", "lectureReviewCommentId")
                                .param("lectureReviewId", "1")
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );
        actions.andExpect(status().isOk()).andDo(document("get-lecture-review-comments",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                requestParameters(
                        parameterWithName("filter").description("지정된 필터 양식 (정리필요) "),
                        parameterWithName("lectureReviewId").description("조회할 댓글들의 강의 식별번호"),
                        parameterWithName("page").description("요청 페이지"),
                        parameterWithName("size").description("요청 페이지당 출력개수")
                ),
                responseFields(
                        fieldWithPath("data.[].lectureReviewCommentId").type(JsonFieldType.NUMBER).description("댓글 식별번호"),
                        fieldWithPath("data.[].content").type(JsonFieldType.STRING).description("댓글 내용"),
                        fieldWithPath("data.[].createdAt").type(JsonFieldType.STRING).description("작성시간 [서버는 string 으로 관리]"),
                        fieldWithPath("data.[].modifiedAt").type(JsonFieldType.STRING).description("수정시간 [서버는 string 으로 관리]"),
                        fieldWithPath("data.[].voteCount").type(JsonFieldType.NUMBER).description("댓글 추천수"),

                        fieldWithPath("data.[].member.memberId").type(JsonFieldType.NUMBER).description("작성자 회원 식별번호"),
                        fieldWithPath("data.[].member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                        fieldWithPath("data.[].member.displayName").type(JsonFieldType.STRING).description("작성자 이름"),
                        fieldWithPath("data.[].member.password").type(JsonFieldType.STRING).description("작성자 비밀번호(이건 없엘예정)"),
                        fieldWithPath("data.[].member.iconImageUrl").type(JsonFieldType.STRING).description("작성자 아이콘 Url"),
                        fieldWithPath("data.[].member.createdAt").type(JsonFieldType.STRING).description("작성자 가입시간 [서버는 string 으로 관리]"),
                        fieldWithPath("data.[].member.roles").type(JsonFieldType.ARRAY).description("작성자 권한"),
                        fieldWithPath("data.[].member.memberStatus").type(JsonFieldType.STRING).description("작성자 상태값"),

                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지정보 - 현재 페이지"),
                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지정보 - 페이지당 출력 갯수"),
                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 질문글수"),
                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("페이지정보 - 전체 페이지수")
                )
        ));
    }

    @Test
    @DisplayName("리뷰 댓글 삭제 테스트")
    void deleteLectureReviewPostComment() throws Exception {
        long lectureReviewCommentId = 1L;

        ResultActions actions = mockMvc.perform(delete("/lectures/reviews/comments/{lecture-review-comment-id}",lectureReviewCommentId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("delete-lecture-review-comment",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-comment-id").description("리뷰 댓글 식별번호")
                )
        ));
    }
}