package com.project.catalogingmtgcards.data.repository

interface ScryFallRepository {

    suspend fun getListCardByColor(colorCardName: String): ScryFallStateRepository

}