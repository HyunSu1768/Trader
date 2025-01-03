import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Structure

interface StockLib : Library {
    companion object {
        val INSTANCE: StockLib = Native.load("/library/wmca.dll", StockLib::class.java)
    }

    @Structure.FieldOrder("symbol")
    class C1101InBlock : Structure() {
        @JvmField
        var symbol: ByteArray = ByteArray(10)
    }

    @Structure.FieldOrder("name", "price")
    class C1101OutBlock1 : Structure() {
        @JvmField
        var name: ByteArray = ByteArray(50)
        @JvmField
        var price: Float = 0.0f
    }

    @Structure.FieldOrder("volume")
    class C1101OutBlock2 : Structure() {
        @JvmField
        var volume: IntArray = IntArray(30)
    }

    @Structure.FieldOrder("bidPrice", "askPrice")
    class C1101OutBlock3 : Structure() {
        @JvmField
        var bidPrice: Float = 0.0f
        @JvmField
        var askPrice: Float = 0.0f
    }

    fun getStockInfo(
        input: C1101InBlock,
        out1: C1101OutBlock1,
        out2: C1101OutBlock2,
        out3: C1101OutBlock3
    ): Int
}
