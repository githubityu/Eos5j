 package io.eblock.eos4j.api.vo;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import java.util.List;
/*    */ import java.util.Map;
 @JsonIgnoreProperties(ignoreUnknown=true)
 public class TableRows
 {
   private Boolean more;
   private List<Map> rows;
   public Boolean getMore(){
     return this.more;
   }

   public void setMore(Boolean more) {
     this.more = more;
   }

  public List<Map> getRows() {
     return this.rows;
   }

   public void setRows(List<Map> rows) {
     this.rows = rows;
   }

     @Override
     public String toString() {
         return "TableRows{" +
                 "more=" + more +
                 ", rows=" + rows +
                 '}';
     }
 }
