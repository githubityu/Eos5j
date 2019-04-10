package io.eblock.eos4j.api.vo.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class NetLimit {
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
        return "NetLimit{" +
                "used=" + used +
                ", available=" + available +
                ", max=" + max +
                '}';
    }
}


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\api\vo\account\NetLimit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */