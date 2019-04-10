package io.eblock.eos4j.api.vo.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CpuLimit {
    private Long used;
    private Long available;
    private Long max;

    public Long getUsed() {
        return this.used;
    }

    public void setUsed(Long used) {
        this.used = used;
    }

    public Long getAvailable() {
        return this.available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Long getMax() {
        return this.max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "CpuLimit{" +
                "used=" + used +
                ", available=" + available +
                ", max=" + max +
                '}';
    }
}
