package br.com.vegait.helpdeliveryman.data.api

import br.com.vegait.helpdeliveryman.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    private const val READ_TIMEOUT = 120L
    private const val CONNECT_TIMEOUT = 2L
    private const val ACCEPT = "Accept"
    private const val CONTENT_TYPE = "Content-Type"
    private const val CONTENT_TYPE_JSON = "application/json"

    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

    val networkModule = module {
        single { provideDefaultOkHttpClient() }
    }

    @Throws(IOException::class)
    private fun accessTokenInterceptor(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()
            .header(CONTENT_TYPE, CONTENT_TYPE_JSON)
            .header(ACCEPT, CONTENT_TYPE_JSON)

        return chain.proceed(requestBuilder.build())
    }

    private fun provideDefaultOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor {
                accessTokenInterceptor(it)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}