package io.eblock.eos4j.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by thinkpad on 2019/1/10.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class JsonToBeanParam {
    private String code;
    private String action;
    private ArgsBean args;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArgsBean getArgs() {
        return args;
    }

    public void setArgs(ArgsBean args) {
        this.args = args;
    }

    public static class ArgsBean{
        private String account;
        private String bytes;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getBytes() {
            return bytes;
        }

        public void setBytes(String bytes) {
            this.bytes = bytes;
        }
    }
}
