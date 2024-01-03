package com.project.catalogingmtgcards.domain.useCase

import com.project.catalogingmtgcards.data.repository.CardRepository
import com.project.catalogingmtgcards.data.repository.StateCardRepository
import com.project.catalogingmtgcards.data.service.ScryfallService
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

class GetCardUseCaseImpl(
    private val repository: CardRepository,
    private val service: ScryfallService
) : GetCardByColorUseCase {

    override suspend fun getListCardUseCase(colorCardName: String): ScryFallStateUseCase {
        val response = service.getListCards(colorCardName)
        //Repository retorna um ScryFallStateRepository do tipo Success ou Error
        return when (repository.getListCardByColor(colorCardName)) {
            StateCardRepository.Success(dataListCard = response.body()) -> {
                ScryFallStateUseCase.Success(listCard = response.body())
            }

            StateCardRepository.Error(exception = Exception("Falha ao buscar card")) -> {
                ScryFallStateUseCase.Error
            }

            else -> {
                ScryFallStateUseCase.Empty
            }
        }

    }
}