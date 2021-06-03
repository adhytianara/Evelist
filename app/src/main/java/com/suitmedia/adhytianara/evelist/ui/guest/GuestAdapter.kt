package com.suitmedia.adhytianara.evelist.ui.guest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.suitmedia.adhytianara.evelist.R
import com.suitmedia.adhytianara.evelist.data.Guest
import com.suitmedia.adhytianara.evelist.databinding.ItemRowGuestBinding
import com.suitmedia.adhytianara.evelist.utils.Constants.Companion.DUMMY_IMAGE_URL
import java.util.*

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.ListViewHolder>() {

    private var listData = ArrayList<Guest>()
    var onItemClick: ((Guest) -> Unit)? = null

    fun setData(newListData: List<Guest>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_guest, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowGuestBinding.bind(itemView)
        fun bind(data: Guest) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(DUMMY_IMAGE_URL)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgGuest)
                tvGuestName.text = data.name
                tvGuestBirthdate.text = data.birthdate
                tvGuestIsPrime.text = data.monthIsPrime.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}