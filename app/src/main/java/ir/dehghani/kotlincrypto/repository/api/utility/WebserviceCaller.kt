package ir.dehghani.kotlincrypto.repository.api.utility

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject

class WebserviceCaller private constructor(private val client: OkHttpClient) {

    companion object {

        @Volatile
        private var instance: WebserviceCaller? = null

        fun init(client: OkHttpClient) {
            instance = WebserviceCaller(client)
        }

        fun getInstance() = instance
    }

    fun call(url: String, requestBody: String, requestType: RequestTypeEnum): WebserviceResponseModel<Any> {
        val requestBuilder = Request.Builder()
            .addHeader("content-type", "application/json; charset=utf-8")
            .addHeader("accept", "application/json")
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
            try {
                val jsonArray = JSONArray(data)
                val jObj = JSONObject("{ \"data\":" + data + "}")
                jsonArray.put(jObj)
                data = jsonArray.toString();
            } catch (_: Exception) {
            }
            WebserviceResponseModel(true, response.message, JSONObject(data.toString()).get("data"))
        } else {
            WebserviceResponseModel(false, response.message, "")
        }
    }

}