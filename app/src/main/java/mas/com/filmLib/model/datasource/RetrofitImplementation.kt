package mas.com.filmLib.model.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import mas.com.filmLib.model.data.DataModelLib
import mas.com.filmLib.model.data.api.ApiService
import mas.com.filmLib.model.data.api.BaseInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation : DataSource<DataModelLib> {

    //    override suspend fun getData(word: String): List<DataModel> {
//        return getService(BaseInterceptor.interceptor).searchAsync(word).await()
//    }
    override suspend fun getData(page: Int): DataModelLib {
        return getService(BaseInterceptor.interceptor).getLibraryAsync(
            "274f828ad283bd634ef4fc1ee4af255f",
            "ru",
            page
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
        //        private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
        private const val BASE_URL = "https://api.themoviedb.org/3/discover/"
    }
}
