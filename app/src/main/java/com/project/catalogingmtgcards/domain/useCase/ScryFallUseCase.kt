package com.project.catalogingmtgcards.domain.useCase

import com.project.catalogingmtgcards.domain.ScryFallStateUseCase

//Chama o repository e devolve um state Success(Response) ou Error
//Se retornar Success converte o response em model

interface ScryFallUseCase {
    suspend fun getListCardByColorUseCase(colorCardName: String): ScryFallStateUseCase
}