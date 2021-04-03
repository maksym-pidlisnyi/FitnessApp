package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.databinding.ItemExerciseBinding
import com.example.fitnessapp.network.Exercise

class ExerciseAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Exercise, ExerciseAdapter.ExerciseViewHolder>(DiffCallBack()) {

    class ExerciseViewHolder(private val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Exercise) {
            binding.exercise = item
            binding.executePendingBindings()
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    class OnClickListener(val clickListener: (exercise: Exercise) -> Boolean) {
        fun onClick(exercise: Exercise) = clickListener(exercise)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ExerciseViewHolder(ItemExerciseBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = getItem(position) as Exercise
        holder.itemView.setOnClickListener {
            onClickListener.onClick(exercise)
        }
        holder.bind(exercise)
    }


}
