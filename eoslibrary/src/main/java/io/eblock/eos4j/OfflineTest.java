/*    */ package io.eblock.eos4j;
/*    */ 
/*    */ import io.eblock.eos4j.api.exception.ApiError;
/*    */ import io.eblock.eos4j.api.exception.ApiException;
/*    */ import io.eblock.eos4j.api.vo.SignParam;
/*    */ import io.eblock.eos4j.api.vo.transaction.Transaction;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OfflineTest
/*    */ {
/*    */   public static void main(String[] args) {}
/*    */   
/*    */   public static void testOfflineCreate()
/*    */   {
/* 22 */     Rpc rpc = new Rpc("http://139.159.210.91:8080");
/*    */     
/* 24 */     SignParam params = rpc.getOfflineSignParams(Long.valueOf(60L));
/*    */     
/* 26 */     OfflineSign sign = new OfflineSign();
/*    */     
/* 28 */     String content = "";
/*    */     try {
/* 30 */       content = sign.createAccount(params, "5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3", "eeeeeeeeeeee", "555555555551", "EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV", "EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV", 
/*    */       
/* 32 */         Long.valueOf(8000L));
/* 33 */       System.out.println(content);
/*    */     } catch (Exception e) {
/* 35 */       e.printStackTrace();
/*    */     }
/*    */     try
/*    */     {
/* 39 */       Transaction tx = rpc.pushTransaction(content);
/* 40 */       System.out.println(tx.getTransactionId());
/*    */     } catch (ApiException ex) {
/* 42 */       System.out.println(ex.getError().getCode());
/*    */     } catch (Exception e) {
/* 44 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public static void testOfflineTransfer() {
/* 49 */     Rpc rpc = new Rpc("http://139.159.210.91:8080");
/*    */     
/*    */ 
/* 52 */     SignParam params = new SignParam();
/* 53 */     params.setChainId("cf057bbfb72640471fd910bcb67639c22df9f92470936cddc1ade0e2f2e7dc4f");
/* 54 */     params.setLastIrreversibleBlockNum(Long.valueOf(3767683L));
/* 55 */     params.setRefBlockPrefix(Long.valueOf(2790153026L));
/* 56 */     Date date = new Date();
/* 57 */     params.setHeadBlockTime(date);
/* 58 */     params.setExp(Long.valueOf(600L));
/*    */     
/* 60 */     OfflineSign sign = new OfflineSign();
/*    */     
/* 62 */     String content = "";
/*    */     try {
/* 64 */       content = sign.transfer(params, "5JCjesis9t66SKFqRiCCrL5RHxAyYYCrPd1dzsjM9eZELeM4exE", "eosio.token", "winner111115", "winner222225", "1.0000 SYS", "test");
/*    */       
/* 66 */       System.out.println(content);
/*    */     } catch (Exception e) {
/* 68 */       e.printStackTrace();
/*    */     }
/*    */     try
/*    */     {
/* 72 */       Transaction tx = rpc.pushTransaction(content);
/* 73 */       System.out.println("transacitonId: " + tx.getTransactionId());
/*    */     } catch (ApiException ex) {
/* 75 */       ex.printStackTrace();
/*    */     } catch (Exception e) {
/* 77 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\OfflineTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */