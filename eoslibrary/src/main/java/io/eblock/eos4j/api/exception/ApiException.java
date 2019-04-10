package io.eblock.eos4j.api.exception;


public class ApiException
        extends RuntimeException {
    private static final long serialVersionUID = 1L;


    private ApiError error;


    public ApiException(ApiError apiError) {
        this.error = apiError;
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiError getError() {
        return this.error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    public String getMessage() {
        if (this.error != null) {
            return this.error.getError().getCode()+error.getError().getWhat();
        }
        return super.getMessage();
    }
}
