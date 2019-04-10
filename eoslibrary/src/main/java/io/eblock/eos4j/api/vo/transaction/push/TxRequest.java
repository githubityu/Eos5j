package io.eblock.eos4j.api.vo.transaction.push;

import io.eblock.eos4j.api.vo.BaseVo;

public class TxRequest extends BaseVo {
   private String compression;
   private Tx transaction;
   private String[] signatures;

   public TxRequest() {}

   public TxRequest(String compression, Tx transaction, String[] signatures) {
     this.compression = compression;
     this.transaction = transaction;
     this.signatures = signatures;
   }

   public String getCompression() {
     return this.compression;
   }

   public void setCompression(String compression) {
     this.compression = compression;
   }

   public Tx getTransaction() {
     return this.transaction;
   }

   public void setTransaction(Tx transaction) {
     this.transaction = transaction;
   }

   public String[] getSignatures() {
     return this.signatures;
   }

   public void setSignatures(String[] signatures) {
     this.signatures = signatures;
   }
}
