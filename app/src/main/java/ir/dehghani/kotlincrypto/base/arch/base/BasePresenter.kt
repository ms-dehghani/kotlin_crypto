package ir.dehghani.kotlincrypto.base.arch.base

abstract class BasePresenter<Model : BaseModel, State : BaseState>(val model: Model, val state: State) {
}