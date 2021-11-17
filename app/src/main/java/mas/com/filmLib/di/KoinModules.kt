package mas.com.filmLib.di

import mas.com.filmLib.model.data.DataModelLib
import mas.com.filmLib.model.datasource.RetrofitImplementation
import mas.com.filmLib.model.datasource.RoomDataBaseImplementation
import mas.com.filmLib.model.repository.Repository
import mas.com.filmLib.model.repository.RepositoryImplementation
import mas.com.filmLib.view.main.MainInteractor
import mas.com.filmLib.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<DataModelLib>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<DataModelLib>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
