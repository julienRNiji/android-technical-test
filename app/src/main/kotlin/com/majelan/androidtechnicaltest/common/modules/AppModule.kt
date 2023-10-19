package com.majelan.androidtechnicaltest.common.modules

import com.majelan.androidtechnicaltest.common.constants.BASE_URL
import com.majelan.androidtechnicaltest.common.data.api.MusicHelper
import com.majelan.androidtechnicaltest.common.data.api.MusicHelperImpl
import com.majelan.androidtechnicaltest.common.data.api.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): MusicService =
        retrofit.create(MusicService::class.java)

    @Provides
    fun provideApiHelper(apiHelper: MusicHelperImpl): MusicHelper = apiHelper
}