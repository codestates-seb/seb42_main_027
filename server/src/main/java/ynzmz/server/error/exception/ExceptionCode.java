package ynzmz.server.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    QUESTION_EXISTS(404, "Question exists"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    ANSWER_CHANGE_ORDER(403, "Answer exists"),
    NOT_IMPLEMENTATION(501, "No Implementation"),

    NOT_AUTHORIZED(401, "본인만 회원탈퇴 할수있습니다"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    TEACHER_NOT_FOUND(404, "해당 고유번호 강사가 없습니다."),
    LECTURE_REVIEW_NOT_FOUND(404, "해당 고유번호 강의 리뷰글이 없습니다."),
    LECTURE_NOT_FOUND(404, "해당 고유번호 강의가 없습니다."),
    LECTURE_REVIEW_COMMENT_NOT_FOUND(404, "해당 고유번호 강의 리뷰글 댓글이 없습니다.");
    // 권한이없을때

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
