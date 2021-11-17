package mas.com.filmLib.model.datasource

import com.google.gson.JsonObject

interface DataSource<T> {

    suspend fun getData(page: Int): T
    suspend fun getDataFilm(page: Int): JsonObject

}
