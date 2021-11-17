package mas.com.filmLib.utils

import mas.com.filmLib.model.data.AppState
import mas.com.filmLib.model.data.DataModelLib

fun parseListResults(data: AppState): AppState {
    var newSearchResults = DataModelLib()
    when (data) {
        is AppState.Success -> {
            newSearchResults = data.data
        }
    }
    return AppState.Success(newSearchResults)
}
