# Eos5j
操作eos的库，让操作更简单
[![](https://jitpack.io/v/githubityu/Eos5j.svg)](https://jitpack.io/#githubityu/Eos5j)
## 目录
-[功能特点](#功能特点)<br>
-[集成方式](#集成方式)<br>
-[常见错误](#常见错误)<br>
# 功能特点
* 1.创建私钥公钥
* 2.创建账号
* 3.转账
* 4.购买内存
* 5.卖内存
* 6.抵押赎回cpu
* 7.抵押赎回net
* 8.查询用户信息
* 9.查询余额
* 10.查询历史记录

重要的事情记得添加权限
```
 <uses-permission android:name="android.permission.INTERNET"/>
```
## 集成方式

方式一 compile引入

```
dependencies {
	        implementation 'com.github.githubityu:Eos5j:Tag'
}
```
项目根目录build.gradle加入

```
allprojects {
   repositories {
      jcenter()
      maven { url 'https://jitpack.io' }
   }
}
```
## 使用方式
```
 var rpc = Rpc(URL)
            //val chainInfo = rpc.chainInfo
           // Log.e("data", chainInfo.toString())

            //查询账户信息
           //val currencyBalance = rpc.getAccount("123455432113")
           // Log.e("data", currencyBalance.toString())
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
```
## 常见错误
## 感谢
[espritblock](https://github.com/espritblock) 
