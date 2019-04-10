package io.eblock.eos4j.api.exception;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ErrorDetails {
    private String message;
    private String file;
    private Integer lineNumber;
    private String method;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getLineNumber() {
        return this.lineNumber;
    }

    @JsonProperty("line_number")
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\api\exception\ErrorDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */