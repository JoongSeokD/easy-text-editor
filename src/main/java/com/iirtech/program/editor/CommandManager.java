package com.iirtech.program.editor;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.iirtech.program.editor.Message.*;

public class CommandManager {
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();
    private Command snapShot;

    public CommandManager(Command snapShot) {
        this.snapShot = snapShot;
    }

    public void edit(Command command) {
        redoStack.clear();
        pushUndoStack();
        executeEdit(command);
    }

    private void pushUndoStack() {
        try {
            undoStack.push(snapShot);
        }catch (OutOfMemoryError e){
            undoStack.clear();
            undoStack.push(snapShot);
            System.out.println(INITIALIZE_UNDO_STACK);
        }
    }

    private void executeEdit(Command command) {
        setOutput(command);
        try {
            command.execute();
            this.snapShot = command;
        }catch (IllegalArgumentException |
                StringIndexOutOfBoundsException e){
            if (!undoStack.isEmpty()){
                undoStack.pop();
            }
            throw e;
        }
    }

    private void setOutput(Command newCommand) {
        final var oldCommand = (TextCommand) this.snapShot;
        final var output = oldCommand.getText().getOutput();

        ((TextCommand) newCommand).getText().setOutput(output);
    }

    public void undo(){readOrUndo(undoStack, redoStack);}
    public void redo(){readOrUndo(redoStack, undoStack);}

    private void readOrUndo(Deque<Command>popStack, Deque<Command>pushStack){
        validationPopStack(popStack);
        pushStack.push(snapShot);
        snapShot = popStack.pop();
    }

    private void validationPopStack(Deque<Command> popStack) {
        if (popStack.isEmpty()){
            var throwMessage = popStack.equals(undoStack) ?
                    WARNING_IS_EMPTY_UNDO_STACK : WARNING_IS_EMPTY_REDO_STACK;
            throw new IllegalStateException(throwMessage.toString());
        }
    }

    public void print() {((TextCommand) this.snapShot).print();}
}
