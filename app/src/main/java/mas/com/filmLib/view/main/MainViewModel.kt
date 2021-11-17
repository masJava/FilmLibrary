package mas.com.filmLib.view.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mas.com.filmLib.model.data.AppState
import mas.com.filmLib.model.data.DataModelLib
import mas.com.filmLib.utils.parseListResults
import mas.com.filmLib.viewmodel.BaseViewModel

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(page: Int, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(page, isOnline) }
    }

    //Doesn't have to use withContext for Retrofit call if you use .addCallAdapterFactory(CoroutineCallAdapterFactory()). The same goes for Room
    private suspend fun startInteractor(page: Int, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(parseListResults(interactor.getData(page, isOnline)))
        }

    override fun getDataFilm(film: Int, isOnline: Boolean) {
        viewModelCoroutineScope.launch {
            _mutableLiveData.postValue(
                AppState.ToDescriptionActivity(interactor.getDataFilm(film))
            )
        }
    }


    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(DataModelLib())
        super.onCleared()
    }
}
