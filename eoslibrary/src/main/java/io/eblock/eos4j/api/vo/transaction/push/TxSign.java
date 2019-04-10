package io.eblock.eos4j.api.vo.transaction.push;
 
import io.eblock.eos4j.api.vo.BaseVo;
import io.eblock.eos4j.ese.BeanField;
 
public class TxSign extends BaseVo {
   @BeanField(order=0)
   private String chain_id;
   @BeanField(order=1)
   private Tx transaction;

   public TxSign() {}
   
   public TxSign(String chain_id, Tx transaction) {
     this.chain_id = chain_id;
     this.transaction = transaction;
   }

   public String getChain_id() {
     return this.chain_id;
   }
   
   public void setChain_id(String chain_id) {
     this.chain_id = chain_id;
   }
   
   public Tx getTransaction() {
     return this.transaction;
   }
   
   public void setTransaction(Tx transaction) {
     this.transaction = transaction;
   }
}
