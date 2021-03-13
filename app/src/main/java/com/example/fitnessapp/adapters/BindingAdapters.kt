package com.example.fitnessapp.adapters

import android.graphics.Bitmap
import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.db.Run
import com.example.fitnessapp.ui.fragments.RunFragment
import com.example.fitnessapp.util.TrackingUtility
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("runImage")
fun ImageView.bindImage(item: Run?) {
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
