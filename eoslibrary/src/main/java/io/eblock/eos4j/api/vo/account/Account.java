package io.eblock.eos4j.api.vo.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("privileged")
    private String privileged;
    @JsonProperty("last_code_update")
    private Date lastCodeUpdate;
    @JsonProperty("created")
    private String created;
    @JsonProperty("ram_quota")
    private Long ramQuota;
    @JsonProperty("net_weight")
    private Long netWeight;
    @JsonProperty("cpu_weight")
    private Long cpuWeight;
    @JsonProperty("net_limit")
    private NetLimit netLimit;
    @JsonProperty("cpu_limit")
    private CpuLimit cpuLimit;
    @JsonProperty("ram_usage")
    private Long ramUsage;
    @JsonProperty("permissions")
    private List<Permission> permissions;
    @JsonProperty("refund_request")
    private RefundRequestBean refundRequest;
    @JsonProperty("total_resources")
    private TotalResources totalResources;

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPrivileged() {
        return this.privileged;
    }

    public void setPrivileged(String privileged) {
        this.privileged = privileged;
    }

    public Date getLastCodeUpdate() {
        return this.lastCodeUpdate;
    }

    public void setLastCodeUpdate(Date lastCodeUpdate) {
        this.lastCodeUpdate = lastCodeUpdate;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Long getRamQuota() {
        return this.ramQuota;
    }

    public void setRamQuota(Long ramQuota) {
        this.ramQuota = ramQuota;
    }

    public Long getNetWeight() {
        return this.netWeight;
    }

    public void setNetWeight(Long netWeight) {
        this.netWeight = netWeight;
    }

    public Long getCpuWeight() {
        return this.cpuWeight;
    }

    public void setCpuWeight(Long cpuWeight) {
        this.cpuWeight = cpuWeight;
    }

    public NetLimit getNetLimit() {
        return this.netLimit;
    }

    public void setNetLimit(NetLimit netLimit) {
        this.netLimit = netLimit;
    }

    public CpuLimit getCpuLimit() {
        return this.cpuLimit;
    }

    public void setCpuLimit(CpuLimit cpuLimit) {
        this.cpuLimit = cpuLimit;
    }

    public Long getRamUsage() {
        return this.ramUsage;
    }

    public void setRamUsage(Long ramUsage) {
        this.ramUsage = ramUsage;
    }

    public List<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public RefundRequestBean getRefundRequest() {
        return refundRequest;
    }

    public void setRefundRequest(RefundRequestBean refundRequest) {
        this.refundRequest = refundRequest;
    }

    public TotalResources getTotalResources() {
        return totalResources;
    }

    public void setTotalResources(TotalResources totalResources) {
        this.totalResources = totalResources;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", privileged='" + privileged + '\'' +
                ", lastCodeUpdate=" + lastCodeUpdate +
                ", created=" + created +
                ", ramQuota=" + ramQuota +
                ", netWeight=" + netWeight +
                ", cpuWeight=" + cpuWeight +
                ", netLimit=" + netLimit +
                ", cpuLimit=" + cpuLimit +
                ", ramUsage=" + ramUsage +
                ", permissions=" + permissions +
                ", refundRequest=" + refundRequest +
                ", totalResources=" + totalResources +
                '}';
    }
}

