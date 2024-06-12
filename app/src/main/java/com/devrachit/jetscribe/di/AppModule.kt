package com.devrachit.jetscribe.di

import android.content.Context
import androidx.room.Room
import com.devrachit.jetscribe.common.Constants
import com.devrachit.jetscribe.data.remote.JetscribeApi
import com.devrachit.jetscribe.data.repository.JetscribeRepositoryImpl
import com.devrachit.jetscribe.domain.repository.JetscribeRepository
import com.devrachit.jetscribe.room.AppDatabase
import com.devrachit.jetscribe.room.SavedBlogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteRecipeDao(database: AppDatabase): SavedBlogDao {
        return database.savedBlogDao()
    }
}