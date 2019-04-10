package io.eblock.eos4j.api.exception;


public class Error {
    private String code;


    private String name;


    private String what;


    private ErrorDetails[] details;


    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhat() {
        return this.what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public ErrorDetails[] getDetails() {
        return this.details;
    }

    public void setDetails(ErrorDetails[] details) {
        this.details = details;
    }
}

