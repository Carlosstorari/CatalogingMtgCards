package com.project.catalogingmtgcards.domain

import com.project.catalogingmtgcards.data.response.CardListResponse
import com.project.catalogingmtgcards.data.response.CardResponse

sealed class ScryFallStateUseCase {
    data class Success(val card: CardListResponse?): ScryFallStateUseCase()
    object Error: ScryFallStateUseCase()// é object pq nao é passado parametro
    object Empty: ScryFallStateUseCase()
}