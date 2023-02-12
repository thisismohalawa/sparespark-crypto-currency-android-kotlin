package sparespark.crypto.currency.data.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sparespark.crypto.currency.core.secret.API_KEY
import sparespark.crypto.currency.core.secret.Constants.ARTICLES_BASE_URL
import sparespark.crypto.currency.core.secret.Constants.COIN_PAPRIKA_BASE_URL
import sparespark.crypto.currency.data.datasource.CoinDatabase
import sparespark.crypto.currency.data.remote.ArticleNetworkService
import sparespark.crypto.currency.data.remote.CoinPaprikaService
import sparespark.crypto.currency.data.repository.ArticlesRepositoryImpl
import sparespark.crypto.currency.data.repository.CoinRepositoryImpl
import sparespark.crypto.currency.domain.repository.ArticlesRepository
import sparespark.crypto.currency.domain.repository.CoinRepository
import javax.inject.Singleton

/*
* SingletonComponent :
* all dependency in module lives as long as application lives.
*
* */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    /*
    * Provides -> provide a dependency.
    * Singleton -> single instance of CoinPaprikaService object.
    * */
    @Provides
    @Singleton
    fun provideCoinPaprikaServices(): CoinPaprikaService {
        return Retrofit.Builder()
            .baseUrl(COIN_PAPRIKA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaService::class.java)
    }

    @Provides
    @Singleton
    fun provideArticlesNetworkServices(): ArticleNetworkService {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("apiKey", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ARTICLES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArticleNetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinDatabase(app: Application): CoinDatabase {
        return Room.databaseBuilder(
            app,
            CoinDatabase::class.java,
            CoinDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        api: CoinPaprikaService,
        db: CoinDatabase
    ): CoinRepository {
        return CoinRepositoryImpl(
            coinApi = api,
            coinDao = db.coinDao
        )
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(
        api: ArticleNetworkService,
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(
            api = api
        )
    }
}