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
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown=true)
/*     */ public class ChainInfo
/*     */ {
/*     */   @JsonProperty("server_version")
/*     */   private String serverVersion;
/*     */   @JsonProperty("chain_id")
/*     */   private String chainId;
/*     */   @JsonProperty("head_block_num")
/*     */   private String headBlockNum;
/*     */   @JsonProperty("last_irreversible_block_num")
/*     */   private Long lastIrreversibleBlockNum;
/*     */   @JsonProperty("last_irreversible_block_id")
/*     */   private String lastIrreversibleBlockId;
/*     */   @JsonProperty("head_block_id")
/*     */   private String headBlockId;
/*     */   @JsonProperty("head_block_time")
/*     */   private Date headBlockTime;
/*     */   @JsonProperty("head_block_producer")
/*     */   private String headBlockProducer;
/*     */   @JsonProperty("virtual_block_cpu_limit")
/*     */   private String virtualBlockCpuLimit;
/*     */   @JsonProperty("virtual_block_net_limit")
/*     */   private String virtualBlockNetLimit;
/*     */   @JsonProperty("block_cpu_limit")
/*     */   private String blockCpuLimit;
/*     */   @JsonProperty("block_net_limit")
/*     */   private String blockNetLimit;
/*     */   
/*     */   public String getServerVersion()
/*     */   {
/*  57 */     return this.serverVersion;
/*     */   }
/*     */   
/*     */   public void setServerVersion(String serverVersion) {
/*  61 */     this.serverVersion = serverVersion;
/*     */   }
/*     */   
/*     */   public String getChainId() {
/*  65 */     return this.chainId;
/*     */   }
/*     */   
/*     */   public void setChainId(String chainId) {
/*  69 */     this.chainId = chainId;
/*     */   }
/*     */   
/*     */   public String getHeadBlockNum() {
/*  73 */     return this.headBlockNum;
/*     */   }
/*     */   
/*     */   public void setHeadBlockNum(String headBlockNum) {
/*  77 */     this.headBlockNum = headBlockNum;
/*     */   }
/*     */   
/*     */   public Long getLastIrreversibleBlockNum() {
/*  81 */     return this.lastIrreversibleBlockNum;
/*     */   }
/*     */   
/*     */   public void setLastIrreversibleBlockNum(Long lastIrreversibleBlockNum) {
/*  85 */     this.lastIrreversibleBlockNum = lastIrreversibleBlockNum;
/*     */   }
/*     */   
/*     */   public String getLastIrreversibleBlockId() {
/*  89 */     return this.lastIrreversibleBlockId;
/*     */   }
/*     */   
/*     */   public void setLastIrreversibleBlockId(String lastIrreversibleBlockId) {
/*  93 */     this.lastIrreversibleBlockId = lastIrreversibleBlockId;
/*     */   }
/*     */   
/*     */   public Date getHeadBlockTime() {
/*  97 */     return this.headBlockTime;
/*     */   }
/*     */   
/*     */   public void setHeadBlockTime(Date headBlockTime) {
/* 101 */     this.headBlockTime = headBlockTime;
/*     */   }
/*     */   
/*     */   public String getHeadBlockProducer() {
/* 105 */     return this.headBlockProducer;
/*     */   }
/*     */   
/*     */   public void setHeadBlockProducer(String headBlockProducer) {
/* 109 */     this.headBlockProducer = headBlockProducer;
/*     */   }
/*     */   
/*     */   public String getVirtualBlockCpuLimit() {
/* 113 */     return this.virtualBlockCpuLimit;
/*     */   }
/*     */   
/*     */   public void setVirtualBlockCpuLimit(String virtualBlockCpuLimit) {
/* 117 */     this.virtualBlockCpuLimit = virtualBlockCpuLimit;
/*     */   }
/*     */   
/*     */   public String getVirtualBlockNetLimit() {
/* 121 */     return this.virtualBlockNetLimit;
/*     */   }
/*     */   
/*     */   public void setVirtualBlockNetLimit(String virtualBlockNetLimit) {
/* 125 */     this.virtualBlockNetLimit = virtualBlockNetLimit;
/*     */   }
/*     */   
/*     */   public String getBlockCpuLimit() {
/* 129 */     return this.blockCpuLimit;
/*     */   }
/*     */   
/*     */   public void setBlockCpuLimit(String blockCpuLimit) {
/* 133 */     this.blockCpuLimit = blockCpuLimit;
/*     */   }
/*     */   
/*     */   public String getBlockNetLimit() {
/* 137 */     return this.blockNetLimit;
/*     */   }
/*     */   
/*     */   public void setBlockNetLimit(String blockNetLimit) {
/* 141 */     this.blockNetLimit = blockNetLimit;
/*     */   }
/*     */   
/*     */   public String getHeadBlockId() {
/* 145 */     return this.headBlockId;
/*     */   }
/*     */   
/*     */   public void setHeadBlockId(String headBlockId) {
/* 149 */     this.headBlockId = headBlockId;
/*     */   }
/*     */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\api\vo\ChainInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */