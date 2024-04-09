package com.melaniadev.rickandmorty.di

import com.melaniadev.rickandmorty.data.RickAndMortyRepositoryImpl
import com.melaniadev.rickandmorty.domain.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRickAndMortyRepository(): RickAndMortyRepository{
        return RickAndMortyRepositoryImpl()
    }
}