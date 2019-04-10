package io.eblock.eos4j.api.vo.transaction.push;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 import io.eblock.eos4j.api.vo.BaseVo;
 import io.eblock.eos4j.ese.BeanField;
 import java.util.ArrayList;
 import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Tx extends BaseVo {
   @BeanField(order=0)
   private Object expiration;
   @BeanField(order=1)
   private Long ref_block_num;
   @BeanField(order=2)
   private Long ref_block_prefix;
   @BeanField(order=3)
   private Long net_usage_words;
   @BeanField(order=4)
   private Long max_cpu_usage_ms;
   @BeanField(order=5)
   private Long delay_sec;
   @BeanField(order=6)
  private List<String> context_free_actions = new ArrayList();
   @BeanField(order=7)
   private List<TxAction> actions;
   @BeanField(order=8)
   private List<TxExtenstions> transaction_extensions = new ArrayList();
   
   public Object getExpiration()
   {
     return this.expiration;
   }
   
   public void setExpiration(Object expiration) {
     this.expiration = expiration;
   }
   
   public Long getRef_block_num() {
    return this.ref_block_num;
   }
   
   public void setRef_block_num(Long ref_block_num) {
     this.ref_block_num = ref_block_num;
   }
   
   public Long getRef_block_prefix() {
     return this.ref_block_prefix;
   }
   
   public void setRef_block_prefix(Long ref_block_prefix) {
    this.ref_block_prefix = ref_block_prefix;
   }
   
   public Long getNet_usage_words() {
     return this.net_usage_words;
   }
   
   public void setNet_usage_words(Long net_usage_words) {
/*  66 */     this.net_usage_words = net_usage_words;
   }
   
   public Long getMax_cpu_usage_ms() {
/*  70 */     return this.max_cpu_usage_ms;
   }
   
   public void setMax_cpu_usage_ms(Long max_cpu_usage_ms) {
/*  74 */     this.max_cpu_usage_ms = max_cpu_usage_ms;
   }
   
   public Long getDelay_sec() {
/*  78 */     return this.delay_sec;
   }
   
   public void setDelay_sec(Long delay_sec) {
/*  82 */     this.delay_sec = delay_sec;
   }
   
   public List<String> getContext_free_actions() {
/*  86 */     return this.context_free_actions;
   }
   
   public void setContext_free_actions(List<String> context_free_actions) {
/*  90 */     this.context_free_actions = context_free_actions;
   }
   
   public List<TxAction> getActions() {
/*  94 */     return this.actions;
   }
   
   public void setActions(List<TxAction> actions) {
/*  98 */     this.actions = actions;
   }
   
   public List<TxExtenstions> getTransaction_extensions() {
/* 102 */     return this.transaction_extensions;
   }

   public void setTransaction_extensions(List<TxExtenstions> transaction_extensions) {
/* 106 */     this.transaction_extensions = transaction_extensions;
   }
}
