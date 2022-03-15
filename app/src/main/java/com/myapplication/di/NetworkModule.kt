package com.myapplication.di

import androidx.viewbinding.BuildConfig
import com.myapplication.network.AlbumService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideAlbumApiService(retrofit: Retrofit): AlbumService =
        retrofit.create(AlbumService::class.java)

//    @Provides
//    @Singleton
//    fun provideAlbumDetailsService(retrofit: Retrofit): AlbumService =
//        retrofit.create(AlbumDetailsService::class.java)

}