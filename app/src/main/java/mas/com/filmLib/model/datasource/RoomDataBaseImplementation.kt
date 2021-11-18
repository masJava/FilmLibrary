package mas.com.filmLib.model.datasource

import com.google.gson.JsonObject
import mas.com.filmLib.model.data.DataModelLib

class RoomDataBaseImplementation : DataSource<DataModelLib> {

    override suspend fun getData(page: Int): DataModelLib {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getDataFilm(page: Int): JsonObject {
        TODO("Not yet implemented")
    }
}
