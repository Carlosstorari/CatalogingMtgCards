package com.project.catalogingmtgcards.data.di

import com.project.catalogingmtgcards.data.repository.ScryFallRepository
import com.project.catalogingmtgcards.data.repository.ScryFallRepositoryImpl
import com.project.catalogingmtgcards.data.service.ScryfallService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    fun loadDataModule(){
        loadKoinModules(repositoryModule + retrofitModule)
    }

    private const val URL_BASE = "https://api.scryfall.com/"

    private val retrofitModule = module {
        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        single<OkHttpClient> {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }
        single<ScryfallService> { get<Retrofit>().create(ScryfallService::class.java) }
    }

    private val repositoryModule = module {
        single<ScryFallRepository> { ScryFallRepositoryImpl(get()) }
    }
}