package com.suitmedia.adhytianara.evelist.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.suitmedia.adhytianara.evelist.R
import com.suitmedia.adhytianara.evelist.data.Event
import com.suitmedia.adhytianara.evelist.databinding.ItemRowEventBinding
import com.suitmedia.adhytianara.evelist.databinding.ItemRowEventHorizontalBinding
import java.util.*

class EventHorizontalAdapter : RecyclerView.Adapter<EventHorizontalAdapter.ListViewHolder>() {

    private var listData = ArrayList<Event>()
    var onItemClick: ((Event) -> Unit)? = null

    fun setData(newListData: List<Event>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_event_horizontal, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowEventHorizontalBinding.bind(itemView)
        fun bind(data: Event) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgEvent)
                tvEventName.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}