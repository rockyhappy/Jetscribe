package com.devrachit.jetscribe.di

import com.devrachit.jetscribe.common.Constants
import com.devrachit.jetscribe.data.remote.JetscribeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun providesBlogApi() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JetscribeApi::class.java)

    @Singleton
    @Provides
    fun providesBlogRepository(blogApi: JetscribeApi): JetscribeRepository = JetscribeRepositoryImpl(blogApi)
}