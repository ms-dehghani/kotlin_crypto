package ir.dehghani.kotlincrypto.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.dehghani.kotlincrypto.model.items.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.model.items.currency.item.presenter.CurrencyItemPresenter
import ir.dehghani.kotlincrypto.model.items.currency.list.model.CurrencyListModel
import ir.dehghani.kotlincrypto.model.items.currency.list.presenter.CurrencyListPresenter
import ir.dehghani.kotlincrypto.model.repository.Repository


@Module
@InstallIn(SingletonComponent::class)
class PresenterModule {

    @Provides
    fun provideCurrencyListPresenter(model: CurrencyListModel): CurrencyListPresenter {
        return CurrencyListPresenter(model)
    }

    @Provides
    fun provideCurrencyListModel(repo: Repository): CurrencyListModel {
        return CurrencyListModel(repo)
    }

    @Provides
    fun provideCurrencyItemPresenter(model: CurrencyItemModel): CurrencyItemPresenter {
        return CurrencyItemPresenter(model)
    }

    @Provides
    fun provideCurrencyItemModel(repo: Repository): CurrencyItemModel {
        return CurrencyItemModel(repo)
    }

}