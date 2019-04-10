/*    */ package io.eblock.eos4j.api.vo.transaction;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonIgnoreProperties(ignoreUnknown=true)
/*    */ public class Receipt
/*    */ {
/*    */   @JsonProperty("status")
/*    */   private String status;
/*    */   @JsonProperty("cpu_usage_us")
/*    */   private Long cpuUsageUs;
/*    */   @JsonProperty("net_usage_words")
/*    */   private Long netUsageWords;
/*    */   
/*    */   public String getStatus()
/*    */   {
/* 24 */     return this.status;
/*    */   }
/*    */   
/*    */   public void setStatus(String status) {
/* 28 */     this.status = status;
/*    */   }
/*    */   
/*    */   public Long getCpuUsageUs() {
/* 32 */     return this.cpuUsageUs;
/*    */   }
/*    */   
/*    */   public void setCpuUsageUs(Long cpuUsageUs) {
/* 36 */     this.cpuUsageUs = cpuUsageUs;
/*    */   }
/*    */   
/*    */   public Long getNetUsageWords() {
/* 40 */     return this.netUsageWords;
/*    */   }
/*    */   
/*    */   public void setNetUsageWords(Long netUsageWords) {
/* 44 */     this.netUsageWords = netUsageWords;
/*    */   }
/*    */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\api\vo\transaction\Receipt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */