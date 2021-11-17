package mas.com.filmLib.model.repository

interface Repository<T> {

    suspend fun getData(page: Int): T
    suspend fun getDataFilm(film: Int): String
}
