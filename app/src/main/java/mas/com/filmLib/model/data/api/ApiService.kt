package mas.com.filmLib.model.data.api

import mas.com.filmLib.model.data.DataModel
import kotlinx.coroutines.Deferred
import mas.com.filmLib.model.data.DataModelLib
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>

    @GET("movie")
    fun getLibraryAsync(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Deferred<DataModelLib>
}
