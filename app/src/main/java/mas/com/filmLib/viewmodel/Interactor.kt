package mas.com.filmLib.viewmodel

interface Interactor<T> {

    suspend fun getData(page: Int, fromRemoteSource: Boolean): T
}
