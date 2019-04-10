package io.eblock.eos4j.api.action;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import io.eblock.eos4j.api.vo.account.Authorization;

/**
 * Created by dyz on 2019/1/21.
 */

public class Act {
    private String account;
    private List<Authorization> authorization = null;
    private Object data;
    private String hexData;
    private String name;

    public Act() {
    }

    public String getAccount() {
        return this.account;
    }

    @JsonProperty("account")
    public void setAccount(String account) {
        this.account = account;
    }

    public List<Authorization> getAuthorization() {
        return this.authorization;
    }

    @JsonProperty("authorization")
    public void setAuthorization(List<Authorization> authorization) {
        this.authorization = authorization;
    }

    public Object getData() {
        return this.data;
    }

    @JsonProperty("data")
    public void setData(Object data) {
        this.data = data;
    }

    public String getHexData() {
        return this.hexData;
    }

    @JsonProperty("hex_data")
    public void setHexData(String hexData) {
        this.hexData = hexData;
    }

    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Act{" +
                "account='" + account + '\'' +
                ", authorization=" + authorization +
                ", data=" + data +
                ", hexData='" + hexData + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
