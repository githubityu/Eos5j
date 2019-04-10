package io.eblock.eos4j.api.vo.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Key {
    private String key;
    private Long weight;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getWeight() {
        return this.weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\api\vo\account\Key.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */