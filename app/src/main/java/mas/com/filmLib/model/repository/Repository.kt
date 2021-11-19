package mas.com.filmLib.model.repository

import mas.com.filmLib.model.data.DataModelFilm

interface Repository<T> {

    suspend fun getData(page: Int): T
    suspend fun getDataFilm(film: Int): DataModelFilm
}
