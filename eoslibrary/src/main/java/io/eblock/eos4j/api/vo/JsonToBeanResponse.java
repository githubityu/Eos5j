package io.eblock.eos4j.api.vo;

/**
 * Created by thinkpad on 2019/1/10.
 */

public class JsonToBeanResponse {
    private String binargs;

    public String getBinargs() {
        return binargs;
    }

    public void setBinargs(String binargs) {
        this.binargs = binargs;
    }

    @Override
    public String toString() {
        return "JsonToBeanResponse{" +
                "binargs='" + binargs + '\'' +
                '}';
    }
}
