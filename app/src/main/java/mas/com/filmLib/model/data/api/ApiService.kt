package mas.com.filmLib.model.data.api

import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import mas.com.filmLib.model.data.DataModelLib
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getLibraryAsync(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Deferred<DataModelLib>

    @GET("movie/{film}")
    fun getFilmAsync(
        @Path("film") film: Int,
        @Query("api_key") key: String,
        @Query("language") language: String,
    ): Deferred<JsonObject>
}
