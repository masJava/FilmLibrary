package mas.com.filmLib.view.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import mas.com.filmLib.R
import mas.com.filmLib.databinding.ActivityMainRecyclerviewItemBinding
import mas.com.filmLib.model.data.Result
import kotlin.math.roundToInt

class MainAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<Result> = arrayListOf()
    private var tagsMap = mapOf<Int, String>(
        Pair(28, "боевик"),
        Pair(12, "приключения"),
        Pair(16, "мультфильм"),
        Pair(35, "комедия"),
        Pair(80, "криминал"),
        Pair(99, "документальный"),
        Pair(18, "драма"),
        Pair(10751, "семейный"),
        Pair(14, "фэнтези"),
        Pair(36, "история"),
        Pair(27, "ужасы"),
        Pair(10402, "музыка"),
        Pair(9648, "детектив"),
        Pair(10749, "мелодрама"),
        Pair(878, "фантастика"),
        Pair(10770, "телевизионный фильм"),
        Pair(53, "триллер"),
        Pair(10752, "военный"),
        Pair(37, "вестерн")
    )

    fun setData(data: List<Result>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ActivityMainRecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(var vb: ActivityMainRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(data: Result) = with(vb) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                headerTextviewRecyclerItem.text = data.title
                releaseDate.text = "Дата релиза: ${data.release_date}"
                if (data.vote_average == 0.0) {
                    rank.visibility = View.GONE
                } else {
                    rank.visibility = View.VISIBLE
                }
                rank.progress = (data.vote_average * 10).roundToInt()
                tvProgressCircle.text = "${(data.vote_average * 10).roundToInt()}%"
                poster?.let {
                    usePicassoToLoadPhoto(vb.root.context, it, data.poster_path)
                }
                tags.text = setTagsStr(data.genre_ids)
            }
            itemView.setOnClickListener { openInNewWindow(data.id) }
        }
    }

    private fun setTagsStr(tagsInt: List<Int>): String {
        var tagsStr = ""
        for (tag in tagsInt)
            tagsStr += "${tagsMap.get(tag)} \n"
        return tagsStr
    }

    private fun usePicassoToLoadPhoto(context: Context, imageView: ImageView, imageLink: String) {
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500$imageLink")
            .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                }

                override fun onError() {
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            })
    }

    private fun openInNewWindow(film: Int) {
        onListItemClickListener.onItemClick(film)
    }

    interface OnListItemClickListener {
        fun onItemClick(film: Int)
    }
}
