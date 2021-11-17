package mas.com.filmLib.model.datasource

interface DataSource<T> {

    suspend fun getData(page: Int): T
}
