package com.blovesoft.eos5j

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.blovesoft.eos5j.Constant.URL
import io.eblock.eos4j.Rpc
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            var rpc = Rpc(URL)
//            val chainInfo = rpc.chainInfo
//            Log.e("data", chainInfo.toString())

            //查询账户信息
           val currencyBalance = rpc.getAccount("123455432113")
            Log.e("data", currencyBalance.toString())
            //创建账号
//            try {
//                val  createAccount = rpc.createAccount(
//                    PRIKEY,
//                    CREATE_ACCOUNT,
//                    "123455432113",
//                    "EOS8NxLorDobjL1nNPfA8p2Saqdn3KLfur41Z3UNDQYwotUyz7h3V",
//                    "EOS8NxLorDobjL1nNPfA8p2Saqdn3KLfur41Z3UNDQYwotUyz7h3V",
//                    4 * 1024,
//                    "0.1",
//                    "0.2",
//                    0L
//                )
//                Log.e("data", createAccount.transactionId)
//            } catch (e: Exception) {
//                Log.e("data", e.message)
//            }

            //转账
            //val transfer = rpc.transfer(PRIKEY, CREATE_SYS, CREATE_ACCOUNT, "123455432113", "50", "54112")

            //Log.e("data", transfer.transactionId)
            //购买内存
//            val buyRam =
//                rpc.buyRam("5JXyVZQtxpVLJiLq49KEdK1zFvTT4LrxPgYs53Mxq6bkPfXpibG", "123455432113", "123455432113", 8000L)
//            Log.e("data", buyRam.transactionId)
            //卖内存
//            val sellRam =
//                rpc.sellRam("5JXyVZQtxpVLJiLq49KEdK1zFvTT4LrxPgYs53Mxq6bkPfXpibG", "123455432113",  1000L)
//            Log.e("data", sellRam.transactionId)
            //抵押cpu或者net
//            val delegatebw = rpc.delegatebw(
//                "5JXyVZQtxpVLJiLq49KEdK1zFvTT4LrxPgYs53Mxq6bkPfXpibG",
//                "123455432113",
//                "123455432113",
//                "0.05",
//                "0.05",
//                0L
//            )
//            Log.e("data", delegatebw.transactionId)

            //赎回cpu 或者net
//            val undelegatebw = rpc.undelegatebw(
//                "5JXyVZQtxpVLJiLq49KEdK1zFvTT4LrxPgYs53Mxq6bkPfXpibG",
//                "123455432113",
//                "123455432113",
//                "0.0001",
//                "0.0001"
//            )
//            Log.e("data", undelegatebw.transactionId)
        }

    }
}
