package io.eblock.eos4j.api.action;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by dyz on 2019/1/21.
 */

public class Action {
//    private Integer accountActionSeq;
    private List<ActionTrace> actionTrace;
    private Integer blockNum;
//    private Integer lastIrreversibleBlock;
    private String blockTime;
//    private Long globalActionSeq;

    public Action() {
    }
/*
    public Integer getAccountActionSeq() {
        return this.accountActionSeq;
    }*/

//    public Integer getLastIrreversibleBlock() {
//        return this.lastIrreversibleBlock;
//    }

//    @JsonProperty("last_irreversible_block")
//    public void setLastIrreversibleBlock(Integer lastIrreversibleBlock) {
//        this.lastIrreversibleBlock = lastIrreversibleBlock;
//    }

    /*@JsonProperty("account_action_seq")
    public void setAccountActionSeq(Integer accountActionSeq) {
        this.accountActionSeq = accountActionSeq;
    }*/

    public List<ActionTrace> getActionTrace() {
        return this.actionTrace;
    }

    @JsonProperty("action_traces")
    public void setActionTrace(List<ActionTrace> actionTrace) {
        this.actionTrace = actionTrace;
    }

    public Integer getBlockNum() {
        return this.blockNum;
    }

    @JsonProperty("block_num")
    public void setBlockNum(Integer blockNum) {
        this.blockNum = blockNum;
    }

    public String getBlockTime() {
        return this.blockTime;
    }

    @JsonProperty("block_time")
    public void setBlockTime(String blockTime) {
        this.blockTime = blockTime;
    }

//    public Long getGlobalActionSeq() {
//        return this.globalActionSeq;
//    }
//
//    @JsonProperty("global_action_seq")
//    public void setGlobalActionSeq(Long globalActionSeq) {
//        this.globalActionSeq = globalActionSeq;
//    }

    @Override
    public String toString() {
        return "Action{" +
                /*"accountActionSeq=" + accountActionSeq +*/
                ", actionTrace=" + actionTrace +
                ", blockNum=" + blockNum +
                /*", lastIrreversibleBlock=" + lastIrreversibleBlock +*/
                ", blockTime='" + blockTime + '\'' +
               /* ", globalActionSeq=" + globalActionSeq +*/
                '}';
    }
}
