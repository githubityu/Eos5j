 package io.eblock.eos4j.api.vo.transaction.push;
 
 import io.eblock.eos4j.api.vo.BaseVo;
 import io.eblock.eos4j.ese.BeanField;
 
 public class TxActionAuth extends BaseVo{
   @BeanField(order=0)
   private String actor;
   @BeanField(order=1)
   private String permission;
   
   public TxActionAuth() {}
   
   public TxActionAuth(String actor, String permission){
     this.actor = actor;
     this.permission = permission;
   }

   public String getActor(){
     return this.actor;
   }
   
   public void setActor(String actor) {
    this.actor = actor;
   }
   
   public String getPermission() {
     return this.permission;
   }
   
   public void setPermission(String permission) {
     this.permission = permission;
   }
 }
