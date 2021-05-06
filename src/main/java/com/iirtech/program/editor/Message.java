package com.iirtech.program.editor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Message {

    INVALID_COMMAND("\n명령어를 인식할 수 없습니다. "),
    INVALID_INDEX_NUMBER_FORMAT("\n치환할 문자열의 시작 인덱스와 종료 인덱스를 바르게 입력해 주세요. "),
    EXAMPLE_EDIT_COMMAND("\nex) edit(1:시작인덱스, 2:끝인덱스, \"반갑습니다.\":치환할 문자열)"),

    HELP_COMMAND("\n도움이 필요하시면 help 또는 ?를 입력해주세요."),

    WARNING_IS_EMPTY_REDO_STACK("경고 : 더 이상 재실행을 할 수 없습니다."),
    WARNING_IS_EMPTY_UNDO_STACK("경고 : 더 이상 실행취소를 할 수 없습니다."),

    INITIALIZE_UNDO_STACK("실행취소 스택을 초기화 합니다.");

    private final String text;

    @Override
    public String toString() {
        return text;
    }
}
