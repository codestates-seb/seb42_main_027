package ynzmz.server.vote.review.lecture.controller;

import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(controllers = LectureReviewVoteController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
class LectureReviewCommentVoteControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private Gson gson;
//    @MockBean
//    private LectureReviewCommentVoteService lectureReviewCommentVoteService;
//    @MockBean
//    private LectureReviewCommentService lectureReviewCommentService;
//    @MockBean
//    private LectureReviewCommentVoteMapper lectureReviewCommentVoteMapper;
//    @MockBean
//    private MemberService memberService;
//
//    @BeforeAll
//    public static void init() {
//        StubData.init();
//    }
//
//    @Test
//    @DisplayName("리뷰 댓글 추천 UP 테스트")
//    void questionVoteUpPost() throws Exception {
//        long lectureReviewPostCommentId = 1L;
//        long memberId = 1L;
//
//        when(lectureReviewCommentService.findLectureReviewCommentById(anyLong())).thenReturn(new LectureReviewComment());
//        when(memberService.findMemberById(anyLong())).thenReturn(member);
//        when(lectureReviewCommentVoteService.lectureReviewCommentVoteUp(any(),any())).thenReturn(LECTURE_REVIEW_COMMENT_VOTE);
//        when(lectureReviewCommentVoteMapper.lectureReviewCommentVoteToLectureReviewCommentVoteResponse(any(),any())).thenReturn(lectureReviewPostCommentVoteResponse);
//
//        ResultActions actions = mockMvc.perform(post("/lecture-review-post-comment-vote/{lecture-review-post-comment-id}/up/{member-id}",
//                lectureReviewPostCommentId,
//                memberId)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//        );
//        actions.andExpect(status().isOk()).andDo(document("lecture-review-post-comment-vote-up",
//                getRequestPreProcessor(),
//                getResponsePreProcessor(),
//                pathParameters(
//                        parameterWithName("lecture-review-post-comment-id").description("추천대상 리뷰 댓글 식별번호"),
//                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
//                ),
//                responseFields(
//                        List.of(
//                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
//                                fieldWithPath("data.lectureReviewPostCommentId").type(JsonFieldType.NUMBER).description("추천대상 리뷰댓글 식별번호"),
//                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
//                                fieldWithPath("data.lectureReviewPostCommentTotalCount").type(JsonFieldType.NUMBER).description("해당 리뷰댓글 전체 추천수")
//                        )
//                ))
//        );
//    }
//
//    @Test
//    @DisplayName("리뷰 댓글 추천 DOWN 테스트")
//    void questionVoteDownPost() throws Exception {
//        long lectureReviewPostCommentId = 1L;
//        long memberId = 1L;
//
//        when(lectureReviewCommentService.findLectureReviewCommentById(anyLong())).thenReturn(new LectureReviewComment());
//        when(memberService.findMemberById(anyLong())).thenReturn(member);
//        when(lectureReviewCommentVoteService.lectureReviewCommentVoteDown(any(),any())).thenReturn(LECTURE_REVIEW_COMMENT_VOTE);
//        when(lectureReviewCommentVoteMapper.lectureReviewCommentVoteToLectureReviewCommentVoteResponse(any(),any())).thenReturn(lectureReviewPostCommentVoteResponse);
//
//        ResultActions actions = mockMvc.perform(post("/lecture-review-post-comment-vote/{lecture-review-post-comment-id}/down/{member-id}",
//                lectureReviewPostCommentId,
//                memberId)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//        );
//        actions.andExpect(status().isOk()).andDo(document("lecture-review-post-comment-vote-down",
//                getRequestPreProcessor(),
//                getResponsePreProcessor(),
//                pathParameters(
//                        parameterWithName("lecture-review-post-comment-id").description("추천대상 리뷰 댓글 식별번호"),
//                        parameterWithName("member-id").description("추천 누르는 회원 식별번호 ( 로그인기능 합치면 필요없을수도있음 )")
//                ),
//                responseFields(
//                        List.of(
//                                fieldWithPath("data.voteStatus").type(JsonFieldType.STRING).description("내 현재 추천 상태값"),
//                                fieldWithPath("data.lectureReviewPostCommentId").type(JsonFieldType.NUMBER).description("추천대상 리뷰댓글 식별번호"),
//                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("내 회원 식별번호 (확인용)"),
//                                fieldWithPath("data.lectureReviewPostCommentTotalCount").type(JsonFieldType.NUMBER).description("해당 리뷰댓글 전체 추천수")
//                        )
//                ))
//        );
//    }
}