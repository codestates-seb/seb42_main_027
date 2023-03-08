package ynzmz.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),

    TEACHER_NOT_FOUND(404, "Teacher not found"),
    TEACHER_EXISTS(409, "Teacher exists"),

    QUESTION_NOT_FOUND(404, "Question not found"),
    QUESTION_EXISTS(409, "Question exists"),

    ANSWER_NOT_FOUND(404, "Answer not found"),
    ANSWER_CHANGE_ORDER(403, "Answer exists"),

    POST_NOT_FOUND(404, "Post not found"),
    POST_EXISTS(404, "Post not found"),

    //추후 추가 가능 --> 기능 봐서

    NOT_IMPLEMENTATION(501, "No Implementation"),

    NOT_AUTHORIZED(401, "본인만 회원탈퇴 할수있습니다"),
    INVALID_MEMBER_STATUS(400, "Invalid member status");

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
