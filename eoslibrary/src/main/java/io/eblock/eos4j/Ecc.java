 package io.eblock.eos4j;
 
 import io.eblock.eos4j.api.vo.transaction.push.TxSign;
 import io.eblock.eos4j.ecc.EccTool;
 import io.eblock.eos4j.ese.Ese;
 import java.util.List;
 
 public class Ecc
 {
   public static String seedPrivate(String seed)
   {
    return EccTool.seedPrivate(seed);
   }

   public static String privateToPublic(String privateKey)
   {
     return EccTool.privateToPublic(privateKey);
   }
 
   public static String sign(String privateKey, String data)
   {
     return EccTool.sign(privateKey, data);
   }

   public static String signTransaction(String privateKey, TxSign sign)
   {
    return EccTool.signTransaction(privateKey, sign);
   }

 
   public static String parseTransferData(String from, String to, String quantity, String memo)
   {
     return Ese.parseTransferData(from, to, quantity, memo);
   }

   public static String parseVoteProducerData(String voter, String proxy, List<String> producers)
   {
     return Ese.parseVoteProducerData(voter, proxy, producers);
   }

   public static String parseAccountData(String creator, String name, String onwer, String active)
   {
     return Ese.parseAccountData(creator, name, onwer, active);
   }

   public static String parseBuyRamData(String payer, String receiver, Long bytes)
   {
    return Ese.parseBuyRamData(payer, receiver, bytes);
   }

   public static String parseBuyRamData(String from, String receiver, String stakeNetQuantity, String stakeCpuQuantity, int transfer)
   {
     return Ese.parseDelegateData(from, receiver, stakeNetQuantity, stakeCpuQuantity, transfer);
   }
 
   public static String parseCloseData(String owner, String symbol)
   {
     return Ese.parseCloseData(owner, symbol);
   }
 }
