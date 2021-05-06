package com.iirtech.program.editor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.iirtech.program.editor.Text.builder;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandManagerTest {

    CommandManager manager;

    @BeforeEach
    void beforeEach(){
        manager = new CommandManager(new TextCommand(builder()
                .output("안녕하세요. 점심 식사는 하셨나요?").build()));
    }

    /*
        @Test
        @DisplayName("edit 명령어 - 실행 취소 스택 OutOfMemory Catch Test")
        void edit_undo_stack_out_of_memory_test() {
            for (int i = 0; i < 45_000_000; i++) {
                validEditExecute();
            }
        }
    */

    @Test
    @DisplayName("edit 명령어 - 잘못된 매개 변수 테스트 (인덱스)")
    void edit_input_index_wrong_test(){
        assertThrows(StringIndexOutOfBoundsException.class,
                this::invalidEditExecute);
    }

    @DisplayName("edit 명령어 - 잘못된 매개 변수 테스트 (파라미터)")
    @ParameterizedTest(name = "{index} {displayName} {0}")
    @ValueSource(strings = {
            "edit(1,2,'\")",
            "edit(1,2,\"')",
            "edit(,2,\"')",
            "edit(1,,\"')",
            "edit(1,2,\")",
            "edit(1,2,)",
            "edit(-1,2,\"\")",
            "edit(1,-2,\"\")",
            "edit(a,2,\"\")",
            "edit(1,b,\"\")",
            "edit(1,2,\"\",3)",
            "edit(1,2,\"\",\")",
            "edit(1,2,\"\"\"asdasd)",
            "edit(1,2,\"\",,,)",
            "edit(1,2,\"\",,,,)",
    })
    void edit_input_parameter_wrong_test(String method){
        assertThrows(IllegalArgumentException.class, () -> getEdit(method));
    }

    @DisplayName("edit 명령어 - 성공")
    @ParameterizedTest(name = "{index} {displayName} {0}")
    @ValueSource(strings = {
            "edit (1,2,\"hello\")",
            "edit ( 1,2,\"hello\")",
            "edit ( 1 ,2,\"hello\")",
            "edit ( 1 , 2,\"hello\")",
            "edit ( 1 , 2 ,\"hello\")",
            "edit ( 1 , 2 , \"hello\")",
            "edit ( 1 , 2 , \"hello\" )",
            "edit ( 1 , 2 , \"hello\" ) ",
            "edit( 1 , 2 , \"hello\" ) ",
            "edit( 1 , 2 , \"\"hello\" ) ",
            "edit( 1 , 2 , \"\" ) ",
            "edit( +1 , 2 , \"\" ) ",
            "edit( +1 , +2 , \"\" ) ",
            "edit( 1 , +2 , \"\" ) ",
    })
    void edit_input_correct_test(String method){
        assertDoesNotThrow(()-> getEdit(method));
    }

    @Test
    @DisplayName("undo 명령어 - 성공")
    void execute_undo(){
        validEditExecute();
        assertDoesNotThrow(() -> undo());
    }
    @Test
    @DisplayName("undo 명령어 - 실패")
    void execute_undo_fail(){
        assertThrows(IllegalStateException.class, () -> undo());
    }
    @Test
    @DisplayName("redo 명령어 - 성공")
    void execute_redo(){
        validEditExecute();
        assertDoesNotThrow(()-> undo());
        assertDoesNotThrow(()-> redo());
    }

    @Test
    @DisplayName("redo 명령어 - 실패")
    void execute_redo_fail(){
        assertThrows(IllegalStateException.class, () -> redo());
        validEditExecute();
        assertDoesNotThrow(()->undo());
        assertDoesNotThrow(() ->redo());
        assertThrows(IllegalStateException.class, () -> redo());
    }

    void validEditExecute() {
        getEdit("edit(1,3,\"hello\")");
    }

    void invalidEditExecute() {
        getEdit("edit(2,1,\"\")");
    }
    void getEdit(String method) {
        manager.edit(new TextCommand(builder()
                .editMethod(method).build()));
    }
    void undo() {
        manager.undo();
    }
    void redo() {
        manager.redo();
    }
}