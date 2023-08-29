package com.project.catalogingmtgcards.data.service

import com.project.catalogingmtgcards.data.model.Card
import com.project.catalogingmtgcards.data.model.CardList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ScryfallService {
//    @GET("cards/named")
//    suspend fun getCardByName(@Query("fuzzy") cardName: String): Response<Card>

    @GET("cards/search")
    suspend fun getListCards(@Query("q") searchQuery: String): Response<CardList>
}