package com.iirtech.program.editor;

import lombok.Builder;

import static com.iirtech.program.editor.Message.*;

@Builder
public class Text{

    private String output;
    private final String editCommand;

    public void edit(){
        validationEditCommand();
        replaceOutput();
    }

    private void validationEditCommand() {
        if (!regExpEditCommand()){
            throw new IllegalArgumentException(
                    ""+INVALID_COMMAND + EXAMPLE_EDIT_COMMAND + HELP_COMMAND);
        }
    }

    private boolean regExpEditCommand() {
        final var numberRegExp = "[\\s]*[+]?[0-9]{1,10}[\\s]*";
        final var StringRegExp = "[\\s]*[\"](.*)[\"][\\s]*";
        return editCommand.matches("^edit[\\s]*\\(" +
                numberRegExp +
                "[,]" + numberRegExp +
                "[,]" + StringRegExp +
                "\\)[\\s]*$");
    }

    private void replaceOutput() {
        try {
            output = replace(escapeParentheses());
        }catch (StringIndexOutOfBoundsException e){
            throw new StringIndexOutOfBoundsException(
                    ""+INVALID_INDEX_NUMBER_FORMAT + EXAMPLE_EDIT_COMMAND + HELP_COMMAND);
        }
    }

    private String escapeParentheses() {
        final int begin = editCommand.indexOf("(") + 1;
        final int end = editCommand.lastIndexOf(")");
        return editCommand.substring(begin, end);
    }

    private String replace(String param) {
        final var params = param.split(",");
        return new StringBuilder(output)
                .replace(
                        getIndex(params[0]),
                        getIndex(params[1]),
                        escapeQuotation(getQuotationString(param)))
                .toString();
    }

    private int getIndex(String param) {
        return Integer.parseInt(param.trim());
    }

    private String getQuotationString(String param) {
        final var firstCommaCurrent = param.indexOf(",") + 1;
        final var secondCommaCurrent = param.indexOf(",", firstCommaCurrent) + 1;
        return param.substring(secondCommaCurrent);
    }

    private String escapeQuotation(String quotationString) {
        final var start = quotationString.indexOf("\"") + 1;
        final var end = quotationString.lastIndexOf("\"");
        return quotationString.substring(start, end);
    }

    public String getOutput() { return this.output; }
    public void setOutput(String output) { this.output = output; }
    public void print(){ System.out.println("출력 : " + output); }
}
