package io.eblock.eos4j.api.service;

import io.eblock.eos4j.api.action.Actions;
import io.eblock.eos4j.api.vo.AccountNameModle;
import io.eblock.eos4j.api.vo.Block;
import io.eblock.eos4j.api.vo.ChainInfo;
import io.eblock.eos4j.api.vo.JsonToBeanParam;
import io.eblock.eos4j.api.vo.JsonToBeanResponse;
import io.eblock.eos4j.api.vo.TableRows;
import io.eblock.eos4j.api.vo.TableRowsByScopeReq;
import io.eblock.eos4j.api.vo.TableRowsReq;
import io.eblock.eos4j.api.vo.account.Account;
import io.eblock.eos4j.api.vo.transaction.Transaction;
import io.eblock.eos4j.api.vo.transaction.push.TxRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public abstract interface RpcService
{
  @GET("/v1/chain/get_info")
  public abstract Call<ChainInfo> getChainInfo();

  @POST("/v1/chain/get_block")
  public abstract Call<Block> getBlock(@Body Map<String, String> paramMap);

  @POST("/v1/chain/get_account")
  public abstract Call<Account> getAccount(@Body Map<String, String> paramMap);

  @POST("/v1/chain/push_transaction")
  public abstract Call<Transaction> pushTransaction(@Body TxRequest paramTxRequest);

  @POST("/v1/chain/get_table_rows")
  public abstract Call<TableRows> getTableRows(@Body TableRowsReq paramTableRowsReq);

  @POST("/v1/chain/get_table_by_scope")
  public abstract Call<TableRows> getTableRowsByScope(@Body TableRowsByScopeReq paramTableRowsReq);

  //获取余额
  @POST("/v1/chain/get_currency_balance")
  public abstract Call<List<String>> getCurrencyBalance(@Body Map<String, String> requestFields);

  //获取账户列表
  @POST("/v1/history/get_key_accounts")
  public abstract Call<AccountNameModle> getKeyAccounts(@Body Map<String, String> paramMap);

  //获取转账记录
  @POST("/v1/history/get_actions")
  public abstract Call<Actions> getActions(@Body Map<String, Object> paramMap);

  @POST("/v1/chain/abi_json_to_bin")
  public abstract Call<JsonToBeanResponse> getAbiJsonToBean(@Body JsonToBeanParam paramJsonToBeanReq);
}
