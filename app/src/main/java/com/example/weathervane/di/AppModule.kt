package com.example.weathervane.di

import com.example.weathervane.data.remote.WeatherApiService
import com.example.weathervane.data.repository.Repository
import com.example.weathervane.data.repository.RepositoryImpl
import com.example.weathervane.store.weatherReducer
import com.example.weathervane.model.WeatherState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.reduxkotlin.Store
import org.reduxkotlin.createThreadSafeStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): WeatherApiService {
        return Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/").addConverterFactory(
            GsonConverterFactory.create())
            .build().create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: WeatherApiService) : Repository {

        return RepositoryImpl(apiService)

    }

    //    Redux dependency injection
    @Provides
    @Singleton
    fun provideRecipeStore(): Store<WeatherState> {
        return createThreadSafeStore(weatherReducer, WeatherState())
    }

}