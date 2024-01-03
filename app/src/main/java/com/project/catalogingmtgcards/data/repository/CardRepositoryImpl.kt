package com.project.catalogingmtgcards.data.repository

import com.project.catalogingmtgcards.data.service.ScryfallService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.ConnectException

class CardRepositoryImpl(
    private val service: ScryfallService
) : CardRepository {

    override suspend fun getListCardByColor(colorCardName: String): StateCardRepository {
        return withContext(Dispatchers.IO) {
            try {
                callGetListCard(colorCardName)
            } catch (e: ConnectException) {
                StateCardRepository.Error(exception = Exception("Falha na comunicação com API"))
            } catch (e: Exception) {
                StateCardRepository.Error(exception = e)
            }
        }
    }

    private suspend fun callGetListCard(cardName: String): StateCardRepository {
        val response = service.getListCards(cardName)
        return if (response.isSuccessful) {
            StateCardRepository.Success(dataListCard = response.body())
        } else {
            StateCardRepository.Error(exception = Exception("Falha ao buscar card"))
        }
    }
}