package ir.dehghani.kotlincrypto.base.arch

abstract class BasePresenter<Model : BaseModel>(private val model: Model) {
    fun getModel() = model

}