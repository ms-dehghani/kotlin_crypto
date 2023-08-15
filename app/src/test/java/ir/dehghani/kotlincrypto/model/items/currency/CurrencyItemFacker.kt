package ir.dehghani.kotlincrypto.model.items.currency

import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import net.datafaker.Faker
import org.json.JSONObject

var faker = Faker()

fun getFakeCurrency() = CurrencyItem(faker.number().positive().toString(), faker.name().firstName(), faker.name().lastName(), JSONObject())


fun getFakeCurrencyList(): List<CurrencyItem> {
    var list = arrayListOf<CurrencyItem>()
    for (i in 1..5)
        list.add(getFakeCurrency())
    return list
}