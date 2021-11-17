package mas.com.filmLib.model.data

sealed class AppState {

    data class Success(val data: DataModelLib) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
