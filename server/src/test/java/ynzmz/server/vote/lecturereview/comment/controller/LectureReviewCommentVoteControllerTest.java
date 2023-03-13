package ynzmz.server.vote.lecturereview.comment.controller;

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
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.comment.lecturereview.service.LectureReviewCommentService;
import ynzmz.server.helper.StubData;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.vote.lecturereview.comment.mapper.LectureReviewCommentVoteMapper;
import ynzmz.server.vote.lecturereview.comment.service.LectureReviewCommentVoteService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ynzmz.server.helper.StubData.*;
import static ynzmz.server.utils.ApiDocumentUtils.getRequestPreProcessor;
import static ynzmz.server.utils.ApiDocumentUtils.getResponsePreProcessor;

@WebMvcTest(controllers = LectureReviewCommentVoteController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
class LectureReviewCommentVoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private LectureReviewCommentVoteService lectureReviewCommentVoteService;
    @MockBean
    private LectureReviewCommentService lectureReviewCommentService;
    @MockBean
    private LectureReviewCommentVoteMapper lectureReviewCommentVoteMapper;
    @MockBean
    private MemberService memberService;

    @BeforeAll
    public static void init() {
        StubData.init();
    }

    @Test
    @DisplayName("리뷰 댓글 추천 UP 테스트")
    void questionVoteUpPost() throws Exception {
        long lectureReviewPostCommentId = 1L;
        long memberId = 1L;

        when(lectureReviewCommentService.findLectureReviewCommentById(anyLong())).thenReturn(new LectureReviewComment());
        when(memberService.findMemberById(anyLong())).thenReturn(member);
        when(lectureReviewCommentVoteService.lectureReviewCommentVoteUp(any(),any())).thenReturn(LECTURE_REVIEW_COMMENT_VOTE);
        when(lectureReviewCommentVoteMapper.lectureReviewCommentVoteToLectureReviewCommentVoteResponse(any(),any())).thenReturn(lectureReviewPostCommentVoteResponse);

        ResultActions actions = mockMvc.perform(post("/lecture-review-post-comment-vote/{lecture-review-post-comment-id}/up/{member-id}",
                lectureReviewPostCommentId,
                memberId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("lecture-review-post-comment-vote-up",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-post-comment-id").description("추천대상 리뷰 댓글 식별번호"),
                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
                                fieldWithPath("data.lectureReviewPostCommentId").type(JsonFieldType.NUMBER).description("추천대상 리뷰댓글 식별번호"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
                                fieldWithPath("data.lectureReviewPostCommentTotalCount").type(JsonFieldType.NUMBER).description("해당 리뷰댓글 전체 추천수")
                        )
                ))
        );
    }

    @Test
    @DisplayName("리뷰 댓글 추천 DOWN 테스트")
    void questionVoteDownPost() throws Exception {
        long lectureReviewPostCommentId = 1L;
        long memberId = 1L;

        when(lectureReviewCommentService.findLectureReviewCommentById(anyLong())).thenReturn(new LectureReviewComment());
        when(memberService.findMemberById(anyLong())).thenReturn(member);
        when(lectureReviewCommentVoteService.lectureReviewCommentVoteDown(any(),any())).thenReturn(LECTURE_REVIEW_COMMENT_VOTE);
        when(lectureReviewCommentVoteMapper.lectureReviewCommentVoteToLectureReviewCommentVoteResponse(any(),any())).thenReturn(lectureReviewPostCommentVoteResponse);

        ResultActions actions = mockMvc.perform(post("/lecture-review-post-comment-vote/{lecture-review-post-comment-id}/down/{member-id}",
                lectureReviewPostCommentId,
                memberId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("lecture-review-post-comment-vote-down",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-post-comment-id").description("추천대상 리뷰 댓글 식별번호"),
                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
                                fieldWithPath("data.lectureReviewPostCommentId").type(JsonFieldType.NUMBER).description("추천대상 리뷰댓글 식별번호"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
                                fieldWithPath("data.lectureReviewPostCommentTotalCount").type(JsonFieldType.NUMBER).description("해당 리뷰댓글 전체 추천수")
                        )
                ))
        );
    }
}