package io.eblock.eos4j.api.action;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by dyz on 2019/1/21.
 */

public class Actions {
    private List<Action> actions;
//    @JsonProperty("last_irreversible_block")
//    private Integer lastIrreversibleBlock;

    public Actions() {
    }

    @JsonProperty("actions")
    public List<Action> getActions() {
        return this.actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    /*public Integer getLastIrreversibleBlock() {
        return this.lastIrreversibleBlock;
    }

    public void setLastIrreversibleBlock(Integer lastIrreversibleBlock) {
        this.lastIrreversibleBlock = lastIrreversibleBlock;
    }*/

    @Override
    public String toString() {
        return "Actions{" +
                "actions=" + actions +
                /*", lastIrreversibleBlock=" + lastIrreversibleBlock +*/
                '}';
    }
}
