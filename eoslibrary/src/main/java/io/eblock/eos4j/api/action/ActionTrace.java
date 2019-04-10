package io.eblock.eos4j.api.action;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import io.eblock.eos4j.api.vo.transaction.Receipt;

/**
 * Created by dyz on 2019/1/21.
 */

public class ActionTrace {
    private Act act;
    private String console;
    private Integer cpuUsage;
    private Integer elapsed;
    private List<Object> inlineTraces = null;
    private Receipt receipt;
    private Integer totalCpuUsage;
    private String trxId;
    private String contextFree;
    private Integer blockNum;
    private String blockTime;
    private String producerBlockId;
    private List<Object> accountRamDeltas;
    private String except;

    public ActionTrace() {
    }

    public List<Object> getAccountRamDeltas() {
        return this.accountRamDeltas;
    }

    @JsonProperty("account_ram_deltas")
    public void setAccountRamDeltas(List<Object> accountRamDeltas) {
        this.accountRamDeltas = accountRamDeltas;
    }

    public String getProducerBlockId() {
        return this.producerBlockId;
    }

    @JsonProperty("producer_block_id")
    public void setProducerBlockId(String producerBlockId) {
        this.producerBlockId = producerBlockId;
    }

    public String getExcept() {
        return this.except;
    }

    @JsonProperty("except")
    public void setExcept(String except) {
        this.except = except;
    }

    public String getBlockTime() {
        return this.blockTime;
    }

    @JsonProperty("block_time")
    public void setBlockTime(String blockTime) {
        this.blockTime = blockTime;
    }

    public Integer getBlockNum() {
        return this.blockNum;
    }

    @JsonProperty("block_num")
    public void setBlockNum(Integer blockNum) {
        this.blockNum = blockNum;
    }

    public String getContextFree() {
        return this.contextFree;
    }

    @JsonProperty("context_free")
    public void setContextFree(String contextFree) {
        this.contextFree = contextFree;
    }

    public Act getAct() {
        return this.act;
    }

    @JsonProperty("act")
    public void setAct(Act act) {
        this.act = act;
    }

    public String getConsole() {
        return this.console;
    }

    @JsonProperty("console")
    public void setConsole(String console) {
        this.console = console;
    }

    public Integer getCpuUsage() {
        return this.cpuUsage;
    }

    @JsonProperty("cpu_usage")
    public void setCpuUsage(Integer cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Integer getElapsed() {
        return this.elapsed;
    }

    @JsonProperty("elapsed")
    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public List<Object> getInlineTraces() {
        return this.inlineTraces;
    }

    @JsonProperty("inline_traces")
    public void setInlineTraces(List<Object> inlineTraces) {
        this.inlineTraces = inlineTraces;
    }

    public Receipt getReceipt() {
        return this.receipt;
    }

    @JsonProperty("receipt")
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Integer getTotalCpuUsage() {
        return this.totalCpuUsage;
    }

    @JsonProperty("total_cpu_usage")
    public void setTotalCpuUsage(Integer totalCpuUsage) {
        this.totalCpuUsage = totalCpuUsage;
    }

    public String getTrxId() {
        return this.trxId;
    }

    @JsonProperty("trx_id")
    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    @Override
    public String toString() {
        return "ActionTrace{" +
                "act=" + act +
                ", console='" + console + '\'' +
                ", cpuUsage=" + cpuUsage +
                ", elapsed=" + elapsed +
                ", inlineTraces=" + inlineTraces +
                ", receipt=" + receipt +
                ", totalCpuUsage=" + totalCpuUsage +
                ", trxId='" + trxId + '\'' +
                ", contextFree='" + contextFree + '\'' +
                ", blockNum=" + blockNum +
                ", blockTime='" + blockTime + '\'' +
                ", producerBlockId='" + producerBlockId + '\'' +
                ", accountRamDeltas=" + accountRamDeltas +
                ", except='" + except + '\'' +
                '}';
    }
}
