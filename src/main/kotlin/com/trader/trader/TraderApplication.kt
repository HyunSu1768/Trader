package com.trader.trader

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TraderApplication

fun main(args: Array<String>) {
    runApplication<TraderApplication>(*args)

    val input = StockLib.C1101InBlock().apply {
        "AAPL".toByteArray().copyInto(symbol)
    }

    // 출력 데이터
    val out1 = StockLib.C1101OutBlock1()
    val out2 = StockLib.C1101OutBlock2()
    val out3 = StockLib.C1101OutBlock3()

    // C 함수 호출
    val result = StockLib.INSTANCE.getStockInfo(input, out1, out2, out3)

    if (result == 0) {
        // 결과 출력
        println("종목명: ${String(out1.name).trim { it <= '\u0000' }}")
        println("현재가: ${out1.price}")

        println("최근 30개의 변동 거래량:")
        out2.volume.forEach { print("$it ") }
        println()

        println("매도호가: ${out3.bidPrice}")
        println("매수호가: ${out3.askPrice}")
    } else {
        println("Error occurred, code: $result")
    }
}
