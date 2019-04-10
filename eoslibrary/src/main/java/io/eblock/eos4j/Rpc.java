//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.eblock.eos4j;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.eblock.eos4j.api.action.Actions;
import io.eblock.eos4j.api.service.RpcService;
import io.eblock.eos4j.api.utils.Generator;
import io.eblock.eos4j.api.vo.AccountNameModle;
import io.eblock.eos4j.api.vo.Block;
import io.eblock.eos4j.api.vo.ChainInfo;
import io.eblock.eos4j.api.vo.JsonToBeanParam;
import io.eblock.eos4j.api.vo.JsonToBeanResponse;
import io.eblock.eos4j.api.vo.SignParam;
import io.eblock.eos4j.api.vo.TableRows;
import io.eblock.eos4j.api.vo.TableRowsByScopeReq;
import io.eblock.eos4j.api.vo.TableRowsReq;
import io.eblock.eos4j.api.vo.account.Account;
import io.eblock.eos4j.api.vo.transaction.Transaction;
import io.eblock.eos4j.api.vo.transaction.push.Tx;
import io.eblock.eos4j.api.vo.transaction.push.TxAction;
import io.eblock.eos4j.api.vo.transaction.push.TxRequest;
import io.eblock.eos4j.api.vo.transaction.push.TxSign;
import io.eblock.eos4j.ese.Action;
import io.eblock.eos4j.ese.DataParam;
import io.eblock.eos4j.ese.DataType;
import io.eblock.eos4j.ese.Ese;
import io.eblock.eos4j.utils.ObjectUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Rpc {
    private final RpcService rpcService;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public Rpc(String baseUrl) {
        //获取合适的节点链接
        this.dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.rpcService = (RpcService)Generator.createService(RpcService.class, baseUrl);
    }

    public ChainInfo getChainInfo() {
        return (ChainInfo)Generator.executeSync(this.rpcService.getChainInfo());
    }

    public Block getBlock(String blockNumberOrId) {
        return (Block)Generator.executeSync(this.rpcService.getBlock(Collections.singletonMap("block_num_or_id", blockNumberOrId)));
    }

    public Account getAccount(String account) {
        return (Account)Generator.executeSync(this.rpcService.getAccount(Collections.singletonMap("account_name", account)));
    }

    public AccountNameModle getKeyAccounts(String public_key) {
        return (AccountNameModle)Generator.executeSync(this.rpcService.getKeyAccounts(Collections.singletonMap("public_key", public_key)));
    }

    public List<String> getCurrencyBalance(String account) {
        Map<String, String> requestFields = new HashMap<>();
//    requestFields.put("code","emttokenbank");
//    requestFields.put("account",account);
//    requestFields.put("symbol","EMT");

        requestFields.put("code","eosio.token");
        requestFields.put("account",account);
        //requestFields.put("symbol","EMT");
        //requestFields.put("filter-on ","eosio:newaccount");
        return Generator.executeSync(rpcService.getCurrencyBalance(requestFields));
    }

    public TableRows getTableRows(TableRowsReq req) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String mapJakcson = mapper.writeValueAsString(req);
//            System.out.println(mapJakcson);
//        }catch (Exception e){}

        return (TableRows)Generator.executeSync(this.rpcService.getTableRows(req));
    }

    public TableRows getTableRowsByScope(TableRowsByScopeReq req) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String mapJakcson = mapper.writeValueAsString(req);
            System.out.println(mapJakcson);
        }catch (Exception e){}

        return (TableRows)Generator.executeSync(this.rpcService.getTableRowsByScope(req));
    }

    public Transaction pushTransaction(String compression, Tx pushTransaction, String[] signatures) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String mapJakcson = mapper.writeValueAsString(new TxRequest(compression, pushTransaction, signatures));
//        System.out.println(mapJakcson);
        Log.e("Log","==="+mapJakcson);
        return (Transaction)Generator.executeSync(this.rpcService.pushTransaction(new TxRequest(compression, pushTransaction, signatures)));
    }

    public Transaction pushTransaction(String tx) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TxRequest txObj = (TxRequest)mapper.readValue(tx, TxRequest.class);
        return (Transaction)Generator.executeSync(this.rpcService.pushTransaction(txObj));
    }

    public SignParam getOfflineSignParams(Long exp) {
        SignParam params = new SignParam();
        ChainInfo info = this.getChainInfo();
        Block block = this.getBlock(info.getLastIrreversibleBlockNum().toString());
        params.setChainId(info.getChainId());
        params.setHeadBlockTime(info.getHeadBlockTime());
        params.setLastIrreversibleBlockNum(info.getLastIrreversibleBlockNum());
        params.setRefBlockPrefix(block.getRefBlockPrefix());
        params.setExp(exp);
        return params;
    }

    public Transaction transfer(String pk, String contractAccount, String from, String to, String quantity, String memo) throws Exception {
        quantity =  String.format("%.4f EOS",Float.parseFloat(quantity));
        ChainInfo info = this.getChainInfo();
        Block block = this.getBlock(info.getLastIrreversibleBlockNum().toString());
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000L + 60L);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0L);
        tx.setMax_cpu_usage_ms(0L);
        tx.setDelay_sec(0L);
        List<TxAction> actions = new ArrayList();
        Map<String, Object> dataMap = new LinkedHashMap();
        dataMap.put("from", from);
        dataMap.put("to", to);
        dataMap.put("quantity", (new DataParam(quantity, DataType.asset, Action.transfer)).getValue());
        dataMap.put("memo", memo);
        TxAction action = new TxAction(from, contractAccount, "transfer", dataMap);
        actions.add(action);
        tx.setActions(actions);
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));
        String data = Ecc.parseTransferData(from, to, quantity, memo);
        action.setData(data);

        Date date =new Date(1000L * Long.parseLong(tx.getExpiration().toString()));
        tx.setExpiration(this.dateFormatter.format(date));
//        tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));
        return this.pushTransaction("none", tx, new String[]{sign});
    }

    public Transaction createAccount(String pk, String creator, String newAccount, String owner, String active, Long buyRam) throws Exception {
        ChainInfo info = this.getChainInfo();
        Block block = this.getBlock(info.getLastIrreversibleBlockNum().toString());
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000L + 60L);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0L);
        tx.setMax_cpu_usage_ms(0L);
        tx.setDelay_sec(0L);
        List<TxAction> actions = new ArrayList();
        tx.setActions(actions);
        Map<String, Object> createMap = new LinkedHashMap();
        createMap.put("creator", creator);
        createMap.put("name", newAccount);
        createMap.put("owner", owner);
        createMap.put("active", active);
        TxAction createAction = new TxAction(creator, "eosio", "newaccount", createMap);
        actions.add(createAction);
        Map<String, Object> buyMap = new LinkedHashMap();
        buyMap.put("payer", creator);
        buyMap.put("receiver", newAccount);
        buyMap.put("bytes", buyRam);
        TxAction buyAction = new TxAction(creator, "eosio", "buyrambytes", buyMap);
        actions.add(buyAction);
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));
        String accountData = Ese.parseAccountData(creator, newAccount, owner, active);
        createAction.setData(accountData);
        String ramData = Ese.parseBuyRamData(creator, newAccount, buyRam);
        buyAction.setData(ramData);
        tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));
        return this.pushTransaction("none", tx, new String[]{sign});
    }

    public Transaction createAccount(String pk, String creator, String newAccount, String owner, String active, Long buyRam, String stakeNetQuantity, String stakeCpuQuantity, Long transfer) throws Exception {
        stakeNetQuantity =  String.format("%.4f EOS",Float.parseFloat(stakeNetQuantity));
        stakeCpuQuantity =  String.format("%.4f EOS",Float.parseFloat(stakeCpuQuantity));
        ChainInfo info = this.getChainInfo();
        Block block = this.getBlock(info.getLastIrreversibleBlockNum().toString());
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000L + 60L);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0L);
        tx.setMax_cpu_usage_ms(0L);
        tx.setDelay_sec(0L);
        List<TxAction> actions = new ArrayList();
        tx.setActions(actions);
        Map<String, Object> createMap = new LinkedHashMap();
        createMap.put("creator", creator);
        createMap.put("name", newAccount);
        createMap.put("owner", owner);
        createMap.put("active", active);
        TxAction createAction = new TxAction(creator, "eosio", "newaccount", createMap);
        actions.add(createAction);
        Map<String, Object> buyMap = new LinkedHashMap();
        buyMap.put("payer", creator);
        buyMap.put("receiver", newAccount);
        buyMap.put("bytes", buyRam);
        TxAction buyAction = new TxAction(creator, "eosio", "buyrambytes", buyMap);
        actions.add(buyAction);
        Map<String, Object> delMap = new LinkedHashMap();
        delMap.put("from", creator);
        delMap.put("receiver", newAccount);
        delMap.put("stake_net_quantity", (new DataParam(stakeNetQuantity, DataType.asset, Action.delegate)).getValue());
        delMap.put("stake_cpu_quantity", (new DataParam(stakeCpuQuantity, DataType.asset, Action.delegate)).getValue());
        delMap.put("transfer", transfer);
        TxAction delAction = new TxAction(creator, "eosio", "delegatebw", delMap);
        actions.add(delAction);

        ObjectUtils.isUint64 = false;
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));
        String accountData = Ese.parseAccountData(creator, newAccount, owner, active);
        createAction.setData(accountData);
        String ramData = Ese.parseBuyRamData(creator, newAccount, buyRam);
        buyAction.setData(ramData);
        String delData = Ese.parseDelegateData(creator, newAccount, stakeNetQuantity, stakeCpuQuantity, transfer.intValue());
        delAction.setData(delData);
        tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));
        return this.pushTransaction("none", tx, new String[]{sign});
    }


    public Transaction createAccount2(String pk, String creator, String newAccount, String owner, String active,
                                     String buyRam, String stakeNetQuantity, String stakeCpuQuantity, Long transfer) throws Exception {
        // get chain info
        ChainInfo info = getChainInfo();
        // get block info
        Block block = getBlock(info.getLastIrreversibleBlockNum().toString());
        // tx
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000 + 60);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0l);
        tx.setMax_cpu_usage_ms(0l);
        tx.setDelay_sec(0l);
        // actions
        List<TxAction> actions = new ArrayList<>();
        tx.setActions(actions);
        // create
        Map<String, Object> createMap = new LinkedHashMap<>();
        createMap.put("creator", creator);
        createMap.put("name", newAccount);
        createMap.put("owner", owner);
        createMap.put("active", active);
        TxAction createAction = new TxAction(creator, "eosio", "newaccount", createMap);
        actions.add(createAction);
        // buyrap
        Map<String, Object> buyMap = new LinkedHashMap<>();
        buyMap.put("payer", creator);
        buyMap.put("receiver", newAccount);
        buyMap.put("bytes",buyRam);
        TxAction buyAction = new TxAction(creator, "eosio", "buyrambytes", buyMap);
        actions.add(buyAction);
        // buyrap
        Map<String, Object> delMap = new LinkedHashMap<>();
        delMap.put("from", creator);
        delMap.put("receiver", newAccount);
        delMap.put("stake_net_quantity", new DataParam(stakeNetQuantity, DataType.asset, Action.delegate).getValue());
        delMap.put("stake_cpu_quantity", new DataParam(stakeCpuQuantity, DataType.asset, Action.delegate).getValue());
        delMap.put("transfer", transfer);
        TxAction delAction = new TxAction(creator, "eosio", "delegatebw", delMap);
        actions.add(delAction);
        // sgin
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));
        // data parse
        String accountData = Ese.parseAccountData(creator, newAccount, owner, active);
        createAction.setData(accountData);
        // data parse
        String ramData = Ese.parseBuyRamData(creator, newAccount, Long.valueOf(buyRam));
        buyAction.setData(ramData);
        // data parse
        String delData = Ese.parseDelegateData(creator, newAccount, stakeNetQuantity, stakeCpuQuantity, transfer.intValue());
        delAction.setData(delData);
        // reset expiration
        tx.setExpiration(dateFormatter.format(new Date(1000 * Long.parseLong(tx.getExpiration().toString()))));
        return pushTransaction("none", tx, new String[]{sign});
    }

    public Transaction voteproducer(String pk, String voter, String proxy, List<String> producers) throws Exception {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String h1, String h2) {
                return h2.compareTo(h1);
            }
        };
        producers.sort(comparator.reversed());
        ChainInfo info = this.getChainInfo();
        Block block = this.getBlock(info.getLastIrreversibleBlockNum().toString());
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000L + 60L);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0L);
        tx.setMax_cpu_usage_ms(0L);
        tx.setDelay_sec(0L);
        List<TxAction> actions = new ArrayList();
        Map<String, Object> dataMap = new LinkedHashMap();
        dataMap.put("voter", voter);
        dataMap.put("proxy", proxy);
        dataMap.put("producers", producers);
        TxAction action = new TxAction(voter, "eosio", "voteproducer", dataMap);
        actions.add(action);
        tx.setActions(actions);
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));
        String data = Ecc.parseVoteProducerData(voter, proxy, producers);
        action.setData(data);
        tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));
        return this.pushTransaction("none", tx, new String[]{sign});
    }

    public Transaction close(String pk, String contract, String owner, String symbol) throws Exception {
        ChainInfo info = this.getChainInfo();
        Block block = this.getBlock(info.getLastIrreversibleBlockNum().toString());
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000L + 60L);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0L);
        tx.setMax_cpu_usage_ms(0L);
        tx.setDelay_sec(0L);
        List<TxAction> actions = new ArrayList();
        Map<String, Object> dataMap = new LinkedHashMap();
        dataMap.put("close-owner", owner);
        dataMap.put("close-symbol", (new DataParam(symbol, DataType.symbol, Action.close)).getValue());
        TxAction action = new TxAction(owner, contract, "close", dataMap);
        actions.add(action);
        tx.setActions(actions);
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));
        String data = Ecc.parseCloseData(owner, symbol);
        action.setData(data);
        tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));
        return this.pushTransaction("none", tx, new String[]{sign});
    }


    /**
     * 买内存ram
     * @param pk
     * 私钥
     * @param payer
     * 付款方账户名，与pk是一对
     * @param receiver
     * 得到内存账户
     * @param buyRam
     * 内存数量,单位字节byte
     * @return
     * @throws Exception
     */
    public Transaction buyRam(String pk, String payer, String receiver, Long buyRam) throws Exception {
        // get chain info
        ChainInfo info = getChainInfo();
        Block block = getBlock(info.getLastIrreversibleBlockNum().toString());
        // tx
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000 + 60);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0l);
        tx.setMax_cpu_usage_ms(0l);
        tx.setDelay_sec(0l);
        // actions
        List<TxAction> actions = new ArrayList<>();
        tx.setActions(actions);
        // buyrap
        Map<String, Object> buyMap = new LinkedHashMap<>();
        buyMap.put("payer", payer);
        buyMap.put("receiver", receiver);
        buyMap.put("bytes", buyRam);
        TxAction buyAction = new TxAction(payer, "eosio", "buyrambytes", buyMap);
        actions.add(buyAction);
        ObjectUtils.isUint64 = false;
        // // sgin
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));
        // data parse
        String ramData = Ese.parseBuyRamData(payer, receiver, buyRam);
        buyAction.setData(ramData);
        // reset expiration
        tx.setExpiration(dateFormatter.format(new Date(1000 * Long.parseLong(tx.getExpiration().toString()))));
        return pushTransaction("none", tx, new String[] { sign });
    }

    /**
     * 质押CPU/NET
     * @param pk
     * @param payer
     * @param receiver
     * @param stakeNetQuantity
     * @param stakeCpuQuantity
     * @param transfer
     * @return
     * @throws Exception
     */
    public Transaction delegatebw(String pk, String payer, String receiver, String stakeNetQuantity, String stakeCpuQuantity, Long transfer) throws Exception {
        // get chain info
        stakeNetQuantity =   String.format("%.4f EOS",Float.parseFloat(stakeNetQuantity));
        stakeCpuQuantity =   String.format("%.4f EOS",Float.parseFloat(stakeCpuQuantity));
        ChainInfo info = getChainInfo();
        Block block = getBlock(info.getLastIrreversibleBlockNum().toString());
        //定义一个交易信息
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000 + 60);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0l);
        tx.setMax_cpu_usage_ms(0l);
        tx.setDelay_sec(0l);
        // actions
        List<TxAction> actions = new ArrayList<>();
        tx.setActions(actions);

        //添加一个事件
        Map<String, Object> delegatebw = new LinkedHashMap();
        delegatebw.put("from", payer);
        delegatebw.put("receiver", receiver);
        delegatebw.put("stake_net_quantity", (new DataParam(stakeNetQuantity, DataType.asset, Action.delegate)).getValue());
        delegatebw.put("stake_cpu_quantity", (new DataParam(stakeCpuQuantity, DataType.asset, Action.delegate)).getValue());
        delegatebw.put("transfer", transfer);
        TxAction delAction = new TxAction(payer, "eosio", "delegatebw", delegatebw);
        actions.add(delAction);

        //签名该交易
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));

        //解析数据
        String delData = Ese.parseDelegateData(payer, receiver, stakeNetQuantity, stakeCpuQuantity, transfer.intValue());
        delAction.setData(delData);

        //设置过期时间
        tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));

        //push 到链上
        return this.pushTransaction("none", tx, new String[]{sign});

    }

    /**
     * 赎回CPU/NET
     * @param pk
     * @param payer
     * @param receiver
     * @param stakeNetQuantity
     * @param stakeCpuQuantity
     * @return
     * @throws Exception
     */
    public Transaction undelegatebw(String pk, String payer, String receiver, String stakeNetQuantity, String stakeCpuQuantity) throws Exception {
        // get chain info
        stakeNetQuantity =   String.format("%.4f EOS",Float.parseFloat(stakeNetQuantity));
        stakeCpuQuantity =   String.format("%.4f EOS",Float.parseFloat(stakeCpuQuantity));
        ChainInfo info = getChainInfo();
        Block block = getBlock(info.getLastIrreversibleBlockNum().toString());
        //定义一个交易信息
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000 + 60);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0l);
        tx.setMax_cpu_usage_ms(0l);
        tx.setDelay_sec(0l);
        // actions
        List<TxAction> actions = new ArrayList<>();
        tx.setActions(actions);

        //添加一个事件
        Map<String, Object> undelegatebw = new LinkedHashMap();
        undelegatebw.put("from", payer);
        undelegatebw.put("receiver", receiver);
        undelegatebw.put("unstake_net_quantity", (new DataParam(stakeNetQuantity, DataType.asset, Action.undelegatebw)).getValue());
        undelegatebw.put("unstake_cpu_quantity", (new DataParam(stakeCpuQuantity, DataType.asset, Action.undelegatebw)).getValue());
        TxAction delAction = new TxAction(payer, "eosio", "undelegatebw", undelegatebw);
        actions.add(delAction);
        //签名该交易
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));

        //解析数据
        String delData = Ese.parseunDelegateData(payer, receiver, stakeNetQuantity, stakeCpuQuantity);
        delAction.setData(delData);

        //设置过期时间
        tx.setExpiration(this.dateFormatter.format(new Date(1000L * Long.parseLong(tx.getExpiration().toString()))));

        //push 到链上
        return this.pushTransaction("none", tx, new String[]{sign});

    }

    /**
     * 卖内存ram
     * @param pk
     * @param seller
     * @param sellRam
     * @return
     * @throws Exception
     */
    public Transaction sellRam(String pk, String seller, Long sellRam) throws Exception {
        // get chain info
        ChainInfo info = getChainInfo();
        Block block = getBlock(info.getLastIrreversibleBlockNum().toString());
        // tx
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000 + 60);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0l);
        tx.setMax_cpu_usage_ms(0l);
        tx.setDelay_sec(0l);
        // actions
        List<TxAction> actions = new ArrayList<>();
        tx.setActions(actions);
        // buyrap
        Map<String, Object> buyMap = new LinkedHashMap<>();
        buyMap.put("receiver", seller);
        buyMap.put("bytes", sellRam);
        TxAction buyAction = new TxAction(seller, "eosio", "sellram", buyMap);
        actions.add(buyAction);
        ObjectUtils.isUint64 = true;
        // // sgin
        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));
        // data parse
        String ramData = Ese.parseSellRamData(seller, sellRam);
        buyAction.setData(ramData);
        // reset expiration
        tx.setExpiration(dateFormatter.format(new Date(1000 * Long.parseLong(tx.getExpiration().toString()))));
        return pushTransaction("none", tx, new String[] { sign });
    }

    /**
     * 获取转账记录
     * @param
     * @return
     */
    public Actions getTransferRecords(int page,int offset,String account_name) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("pos", page);
        map.put("offset",offset);
        map.put("account_name",account_name);
        return (Actions)Generator.executeSync(this.rpcService.getActions(map));
    }

    public Transaction linkAuth(String pk, String account, String type) throws Exception {
//        lihelihemail active '{"threshold": 1,"keys": [],"accounts": [{"permission":{"actor":"555555555555","permission":"eosio.code"},"weight":1}]}' owner
//        {"account":"lihelihemail","code":"eosio","type":"555555555555","requirement":"active"}

        ChainInfo info = this.getChainInfo();
        Block block = this.getBlock(info.getLastIrreversibleBlockNum().toString());
        Tx tx = new Tx();
        tx.setExpiration(info.getHeadBlockTime().getTime() / 1000L + 60L);
        tx.setRef_block_num(info.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(block.getRefBlockPrefix());
        tx.setNet_usage_words(0L);
        tx.setMax_cpu_usage_ms(0L);
        tx.setDelay_sec(0L);
        List<TxAction> actions = new ArrayList();

        String permissionData = Ese.parseLinkAuth(account, "eosio", type, "active");
        TxAction action = new TxAction(account, "eosio", "updateauth", permissionData);
        actions.add(action);
        tx.setActions(actions);

        String sign = Ecc.signTransaction(pk, new TxSign(info.getChainId(), tx));
        Date date = new Date(1000L * Long.parseLong(tx.getExpiration().toString()));
        tx.setExpiration(this.dateFormatter.format(date));
        return this.pushTransaction("none", tx, new String[]{sign});
    }

    public JsonToBeanResponse getAbiJsonToBean(JsonToBeanParam req) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String mapJakcson = mapper.writeValueAsString(req);
            System.out.println(mapJakcson);
        }catch (Exception e){}

        return (JsonToBeanResponse)Generator.executeSync(this.rpcService.getAbiJsonToBean(req));
    }
}
