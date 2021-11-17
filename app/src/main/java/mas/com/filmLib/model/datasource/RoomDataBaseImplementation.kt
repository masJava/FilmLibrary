package mas.com.filmLib.model.datasource

import mas.com.filmLib.model.data.DataModelLib

class RoomDataBaseImplementation : DataSource<DataModelLib> {

    override suspend fun getData(page: Int): DataModelLib {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

//class RoomDataBaseImplementation : DataSource<List<DataModel>> {
//
//    override suspend fun getData(word: String): List<DataModel> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
