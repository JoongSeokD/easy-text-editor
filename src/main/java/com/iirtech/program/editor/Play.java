package com.iirtech.program.editor;

import java.util.Scanner;

import static com.iirtech.program.editor.Message.HELP_COMMAND;
import static com.iirtech.program.editor.Message.INVALID_COMMAND;

public class Play {

    public Play(String[] args) {
        play(createCommandManager(getBaseString(args)), new Scanner(System.in));
    }

    private String getBaseString(String[] args) {
        return args.length > 0 ?
                String.join(" ", args) :
                "안녕하세요. 점심 식사는 하셨나요?";
    }

    private CommandManager createCommandManager(String baseString) {
        return new CommandManager(new TextCommand(Text.builder().output(baseString).build()));
    }

    private void play(CommandManager manager, Scanner sc) {
        manager.print();
        try {
            executeCommand(manager, inputString(sc));
        } catch (IllegalArgumentException |
                StringIndexOutOfBoundsException |
                IllegalStateException e){
            System.out.println(e.getMessage());
        }
        play(manager, sc);
    }

    private String inputString(Scanner sc) {
        System.out.print("입력 : ");
        return sc.nextLine();
    }

    private void executeCommand(CommandManager manager, String method) {
        if (isEdit(method)){
            manager.edit(new TextCommand(Text.builder().editMethod(method).build()));
        }else if (isUndo(method)){
            manager.undo();
        }else if (isRedo(method)){
            manager.redo();
        }else if (isHelp(method)){
            printHelper();
        }else if (isExit(method)){
            exit();
        }else {
            throw new IllegalArgumentException(""+INVALID_COMMAND + HELP_COMMAND);
        }
    }

    private boolean isEdit(String method) {
        return method.startsWith("edit");
    }
    private boolean isUndo(String method) { return method.equals("undo"); }
    private boolean isRedo(String method) { return method.equals("redo"); }
    private boolean isHelp(String method) {
        return method.equals("help") || method.equals("?");
    }
    private boolean isExit(String method) { return method.equals("exit"); }

    private void printHelper() {
        String sb = "\n출력된 텍스트를 수정하시려면 edit(숫자:시작인덱스 , 숫자:끝인덱스 , \"문자열\":치환할 문자)을 입력해주세요.\n" +
                    "재실행을 원하시면 redo\n" +
                    "실행 취소를 원하시면 undo\n" +
                    "종료를 원하시면 exit\n"
                ;
        System.out.println(sb);
    }
    private void exit() {
        System.out.println("종료합니다.");
        System.exit(0);
    }

}
