package ynzmz.server.vote.review.lecture.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.comment.review.lecture.service.LectureReviewCommentService;
import ynzmz.server.helper.StubData;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.review.lecture.sevice.LectureReviewService;
import ynzmz.server.utils.SecurityTestConfig;
import ynzmz.server.vote.review.lecture.mapper.LectureReviewVoteMapper;
import ynzmz.server.vote.review.lecture.service.LectureReviewVoteService;

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

@WebMvcTest(controllers = LectureReviewVoteController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@WithMockUser
@Import(SecurityTestConfig.class)
class LectureReviewVoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private LectureReviewVoteService lectureReviewVoteService;
    @MockBean
    private LectureReviewService lectureReviewService;
    @MockBean
    private LectureReviewCommentService lectureReviewCommentService;
    @MockBean
    private LectureReviewVoteMapper lectureReviewVoteMapper;
    @MockBean
    private  MemberService memberService;

    @BeforeAll
    public static void init() {
        StubData.init();
    }
    @Test
    @DisplayName("강의 리뷰글 추천 UP 테스트")
    void lectureReviewVoteUpPost() throws Exception {
        long lectureReviewPostId = 1L;
        long memberId = 1L;

        when(lectureReviewService.findLectureReviewById(anyLong())).thenReturn(lectureReview);
        when(memberService.findMemberById(anyLong())).thenReturn(member);
        when(lectureReviewVoteService.lectureReviewVoteUp(any(),any())).thenReturn(lectureReviewVote);
        when(lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(any())).thenReturn(lectureReviewVoteResponse);

        ResultActions actions = mockMvc.perform(post("/vote/lecture/review/{lecture-review-id}/up/{member-id}",
                lectureReviewPostId,
                memberId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("lecture-review-vote-up",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-id").description("추천대상 강의 리뷰글 식별번호"),
                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
                                fieldWithPath("data.target").type(JsonFieldType.STRING).description("리뷰글인지 댓글인지 (REVIEW,COMMENT)"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
                                fieldWithPath("data.lectureReviewId").type(JsonFieldType.NUMBER).description("추천대상 리뷰글 식별번호"),
                                fieldWithPath("data.lectureReviewTotalCount").type(JsonFieldType.NUMBER).description("추천대상 리뷰글 전체 추천수"),
                                fieldWithPath("data.lectureReviewCommentId").type(JsonFieldType.NUMBER).description("리뷰글 추천시 필요없는값 (0 으로감)"),
                                fieldWithPath("data.lectureReviewCommentTotalCount").type(JsonFieldType.NUMBER).description("리뷰글 추천시 필요없는값 (0 으로감)")
                        )
                ))
        );

    }

    @Test
    @DisplayName("강의 리뷰글 추천 DOWN 테스트")
    void lectureReviewVoteDownPost() throws Exception {

        long lectureReviewPostId = 1L;
        long memberId = 1L;

        when(lectureReviewService.findLectureReviewById(anyLong())).thenReturn(new LectureReview());
        when(memberService.findMemberById(anyLong())).thenReturn(member);
        when(lectureReviewVoteService.lectureReviewVoteDown(any(),any())).thenReturn(lectureReviewVote);
        when(lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(any())).thenReturn(lectureReviewVoteResponse);

        ResultActions actions = mockMvc.perform(post("/vote/lecture/review/{lecture-review-id}/down/{member-id}",
                lectureReviewPostId,
                memberId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("lecture-review-vote-down",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-id").description("추천대상 강의 리뷰글 식별번호"),
                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
                                fieldWithPath("data.target").type(JsonFieldType.STRING).description("리뷰글인지 댓글인지 (REVIEW,COMMENT)"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
                                fieldWithPath("data.lectureReviewId").type(JsonFieldType.NUMBER).description("추천대상 리뷰글 식별번호"),
                                fieldWithPath("data.lectureReviewTotalCount").type(JsonFieldType.NUMBER).description("추천대상 리뷰글 전체 추천수"),
                                fieldWithPath("data.lectureReviewCommentId").type(JsonFieldType.NUMBER).description("리뷰글 추천시 필요없는값 (0 으로감)"),
                                fieldWithPath("data.lectureReviewCommentTotalCount").type(JsonFieldType.NUMBER).description("리뷰글 추천시 필요없는값 (0 으로감)")
                        )
                ))
        );
    }

    @Test
    @DisplayName("리뷰 댓글 추천 UP 테스트")
    void lectureReviewCommentVoteUpPost() throws Exception {
        long lectureReviewPostCommentId = 1L;
        long memberId = 1L;

        when(lectureReviewCommentService.findLectureReviewCommentById(anyLong())).thenReturn(new LectureReviewComment());
        when(memberService.findMemberById(anyLong())).thenReturn(member);
        when(lectureReviewVoteService.lectureReviewVoteDown(any(),any())).thenReturn(lectureReviewVote);
        when(lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(any())).thenReturn(lectureReviewCommentVoteResponse);

        ResultActions actions = mockMvc.perform(post("/vote/lecture/review/comment/{lecture-review-comment-id}/up/{member-id}",
                lectureReviewPostCommentId,
                memberId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("lecture-review-comment-vote-up",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-comment-id").description("추천대상 리뷰 댓글 식별번호"),
                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
                                fieldWithPath("data.target").type(JsonFieldType.STRING).description("리뷰글인지 댓글인지 (REVIEW,COMMENT)"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
                                fieldWithPath("data.lectureReviewId").type(JsonFieldType.NUMBER).description("댓글 추천시 필요없는값 (0 으로감)"),
                                fieldWithPath("data.lectureReviewTotalCount").type(JsonFieldType.NUMBER).description("댓글 추천시 필요없는값 (0 으로감)"),
                                fieldWithPath("data.lectureReviewCommentId").type(JsonFieldType.NUMBER).description("추천대상 댓글 식별번호"),
                                fieldWithPath("data.lectureReviewCommentTotalCount").type(JsonFieldType.NUMBER).description("추천대상 댓글 전체 추천수")
                        )
                ))
        );
    }

    @Test
    @DisplayName("리뷰 댓글 추천 DOWN 테스트")
    void lectureReviewCommentVoteDownPost() throws Exception {
        long lectureReviewPostCommentId = 1L;
        long memberId = 1L;

        when(lectureReviewCommentService.findLectureReviewCommentById(anyLong())).thenReturn(new LectureReviewComment());
        when(memberService.findMemberById(anyLong())).thenReturn(member);
        when(lectureReviewVoteService.lectureReviewVoteDown(any(),any())).thenReturn(lectureReviewVote);
        when(lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(any())).thenReturn(lectureReviewCommentVoteResponse);

        ResultActions actions = mockMvc.perform(post("/vote/lecture/review/comment/{lecture-review-comment-id}/down/{member-id}",
                lectureReviewPostCommentId,
                memberId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("lecture-review-comment-vote-down",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-comment-id").description("추천대상 리뷰 댓글 식별번호"),
                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
                                fieldWithPath("data.target").type(JsonFieldType.STRING).description("리뷰글인지 댓글인지 (REVIEW,COMMENT)"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
                                fieldWithPath("data.lectureReviewId").type(JsonFieldType.NUMBER).description("댓글 추천시 필요없는값 (0 으로감)"),
                                fieldWithPath("data.lectureReviewTotalCount").type(JsonFieldType.NUMBER).description("댓글 추천시 필요없는값 (0 으로감)"),
                                fieldWithPath("data.lectureReviewCommentId").type(JsonFieldType.NUMBER).description("추천대상 댓글 식별번호"),
                                fieldWithPath("data.lectureReviewCommentTotalCount").type(JsonFieldType.NUMBER).description("추천대상 댓글 전체 추천수")
                        )
                ))
        );
    }
}