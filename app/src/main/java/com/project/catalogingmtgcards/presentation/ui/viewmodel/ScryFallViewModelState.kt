package com.project.catalogingmtgcards.presentation.ui.viewmodel

import com.project.catalogingmtgcards.data.response.CardListResponse
import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

sealed class ScryFallViewModelState{
    data class Success(val card: ScryFallStateUseCase): ScryFallViewModelState()
    object Error: ScryFallViewModelState()// é object pq nao é passado parametro
    object Empty: ScryFallViewModelState()
    object Loading: ScryFallViewModelState()
}