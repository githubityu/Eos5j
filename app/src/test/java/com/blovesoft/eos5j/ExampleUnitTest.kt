package com.blovesoft.eos5j

import io.eblock.eos4j.ecc.EccTool
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        //123455432113
        //5JXyVZQtxpVLJiLq49KEdK1zFvTT4LrxPgYs53Mxq6bkPfXpibG
        //EOS8NxLorDobjL1nNPfA8p2Saqdn3KLfur41Z3UNDQYwotUyz7h3V
        val seedPrivate = EccTool.privateToPublic("5JXyVZQtxpVLJiLq49KEdK1zFvTT4LrxPgYs53Mxq6bkPfXpibG")
        println(seedPrivate)
    }
}
