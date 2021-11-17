package mas.com.filmLib.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mas.com.filmLib.databinding.ActivityMainRecyclerviewItemBinding
import mas.com.filmLib.model.data.DataModel
import mas.com.filmLib.model.data.Result
import kotlin.math.roundToInt

class MainAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<Result> = arrayListOf()

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
                descriptionTextviewRecyclerItem.text = data.overview
                if (data.vote_average == 0.0) {
                    rank.visibility = View.GONE
                } else {
                    rank.visibility = View.VISIBLE
                }
                rank.progress = (data.vote_average * 10).roundToInt()
                tvProgressCircle.text = "${(data.vote_average * 10).roundToInt()} %"
//                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
