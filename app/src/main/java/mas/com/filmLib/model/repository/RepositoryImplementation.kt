package mas.com.filmLib.model.repository

import mas.com.filmLib.model.data.DataModel
import mas.com.filmLib.model.data.DataModelLib
import mas.com.filmLib.model.datasource.DataSource

//class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
//    Repository<List<DataModel>> {
//
//    override suspend fun getData(word: String): List<DataModel> {
//        return dataSource.getData(word)
//    }
//}
class RepositoryImplementation(private val dataSource: DataSource<DataModelLib>) :
    Repository<DataModelLib> {

    override suspend fun getData(page: Int): DataModelLib {
        var data =  dataSource.getData(page)
        return data
    }
}
