/*    */ package io.eblock.eos4j.api.vo;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SignParam
/*    */ {
/*    */   private Date headBlockTime;
/*    */   private String chainId;
/*    */   private Long lastIrreversibleBlockNum;
/*    */   private Long refBlockPrefix;
/*    */   private Long exp;
/*    */   
/*    */   public Date getHeadBlockTime()
/*    */   {
/* 33 */     return this.headBlockTime;
/*    */   }
/*    */   
/*    */   public void setHeadBlockTime(Date headBlockTime) {
/* 37 */     this.headBlockTime = headBlockTime;
/*    */   }
/*    */   
/*    */   public String getChainId() {
/* 41 */     return this.chainId;
/*    */   }
/*    */   
/*    */   public void setChainId(String chainId) {
/* 45 */     this.chainId = chainId;
/*    */   }
/*    */   
/*    */   public Long getLastIrreversibleBlockNum() {
/* 49 */     return this.lastIrreversibleBlockNum;
/*    */   }
/*    */   
/*    */   public void setLastIrreversibleBlockNum(Long lastIrreversibleBlockNum) {
/* 53 */     this.lastIrreversibleBlockNum = lastIrreversibleBlockNum;
/*    */   }
/*    */   
/*    */   public Long getRefBlockPrefix() {
/* 57 */     return this.refBlockPrefix;
/*    */   }
/*    */   
/*    */   public void setRefBlockPrefix(Long refBlockPrefix) {
/* 61 */     this.refBlockPrefix = refBlockPrefix;
/*    */   }
/*    */   
/*    */   public Long getExp() {
/* 65 */     return this.exp;
/*    */   }
/*    */   
/*    */   public void setExp(Long exp) {
/* 69 */     this.exp = exp;
/*    */   }
/*    */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\api\vo\SignParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */