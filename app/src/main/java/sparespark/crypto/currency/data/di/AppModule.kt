package sparespark.crypto.currency.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sparespark.crypto.currency.core.Constants.COIN_PAPRIKA_BASE_URL
import sparespark.crypto.currency.data.remote.CoinPaprikaService
import sparespark.crypto.currency.data.repository.CoinRepositoryImpl
import sparespark.crypto.currency.domain.repository.CoinRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // all dependency in module lives as long as application lives.
object AppModule {

    @Provides // provide a dependency.
    @Singleton // single instance of CoinPaprikaService object.
    fun provideCoinPaprikaServices(): CoinPaprikaService {
        return Retrofit.Builder()
            .baseUrl(COIN_PAPRIKA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaService): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}