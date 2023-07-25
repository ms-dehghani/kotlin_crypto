package ir.dehghani.kotlincrypto.pojo

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class CurrencyItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("quote")
    val quote: JSONObject,
) {

    fun getPrice(): Double {
        if (quote.has("USD"))
            return quote.getJSONObject("USD").getDouble("price")
        return 0.0
    }

}

fun currencyItemEmpty() : CurrencyItem {
    return CurrencyItem(0,"","",JSONObject())
}
