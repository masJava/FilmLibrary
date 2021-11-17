package mas.com.filmLib.utils

import mas.com.filmLib.model.data.AppState
import mas.com.filmLib.model.data.DataModel
import mas.com.filmLib.model.data.DataModelLib
import mas.com.filmLib.model.data.Meanings

fun parseSearchResults(data: AppState): AppState {
    var newSearchResults = DataModelLib()
    when (data) {
        is AppState.Success -> {
            newSearchResults = data.data
//            if (!searchResults.isNullOrEmpty()) {
//                for (searchResult in searchResults) {
//                    parseResult(searchResult, newSearchResults)
//                }
//            }
        }
    }

    return AppState.Success(newSearchResults)
}

private fun parseResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}
