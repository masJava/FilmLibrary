package mas.com.filmLib.model.datasource

import mas.com.filmLib.model.data.DataModelFilm

interface DataSource<T> {

    suspend fun getData(page: Int): T
    suspend fun getDataFilm(page: Int): DataModelFilm

}
