package io.eblock.eos4j.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TableRowsReq{
   private String code = "eosio";
   private String scope;
   private String table;
   private Boolean json = Boolean.valueOf(true);
   private int limit = 50;
   private String lower_bound;
   private String upper_bound;
   private Boolean reverse = Boolean.valueOf(true);
   private String key_type = "i64";
   private String index_position = "secondary";

    public String getKey_type() {
        return key_type;
    }

    public void setKey_type(String key_type) {
        this.key_type = key_type;
    }

    public String getIndex_position() {
        return index_position;
    }

    public void setIndex_position(String index_position) {
        this.index_position = index_position;
    }

    public String getCode() {
     return this.code;
   }
   public void setCode(String code) {
     this.code = code;
   }
   public String getScope() {
     return this.scope;
   }
   public void setScope(String scope) {
     this.scope = scope;
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

    public String getLower_bound() {
        return lower_bound;
    }

    public void setLower_bound(String lower_bound) {
        this.lower_bound = lower_bound;
    }

    public String getUpper_bound() {
        return upper_bound;
    }

    public void setUpper_bound(String upper_bound) {
        this.upper_bound = upper_bound;
    }

    public Boolean getReverse() {
        return reverse;
    }

    public void setReverse(Boolean reverse) {
        this.reverse = reverse;
    }
}
