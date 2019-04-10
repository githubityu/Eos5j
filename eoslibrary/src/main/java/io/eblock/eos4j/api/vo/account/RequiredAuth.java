package io.eblock.eos4j.api.vo.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RequiredAuth {
//    private List<String> accounts;
    private List<Key> keys;
    private Long threshold;

//    public List<String> getAccounts() {
//        return this.accounts;
//    }
//
//    public void setAccounts(List<String> accounts) {
//        this.accounts = accounts;
//    }

    public List<Key> getKeys() {
        return this.keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public Long getThreshold() {
        return this.threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }
}


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\api\vo\account\RequiredAuth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */