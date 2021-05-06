package com.iirtech.program.editor;

import lombok.Builder;

import static com.iirtech.program.editor.Message.*;

@Builder
public class Text{

    private String output;
    private final String editMethod;

    public void edit(){
        validationEditMethod();
        replaceOutput();
    }

    private void validationEditMethod() {
        if (!getEditMethodRegExp()){
            throw new IllegalArgumentException(
                    ""+INVALID_COMMAND + EXAMPLE_EDIT_COMMAND + HELP_COMMAND);
        }
    }

    private boolean getEditMethodRegExp() {
        final var numberRegExp = "[\\s]*[+]?[0-9]{1,10}[\\s]*";
        final var StringRegExp = "[\\s]*[\"](.*)[\"][\\s]*";
        return editMethod.matches("^edit[\\s]*\\(" +
                numberRegExp +
                "[,]" + numberRegExp +
                "[,]" + StringRegExp +
                "\\)[\\s]*$");
    }

    private void replaceOutput() {
        try {
            output = replace(escapeParentheses().split(","));
        }catch (StringIndexOutOfBoundsException e){
            throw new StringIndexOutOfBoundsException(
                    ""+INVALID_INDEX_NUMBER_FORMAT + EXAMPLE_EDIT_COMMAND + HELP_COMMAND);
        }
    }

    private String escapeParentheses() {
        final int begin = editMethod.indexOf("(") + 1;
        final int end = editMethod.lastIndexOf(")");
        return editMethod.substring(begin, end);
    }

    private String replace(String[] params) {
        if (params.length != 3){
            throw new IllegalArgumentException(
                    ""+INVALID_PARAMETER + EXAMPLE_EDIT_COMMAND + HELP_COMMAND);
        }
        return new StringBuilder(output)
                .replace(
                        getIndex(params[0]),
                        getIndex(params[1]),
                        escapeQuotation(params[2]))
                .toString();
    }

    private int getIndex(String param) {
        return Integer.parseInt(param.trim());
    }

    private String escapeQuotation(String quotationString) {
        final int start = quotationString.indexOf("\"") + 1;
        final int end = quotationString.lastIndexOf("\"");
        return quotationString.substring(start, end);
    }

    public String getOutput() {
        return this.output;
    }
    public void setOutput(String output) {
        this.output = output;
    }
    public void print(){ System.out.println("출력 : " + output); }
}
