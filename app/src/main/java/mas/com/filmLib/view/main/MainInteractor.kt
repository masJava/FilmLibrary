package mas.com.filmLib.view.main

import mas.com.filmLib.model.data.AppState
import mas.com.filmLib.model.data.DataModel
import mas.com.filmLib.model.data.DataModelLib
import mas.com.filmLib.model.repository.Repository
import mas.com.filmLib.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<DataModelLib>,
    private val repositoryLocal: Repository<DataModelLib>
) : Interactor<AppState> {

    override suspend fun getData(page: Int, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(page)
        )
    }
}
