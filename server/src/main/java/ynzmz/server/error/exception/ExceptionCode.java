package ynzmz.server.error.exception;

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

    //추후 추가 가능 --> 기능 봐서

    NOT_IMPLEMENTATION(501, "No Implementation"),

    NOT_AUTHORIZED(401, "본인만 회원탈퇴 할수있습니다"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    LECTURE_REVIEW_NOT_FOUND(404, "해당 고유번호 강의 리뷰글이 없습니다."),
    LECTURE_NOT_FOUND(404, "해당 고유번호 강의가 없습니다."),
    LECTURE_REVIEW_COMMENT_NOT_FOUND(404, "해당 고유번호 강의 리뷰글 댓글이 없습니다."),
    EVENT_NOT_FOUND(404,"해당 아이디 관련 이벤트가 없습니다"),
    EVENT_NOT_YZ(600,"야놀지말자 이벤트가 아니라 수정할 수 없습니다");


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
