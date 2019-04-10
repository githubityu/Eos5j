/*     */ package io.eblock.eos4j;
/*     */ 
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import io.eblock.eos4j.api.vo.SignParam;
/*     */ import io.eblock.eos4j.api.vo.transaction.push.Tx;
/*     */ import io.eblock.eos4j.api.vo.transaction.push.TxAction;
/*     */ import io.eblock.eos4j.api.vo.transaction.push.TxRequest;
/*     */ import io.eblock.eos4j.api.vo.transaction.push.TxSign;
/*     */ import io.eblock.eos4j.ese.Action;
/*     */ import io.eblock.eos4j.ese.DataParam;
/*     */ import io.eblock.eos4j.ese.DataType;
/*     */ import io.eblock.eos4j.ese.Ese;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OfflineSign
/*     */ {
/*  29 */   SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
/*     */   
/*     */   public OfflineSign() {
/*  32 */     this.dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String pushTransaction(String compression, Tx pushTransaction, String[] signatures)
/*     */     throws Exception
/*     */   {
/*  44 */     ObjectMapper mapper = new ObjectMapper();
/*  45 */     String mapJakcson = mapper.writeValueAsString(new TxRequest(compression, pushTransaction, signatures));
/*  46 */     return mapJakcson;
/*     */   }
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
/*     */   public String transfer(SignParam signParam, String pk, String contractAccount, String from, String to, String quantity, String memo)
/*     */     throws Exception
/*     */   {
/*  64 */     Tx tx = new Tx();
/*  65 */     tx.setExpiration(Long.valueOf(signParam.getHeadBlockTime().getTime() / 1000L + signParam.getExp().longValue()));
/*  66 */     tx.setRef_block_num(signParam.getLastIrreversibleBlockNum());
/*  67 */     tx.setRef_block_prefix(signParam.getRefBlockPrefix());
/*  68 */     tx.setNet_usage_words(Long.valueOf(0L));
/*  69 */     tx.setMax_cpu_usage_ms(Long.valueOf(0L));
/*  70 */     tx.setDelay_sec(Long.valueOf(0L));
/*     */     
/*  72 */     List<TxAction> actions = new ArrayList();
/*     */     
/*  74 */     Map<String, Object> dataMap = new LinkedHashMap();
/*  75 */     dataMap.put("from", from);
/*  76 */     dataMap.put("to", to);
/*  77 */     dataMap.put("quantity", new DataParam(quantity, DataType.asset, Action.transfer).getValue());
/*  78 */     dataMap.put("memo", memo);
/*     */     
/*  80 */     TxAction action = new TxAction(from, contractAccount, "transfer", dataMap);
/*  81 */     actions.add(action);
/*  82 */     tx.setActions(actions);
/*     */     
/*  84 */     String sign = Ecc.signTransaction(pk, new TxSign(signParam.getChainId(), tx));
/*     */     
/*  86 */     String data = Ecc.parseTransferData(from, to, quantity, memo);
/*     */     
/*  88 */     action.setData(data);
/*     */     
/*  90 */     tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));
/*  91 */     return pushTransaction("none", tx, new String[] { sign });
/*     */   }
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
/*     */   public String createAccount(SignParam signParam, String pk, String creator, String newAccount, String owner, String active, Long buyRam)
/*     */     throws Exception
/*     */   {
/* 109 */     Tx tx = new Tx();
/* 110 */     tx.setExpiration(Long.valueOf(signParam.getHeadBlockTime().getTime() / 1000L + signParam.getExp().longValue()));
/* 111 */     tx.setRef_block_num(signParam.getLastIrreversibleBlockNum());
/* 112 */     tx.setRef_block_prefix(signParam.getRefBlockPrefix());
/* 113 */     tx.setNet_usage_words(Long.valueOf(0L));
/* 114 */     tx.setMax_cpu_usage_ms(Long.valueOf(0L));
/* 115 */     tx.setDelay_sec(Long.valueOf(0L));
/*     */     
/* 117 */     List<TxAction> actions = new ArrayList();
/* 118 */     tx.setActions(actions);
/*     */     
/* 120 */     Map<String, Object> createMap = new LinkedHashMap();
/* 121 */     createMap.put("creator", creator);
/* 122 */     createMap.put("name", newAccount);
/* 123 */     createMap.put("owner", owner);
/* 124 */     createMap.put("active", active);
/* 125 */     TxAction createAction = new TxAction(creator, "eosio", "newaccount", createMap);
/* 126 */     actions.add(createAction);
/*     */     
/* 128 */     Map<String, Object> buyMap = new LinkedHashMap();
/* 129 */     buyMap.put("payer", creator);
/* 130 */     buyMap.put("receiver", newAccount);
/* 131 */     buyMap.put("bytes", buyRam);
/* 132 */     TxAction buyAction = new TxAction(creator, "eosio", "buyrambytes", buyMap);
/* 133 */     actions.add(buyAction);
/*     */     
/* 135 */     String sign = Ecc.signTransaction(pk, new TxSign(signParam.getChainId(), tx));
/*     */     
/* 137 */     String accountData = Ese.parseAccountData(creator, newAccount, owner, active);
/* 138 */     createAction.setData(accountData);
/*     */     
/* 140 */     String ramData = Ese.parseBuyRamData(creator, newAccount, buyRam);
/* 141 */     buyAction.setData(ramData);
/*     */     
/* 143 */     tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));
/* 144 */     return pushTransaction("none", tx, new String[] { sign });
/*     */   }
/*     */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\OfflineSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */