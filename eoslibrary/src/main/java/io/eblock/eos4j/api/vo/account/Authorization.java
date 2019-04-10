package io.eblock.eos4j.api.vo.account;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dyz on 2019/1/21.
 */

public class Authorization {
    private String actor;
    private String permission;

    public Authorization() {
    }

    public String getActor() {
        return this.actor;
    }

    @JsonProperty("actor")
    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getPermission() {
        return this.permission;
    }

    @JsonProperty("permission")
    public void setPermission(String permission) {
        this.permission = permission;
    }
}
