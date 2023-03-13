package ynzmz.server.vote.lecturereview.controller;

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
import ynzmz.server.helper.StubData;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.lecturereview.sevice.LectureReviewService;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.vote.lecturereview.mapper.LectureReviewVoteMapper;
import ynzmz.server.vote.lecturereview.service.LectureReviewVoteService;

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

@WebMvcTest(controllers = LectureReviewVoteController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
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
    private LectureReviewVoteMapper lectureReviewVoteMapper;
    @MockBean
    private MemberService memberService;

    @BeforeAll
    public static void init() {
        StubData.init();
    }
    @Test
    @DisplayName("강의 리뷰글 추천 UP 테스트")
    void questionVoteUpPost() throws Exception {
        long lectureReviewPostId = 1L;
        long memberId = 1L;

        when(lectureReviewService.findLectureReviewById(anyLong())).thenReturn(new LectureReview());
        when(memberService.findMemberById(anyLong())).thenReturn(member);
        when(lectureReviewVoteService.lectureReviewVoteUp(any(),any())).thenReturn(LECTURE_REVIEW_VOTE);
        when(lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(any(),any())).thenReturn(lectureReviewPostVoteResponse);

        ResultActions actions = mockMvc.perform(post("/lecture-review-post-vote/{lecture-review-post-id}/up/{member-id}",
                lectureReviewPostId,
                memberId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("lecture-review-post-vote-up",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-post-id").description("추천대상 강의 리뷰글 식별번호"),
                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
                                fieldWithPath("data.lectureReviewPostId").type(JsonFieldType.NUMBER).description("추천대상 리뷰글 식별번호"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
                                fieldWithPath("data.lectureReviewPostTotalCount").type(JsonFieldType.NUMBER).description("해당 리뷰글 전체 추천수")
                        )
                ))
        );

    }

    @Test
    @DisplayName("강의 리뷰글 추천 DOWN 테스트")
    void questionVoteDownPost() throws Exception {

        long lectureReviewPostId = 1L;
        long memberId = 1L;

        when(lectureReviewService.findLectureReviewById(anyLong())).thenReturn(new LectureReview());
        when(memberService.findMemberById(anyLong())).thenReturn(member);
        when(lectureReviewVoteService.lectureReviewVoteDown(any(),any())).thenReturn(LECTURE_REVIEW_VOTE);
        when(lectureReviewVoteMapper.lectureReviewVoteToLectureReviewResponse(any(),any())).thenReturn(lectureReviewPostVoteResponse);

        ResultActions actions = mockMvc.perform(post("/lecture-review-post-vote/{lecture-review-post-id}/down/{member-id}",
                lectureReviewPostId,
                memberId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(status().isOk()).andDo(document("lecture-review-post-vote-down",
                getRequestPreProcessor(),
                getResponsePreProcessor(),
                pathParameters(
                        parameterWithName("lecture-review-post-id").description("추천대상 강의 리뷰글 식별번호"),
                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
                ),
                responseFields(
                        List.of(
                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
                                fieldWithPath("data.lectureReviewPostId").type(JsonFieldType.NUMBER).description("추천대상 리뷰글 식별번호"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
                                fieldWithPath("data.lectureReviewPostTotalCount").type(JsonFieldType.NUMBER).description("해당 리뷰글 전체 추천수")
                        )
                ))
        );
    }
}