//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.eblock.eos4j.api.vo.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.eblock.Issue2245Test;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class Transaction extends Issue2245Test.Bar {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("processed")
    private Processed processed;

    public Transaction() {
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Processed getProcessed() {
        return this.processed;
    }

    public void setProcessed(Processed processed) {
        this.processed = processed;
    }
}
