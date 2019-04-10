 package io.eblock.eos4j.api.vo.account;

 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 import com.fasterxml.jackson.annotation.JsonProperty;











 @JsonIgnoreProperties(ignoreUnknown=true)
 public class Permission
 {
   @JsonProperty("perm_name")
   private String permName;
   @JsonProperty("parent")
   private String parent;
   @JsonProperty("required_auth")
   private RequiredAuth requiredAuth;

   public String getPermName()
   {
     return this.permName;
   }

   public void setPermName(String permName) {
     this.permName = permName;
   }

   public String getParent() {
     return this.parent;
   }

   public void setParent(String parent) {
     this.parent = parent;
   }

   public RequiredAuth getRequiredAuth() {
     return this.requiredAuth;
   }

   public void setRequiredAuth(RequiredAuth requiredAuth) {
     this.requiredAuth = requiredAuth;
   }

   @Override
   public String toString() {
     return "Permission{" +
             "permName='" + permName + '\'' +
             ", parent='" + parent + '\'' +
             ", requiredAuth=" + requiredAuth +
             '}';
   }
 }

