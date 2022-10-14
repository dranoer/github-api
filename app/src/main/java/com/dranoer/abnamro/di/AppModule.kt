package com.dranoer.abnamro.di

import com.dranoer.abnamro.BuildConfig
import com.dranoer.abnamro.data.remote.NetworkDataSource
import com.dranoer.abnamro.data.remote.WebService
import com.dranoer.abnamro.domain.RepoRepository
import com.dranoer.abnamro.ui.util.Const.BASE_API
import com.dranoer.abnamro.ui.util.Const.TIME_OUT
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApiService(): WebService {

        val gson = GsonBuilder().setLenient().create()

        val httpLogger = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder()
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLogger)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        remoteSource: NetworkDataSource,
    ) = RepoRepository(remoteSource)
}