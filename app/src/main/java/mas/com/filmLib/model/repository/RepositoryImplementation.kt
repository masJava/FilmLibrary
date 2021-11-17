package mas.com.filmLib.model.repository

import mas.com.filmLib.model.data.DataModelLib
import mas.com.filmLib.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<DataModelLib>) :
    Repository<DataModelLib> {

    override suspend fun getData(page: Int): DataModelLib = dataSource.getData(page)

    override suspend fun getDataFilm(film: Int): String = dataSource.getDataFilm(film).toString()

}
