package com.example.numberfacts.presentation.screen.search_fact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.numberfacts.R
import com.example.numberfacts.databinding.ItemNumberFactBinding
import com.example.numberfacts.domain.model.NumberFactModel

class SearchFactAdapter(
    private val onItemClick: (NumberFactModel) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    private val list: MutableList<NumberFactModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_number_fact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun update(newList: List<NumberFactModel>) {
        val callback = DiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(callback)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemNumberFactBinding::bind)

        fun onBind(model: NumberFactModel) {
            binding.root.setOnClickListener { onItemClick(model) }
            binding.number.text = model.number.toString()
            binding.fact.text = model.fact
        }
    }

    inner class DiffUtilCallback(
        private val old: List<NumberFactModel>,
        private val new: List<NumberFactModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            new[newItemPosition] == old[oldItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            new[newItemPosition] == old[oldItemPosition]
    }
}