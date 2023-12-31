package com.project.catalogingmtgcards.domain.di

import com.project.catalogingmtgcards.domain.useCase.getListCardUseCase.GetListCardUseCase
import com.project.catalogingmtgcards.domain.useCase.getCardByNameRepository.GetCardByNameUseCase
import com.project.catalogingmtgcards.domain.useCase.getCardByNameRepository.GetCardByNameUseCaseImpl
import com.project.catalogingmtgcards.domain.useCase.getListCardUseCase.GetListCardUseCaseImpl
import com.project.catalogingmtgcards.domain.useCase.autocompleteSearchUseCase.GetListNameCardAutocompleteUseCase
import com.project.catalogingmtgcards.domain.useCase.autocompleteSearchUseCase.GetListNameCardAutocompleteUseCaseImpl
import com.project.catalogingmtgcards.domain.useCase.getImageManaSymbolUseCase.GetSymbolManaCostUseCase
import com.project.catalogingmtgcards.domain.useCase.getImageManaSymbolUseCase.GetSymbolManaCostUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object UseCaseModule {

    fun loadUseCaseModule() {
        loadKoinModules(createUseCaseScryFallList())
    }

    private fun createUseCaseScryFallList() = module {
        single<GetListCardUseCase> { GetListCardUseCaseImpl(get(), get()) }
        single<GetSymbolManaCostUseCase> { GetSymbolManaCostUseCaseImpl(get(), get()) }
        single<GetListNameCardAutocompleteUseCase> {
            GetListNameCardAutocompleteUseCaseImpl(
                get(),
                get()
            )
        }
        single<GetCardByNameUseCase> { GetCardByNameUseCaseImpl(get(), get()) }
    }
}