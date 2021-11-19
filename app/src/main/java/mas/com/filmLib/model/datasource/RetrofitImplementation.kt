package mas.com.filmLib.model.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import mas.com.filmLib.model.data.DataModelFilm
import mas.com.filmLib.model.data.DataModelLib
import mas.com.filmLib.model.data.api.ApiService
import mas.com.filmLib.model.data.api.BaseInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation : DataSource<DataModelLib> {

    override suspend fun getData(page: Int): DataModelLib {
        return getService(BaseInterceptor.interceptor).getLibraryAsync(
            API_KEY,
            "ru",
            page
        ).await()
    }

    override suspend fun getDataFilm(film: Int): DataModelFilm {
        return getService(BaseInterceptor.interceptor).getFilmAsync(
            film,
            API_KEY,
            "ru"
        ).await()
    }

    private fun getService(interceptor: Interceptor): ApiService {
        return createRetrofit(interceptor).create(ApiService::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient(interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "274f828ad283bd634ef4fc1ee4af255f"
    }
}
