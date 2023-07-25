package ir.dehghani.kotlincrypto.repository.api.utility

import org.json.JSONObject

data class WebserviceResponseModel<T>(val status: Boolean,val message: String , val data: T ,val metadata: JSONObject? = null)
