package mas.com.filmLib.model.datasource

import mas.com.filmLib.model.data.DataModelFilm
import mas.com.filmLib.model.data.DataModelLib

class RoomDataBaseImplementation : DataSource<DataModelLib> {

    override suspend fun getData(page: Int): DataModelLib {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getDataFilm(page: Int): DataModelFilm {
        TODO("Not yet implemented")
    }
}
