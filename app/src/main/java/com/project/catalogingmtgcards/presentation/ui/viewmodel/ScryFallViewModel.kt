package com.project.catalogingmtgcards.presentation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.catalogingmtgcards.data.response.CardResponseDto
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase
import com.project.catalogingmtgcards.domain.model.Card
import com.project.catalogingmtgcards.domain.useCase.GetCardByColorUseCase
import com.project.catalogingmtgcards.domain.useCase.GetSymbolManaCostUseCase
import com.project.catalogingmtgcards.presentation.ui.fragments.SearchConstants.ONLY_RED
import kotlinx.coroutines.launch

class ScryFallViewModel(
    application: Application,
    private val useCase: GetCardByColorUseCase,
    private val useCaseSymbol: GetSymbolManaCostUseCase
) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val state: MutableLiveData<ScryFallViewModelState> = MutableLiveData()
    val getState: LiveData<ScryFallViewModelState> = state

    fun getCardListItem(searchQuery: String = ONLY_RED) {
        viewModelScope.launch {
            val useCaseGetCardsList = useCase.getListCardUseCase(searchQuery)
            if (useCaseGetCardsList is ScryFallStateUseCase.Success) {
                val useCaseGetManaCostSymbol =
                    useCaseGetCardsList.card?.let { useCaseSymbol.getSymbolManaCost(it.data) }
                if (useCaseGetManaCostSymbol is ScryFallStateUseCase.Success) {
                    state.postValue(
                        ScryFallViewModelState.Success(
                            createListCard(
                                useCaseGetCardsList.card.data,
                                useCaseGetManaCostSymbol.symbologyMana
                            )
                        )
                    )
                }
            }
        }
    }

    private fun createListCard(
        listCard: List<CardResponseDto>,
        manaCostList: List<List<String>>?
    ): List<Card> {
        var cardList = mutableListOf<Card>()
        listCard.forEachIndexed { index, cardResponse ->
            cardResponse.imageCard?.let{
                cardList.add(
                    Card(
                        imgCard = it.artCrop,
                        name = cardResponse.name,
                        typeLine = cardResponse.typeLine,
                        listUrlManaCost = manaCostList?.get(index)
                    )
                )
            }

        }
        return cardList
    }
}

