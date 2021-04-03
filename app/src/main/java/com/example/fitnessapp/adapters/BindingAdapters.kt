package com.example.fitnessapp.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fitnessapp.R
import com.example.fitnessapp.db.Run
import com.example.fitnessapp.network.Exercise
import com.example.fitnessapp.util.TrackingUtility
import java.text.SimpleDateFormat
import java.util.*


// run adapters

@BindingAdapter("runImage")
fun ImageView.bindRunImage(item: Run?) {
    item?.let {
//        Glide
//            .with(this.context)
//            .load(item.img)
//            .into(this)

        this.setImageBitmap(item.img)
    }
}

@BindingAdapter("runDate")
fun TextView.bindDate(item: Run?) {
    item?.let {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = item.timestamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        text = dateFormat.format(calendar.time)
    }
}

@BindingAdapter("runTime")
fun TextView.bindTime(item: Run?) {
    item?.let {
        text = TrackingUtility.getFormattedStopWatchTime(item.timeInMillis)
    }
}

@BindingAdapter("runAvgSpeed")
fun TextView.bindAvgSpeed(item: Run?) {
    item?.let {
        "${item.avgSpeedInKMH}km/h".also {
            text = it
        }
    }
}

@BindingAdapter("runDistance")
fun TextView.bindDistance(item: Run?) {
    item?.let {
        "${item.distanceInMeters / 1000f}km".also {
            text = it
        }
    }
}

@BindingAdapter("burnedCalories")
fun TextView.bindBurnedCalories(item: Run?) {
    item?.let {
        "${item.caloriesBurned}kcal".also {
            text = it
        }
    }
}

@BindingAdapter("setAdapter")
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}


// exercise adapters

@BindingAdapter("exerciseImage")
fun ImageView.bindExerciseImage(imageUrl: String?) {
    imageUrl?.let {
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide
            .with(this.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)
    }
}

@BindingAdapter("exerciseName")
fun TextView.bindExerciseName(item: Exercise?) {
    item?.let {
        item.name.also {
            text = it
        }
    }
}

@BindingAdapter("exerciseField")
fun TextView.bindExerciseField(item: Exercise?) {
    item?.let {
        when (id) {
            R.id.tvCategory -> "Category: ${item.category}".also { text = it }
            R.id.tvEquipment -> "Equipment: ${item.equipment}".also { text = it }
            R.id.tvDifficulty -> "Difficulty: ${item.difficulty}".also { text = it }
            R.id.tvDescription -> "Description: ${item.description}".also { text = it }
            else -> {
            }
        }
    }
}