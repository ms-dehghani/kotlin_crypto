package ir.dehghani.kotlincrypto.pojo

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
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

fun currencyItemEmpty(): CurrencyItem {
    return CurrencyItem(0, "", "", JSONObject())
}

fun currencyListParser(jsonArray: Any): List<CurrencyItem> {
    var result = arrayListOf<CurrencyItem>()
    if (jsonArray is JSONArray) {
        result = Gson().fromJson(jsonArray.toString() , object : TypeToken<List<CurrencyItem?>?>() {}.type)
    }
    return result
}