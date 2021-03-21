package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.databinding.ItemRunBinding
import com.example.fitnessapp.db.Run

class RunAdapter(private val onClickListener: OnClickListener) :
        ListAdapter<Run, RunAdapter.RunViewHolder>(DiffCallBack()) {

    class RunViewHolder (private val binding: ItemRunBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Run) {
            binding.run = item
            binding.executePendingBindings()
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    class OnClickListener(val clickListener: (run: Run) -> Boolean) {
        fun onLongClick(run: Run) = clickListener(run)
    }

//    val differ = AsyncListDiffer(this, diffCallBack)
    //    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(ItemRunBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = getItem(position) as Run
        holder.itemView.setOnLongClickListener {
            onClickListener.onLongClick(run)
            true
        }
        holder.bind(run)
    }

}