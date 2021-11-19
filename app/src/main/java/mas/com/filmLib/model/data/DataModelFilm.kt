package mas.com.filmLib.model.data

import android.os.Parcel
import android.os.Parcelable


data class DataModelFilm(
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.createTypedArrayList(Genre) ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.createTypedArrayList(ProductionCompany) ?: emptyList(),
        parcel.createTypedArrayList(ProductionCountry) ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(SpokenLanguage) ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    )

    constructor() : this(
        false,
        "",
        0,
        emptyList(),
        "",
        0,
        "",
        "",
        "",
        "",
        0.0,
        "",
        emptyList(),
        emptyList(),
        "",
        0,
        0,
        emptyList(),
        "",
        "",
        "",
        false,
        0.0,
        0
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdrop_path)
        parcel.writeInt(budget)
        parcel.writeTypedList(genres)
        parcel.writeString(homepage)
        parcel.writeInt(id)
        parcel.writeString(imdb_id)
        parcel.writeString(original_language)
        parcel.writeString(original_title)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(poster_path)
        parcel.writeTypedList(production_companies)
        parcel.writeTypedList(production_countries)
        parcel.writeString(release_date)
        parcel.writeInt(revenue)
        parcel.writeInt(runtime)
        parcel.writeTypedList(spoken_languages)
        parcel.writeString(status)
        parcel.writeString(tagline)
        parcel.writeString(title)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(vote_average)
        parcel.writeInt(vote_count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataModelFilm> {
        override fun createFromParcel(parcel: Parcel): DataModelFilm {
            return DataModelFilm(parcel)
        }

        override fun newArray(size: Int): Array<DataModelFilm?> {
            return arrayOfNulls(size)
        }
    }
}

