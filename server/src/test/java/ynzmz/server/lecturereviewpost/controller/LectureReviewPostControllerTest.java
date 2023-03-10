package ynzmz.server.lecturereviewpost.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import ynzmz.server.lecture.service.LectureService;
import ynzmz.server.lecturereviewpost.dto.LectureReviewPostDto;
import ynzmz.server.lecturereviewpost.mapper.LectureReviewPostMapper;
import ynzmz.server.lecturereviewpost.sevice.LectureReviewPostService;
import ynzmz.server.helper.StubData;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = LectureReviewPostController.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
class LectureReviewPostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private LectureReviewPostService lectureReviewPostService;
    @MockBean
    private LectureService lectureService;
    @MockBean
    private LectureReviewPostMapper lectureReviewPostMapper;
    @BeforeAll
    public static void init() {
        StubData.init();
    }

    @Test
    @DisplayName("강의 리뷰 등록 테스트")
    void postReviewPost() {

        LectureReviewPostDto.Post mockPost = new LectureReviewPostDto.Post("이 강의 추천합니다!",
                4.5, "꼼꼼하게 설명해주시는게 좋네요",
                1L,
                1L,
                "2023.03.10.18:52:36");
        String jsonPost = gson.toJson(mockPost);

        when(lectureReviewPostMapper.lectureReviewPostInfoResponseToLectureReviewPost(any()));

    }

    @Test
    void patchReviewPost() {
    }

    @Test
    void getLectureReviewPosts() {
    }

    @Test
    void getReviewPostDetail() {
    }

    @Test
    void deleteReview() {
    }
}