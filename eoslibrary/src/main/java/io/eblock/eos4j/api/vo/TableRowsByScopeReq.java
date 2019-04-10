package io.eblock.eos4j.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TableRowsByScopeReq {
   private String code = "eosio";
   private String table;
   private Boolean json = Boolean.valueOf(true);
   private int limit = 50;
   private String lower_bound;

    public String getLower_bound() {
        return lower_bound;
    }

    public void setLower_bound(String lower_bound) {
        this.lower_bound = lower_bound;
    }

    public String getCode() {
     return this.code;
   }
   public void setCode(String code) {
     this.code = code;
   }
   public String getTable() {
     return this.table;
   }
   public void setTable(String table) {
     this.table = table;
   }
   public int getLimit() {
     return this.limit;
   }
   public void setLimit(int limit) {
     this.limit = limit;
   }
   public Boolean getJson() {
     return this.json;
   }
   public void setJson(Boolean json) {
     this.json = json;
   }
}
