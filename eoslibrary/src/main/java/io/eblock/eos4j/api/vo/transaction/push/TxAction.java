 package io.eblock.eos4j.api.vo.transaction.push;
 
 import io.eblock.eos4j.api.vo.BaseVo;
 import io.eblock.eos4j.ese.BeanField;
 import java.util.List;
 
public class TxAction extends BaseVo {
       @BeanField(order=0)
       private String account;
       @BeanField(order=1)
       private String name;
       @BeanField(order=2)
       private List<TxActionAuth> authorization;
       @BeanField(order=3)
       private Object data;

       public TxAction() {}

       public TxAction(String actor, String account, String name, Object data) {
         this.account = account;
         this.name = name;
         this.data = data;
         this.authorization = new java.util.ArrayList();
         this.authorization.add(new TxActionAuth(actor, "active"));
       }

       public String getAccount() {
         return this.account;
       }

       public void setAccount(String account) {
         this.account = account;
       }

       public String getName() {
         return this.name;
       }

       public void setName(String name) {
         this.name = name;
       }

       public List<TxActionAuth> getAuthorization() {
         return this.authorization;
       }

       public void setAuthorization(List<TxActionAuth> authorization) {
        this.authorization = authorization;
       }

       public Object getData() {
         return this.data;
       }

       public void setData(Object data) {
         this.data = data;
       }
}
