package ir.dehghani.kotlincrypto.model.repository.api.utility

import ir.dehghani.kotlincrypto.BuildConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebserviceCaller @Inject constructor(private val client: OkHttpClient) {

    fun call(url: String, requestBody: String, requestType: RequestTypeEnum): WebserviceResponseModel<Any> {
        val requestBuilder = Request.Builder()
            .addHeader("content-type", "application/json; charset=utf-8")
            .addHeader("accept", "application/json")
            .addHeader("X-CMC_PRO_API_KEY", BuildConfig.API_KEY)
            .url(url)

        when (requestType) {
            RequestTypeEnum.Post -> {
                val body = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), requestBody);
                requestBuilder.post(body = body)
            }

            else -> {}
        }
        val response = client.newCall(requestBuilder.build()).execute()
        return if (response.isSuccessful) {
            var data = response.body?.string()
//            try {
//                val jsonArray = JSONArray()
//                val jObj = JSONObject("{ \"data\":" + JSONObject(data).getJSONArray("data").toString() + "}")
//                jsonArray.put(jObj)
//                data = jsonArray.toString();
//            } catch (_: Exception) {
//            }
            WebserviceResponseModel(true, response.message, JSONObject(data.toString()).get("data"))
        } else {
            WebserviceResponseModel(false, response.message, "")
        }
    }

}