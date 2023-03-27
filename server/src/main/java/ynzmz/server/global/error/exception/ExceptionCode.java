package ynzmz.server.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    TEACHER_EXISTS(409, "Teacher exists"),
    TEACHER_NOT_FOUND(404, "TEACHER_NOT_FOUND"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    QUESTION_EXISTS(409, "Question exists"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    ANSWER_CHANGE_ORDER(403, "Answer exists"),
    POST_NOT_FOUND(404, "Post not found"),
    POST_EXISTS(404, "Post not found"),
    DISPLAY_NAME_EXISTS(409, "DisplayName exists"),
    INVALID_NOW_PASSWORD(404, "Password is not same"),
    PASSWORD_NOT_MATCHED(404,"Password not matched"),
    SAME_PASSWORD(404, "Same password"),
    UNAUTHORIZED(404, "No authorization"),

    //추후 추가 가능 --> 기능 봐서

    NOT_IMPLEMENTATION(501, "No Implementation"),

    NOT_AUTHORIZED(401, "본인만 회원탈퇴 할수있습니다"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    THIS_MEMBER_NOT_PERMISSION(403, "권한없음 본인의 게시글만 수정할수있습니다."),
    LECTURE_REVIEW_NOT_FOUND(404, "해당 고유번호 강의 리뷰글이 없습니다."),
    LECTURE_NOT_FOUND(404, "해당 고유번호 강의가 없습니다."),
    LECTURE_REVIEW_COMMENT_NOT_FOUND(404, "해당 고유번호 강의 리뷰글 댓글이 없습니다."),
    EVENT_NOT_FOUND(404,"해당 아이디 관련 이벤트가 없습니다"),
    EVENT_NOT_YZ(600,"야놀지말자 이벤트가 아니라 수정할 수 없습니다"),
    FREE_REVIEW_COMMENT_NOT_FOUND(404, "자유게시글 아이디 리뷰글 댓글이 없습니다."),
    FREE_NOT_FOUND(404,"자유게시글이 존재하지 않습니다"),
    QNA_RE_COMMENT_NOT_FOUND(404,"해당 질문답변게시판 대댓글이 존재하지 않습니다 "),
    QNA_COMMENT_NOT_FOUND(404,"해당 질문답변게시판 댓글이 존재하지 않습니다 ");

    // 권한이없을때

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
