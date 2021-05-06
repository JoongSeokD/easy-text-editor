package com.iirtech.program.editor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TextCommand implements Command {
    private final Text text;

    @Override
    public void execute() {
        text.edit();
    }
    public void print(){
        text.print();
    }
}
