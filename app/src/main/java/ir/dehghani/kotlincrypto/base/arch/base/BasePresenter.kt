package ir.dehghani.kotlincrypto.base.arch.base
abstract class BasePresenter<Model : BaseModel, State : BaseState>(private val model: Model, private val state: State) {

    fun getState() = state

    fun getModel() = model

}