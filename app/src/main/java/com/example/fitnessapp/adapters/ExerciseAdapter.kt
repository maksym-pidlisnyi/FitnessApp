package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ItemExerciseBinding
import com.example.fitnessapp.domain.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class ExerciseAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ExerciseAdapter.DataItem, RecyclerView.ViewHolder>(DiffCallBack()) {

    class ExerciseViewHolder(private val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Exercise) {
            binding.exercise = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ExerciseViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemExerciseBinding.inflate(layoutInflater, parent, false)

                return ExerciseViewHolder(binding)
            }
        }
    }

    sealed class DataItem {
        data class ExerciseItem(val exercise: Exercise) : DataItem() {
            override val id = exercise.id
        }

        object Header : DataItem() {
            override val id = Long.MIN_VALUE.toString()
        }

        abstract val id: String
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header_exercises, parent, false)
                return TextViewHolder(view)
            }
        }
    }


    class DiffCallBack : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    class OnClickListener(val clickListener: (exercise: Exercise) -> Unit) {
        fun onClick(networkExercise: Exercise) = clickListener(networkExercise)
    }

    //    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        ExerciseViewHolder(ItemExerciseBinding.inflate(LayoutInflater.from(parent.context)))
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ExerciseViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExerciseViewHolder -> {
                val exercise = getItem(position) as DataItem.ExerciseItem
                holder.itemView.setOnClickListener {
                    onClickListener.onClick(exercise.exercise)
                }
                holder.bind(exercise.exercise)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ExerciseItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)
    fun addHeaderAndSubmitList(list: List<Exercise>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.ExerciseItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }


}
