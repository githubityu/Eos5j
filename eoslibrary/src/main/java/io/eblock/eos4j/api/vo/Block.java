/*     */ package io.eblock.eos4j.api.vo;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.fasterxml.jackson.annotation.JsonProperty;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown=true)
/*     */ public class Block
/*     */ {
/*     */   @JsonProperty("timestamp")
/*     */   private Date timestamp;
/*     */   @JsonProperty("producer")
/*     */   private String producer;
/*     */   @JsonProperty("confirmed")
/*     */   private Long confirmed;
/*     */   @JsonProperty("previous")
/*     */   private String previous;
/*     */   @JsonProperty("transaction_mroot")
/*     */   private String transactionMroot;
/*     */   @JsonProperty("action_mroot")
/*     */   private String actionMroot;
/*     */   @JsonProperty("schedule_version")
/*     */   private String scheduleVersion;
/*     */   @JsonProperty("id")
/*     */   private String id;
/*     */   @JsonProperty("block_num")
/*     */   private Long blockNum;
/*     */   @JsonProperty("ref_block_prefix")
/*     */   private Long refBlockPrefix;
/*     */   
/*     */   public Date getTimestamp()
/*     */   {
/*  51 */     return this.timestamp;
/*     */   }
/*     */   
/*     */   public void setTimestamp(Date timestamp) {
/*  55 */     this.timestamp = timestamp;
/*     */   }
/*     */   
/*     */   public String getProducer() {
/*  59 */     return this.producer;
/*     */   }
/*     */   
/*     */   public void setProducer(String producer) {
/*  63 */     this.producer = producer;
/*     */   }
/*     */   
/*     */   public Long getConfirmed() {
/*  67 */     return this.confirmed;
/*     */   }
/*     */   
/*     */   public void setConfirmed(Long confirmed) {
/*  71 */     this.confirmed = confirmed;
/*     */   }
/*     */   
/*     */   public String getPrevious() {
/*  75 */     return this.previous;
/*     */   }
/*     */   
/*     */   public void setPrevious(String previous) {
/*  79 */     this.previous = previous;
/*     */   }
/*     */   
/*     */   public String getTransactionMroot() {
/*  83 */     return this.transactionMroot;
/*     */   }
/*     */   
/*     */   public void setTransactionMroot(String transactionMroot) {
/*  87 */     this.transactionMroot = transactionMroot;
/*     */   }
/*     */   
/*     */   public String getActionMroot() {
/*  91 */     return this.actionMroot;
/*     */   }
/*     */   
/*     */   public void setActionMroot(String actionMroot) {
/*  95 */     this.actionMroot = actionMroot;
/*     */   }
/*     */   
/*     */   public String getScheduleVersion() {
/*  99 */     return this.scheduleVersion;
/*     */   }
/*     */   
/*     */   public void setScheduleVersion(String scheduleVersion) {
/* 103 */     this.scheduleVersion = scheduleVersion;
/*     */   }
/*     */   
/*     */   public String getId() {
/* 107 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 111 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Long getBlockNum() {
/* 115 */     return this.blockNum;
/*     */   }
/*     */   
/*     */   public void setBlockNum(Long blockNum) {
/* 119 */     this.blockNum = blockNum;
/*     */   }
/*     */   
/*     */   public Long getRefBlockPrefix() {
/* 123 */     return this.refBlockPrefix;
/*     */   }
/*     */   
/*     */   public void setRefBlockPrefix(Long refBlockPrefix) {
/* 127 */     this.refBlockPrefix = refBlockPrefix;
/*     */   }
/*     */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\api\vo\Block.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */