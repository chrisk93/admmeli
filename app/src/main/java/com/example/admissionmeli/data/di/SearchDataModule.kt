package com.example.admissionmeli.data.di

import com.example.admissionmeli.data.ItemRepository
import com.example.admissionmeli.data.ItemRepositoryImpl
import com.example.admissionmeli.data.SearchRepository
import com.example.admissionmeli.data.SearchRepositoryImpl
import com.example.admissionmeli.data.remote.ItemApi
import com.example.admissionmeli.data.remote.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDataModule {
    @Singleton
    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi = retrofit.create(SearchApi::class.java)

    @Singleton
    @Provides
    fun provideItemApi(retrofit: Retrofit): ItemApi = retrofit.create(ItemApi::class.java)

    @Singleton
    @Provides
    fun provideSearchRepository(searchApi: SearchApi): SearchRepository =
        SearchRepositoryImpl(searchApi)

    @Singleton
    @Provides
    fun provideItemRepository(itemApi: ItemApi): ItemRepository =
        ItemRepositoryImpl(itemApi)

}