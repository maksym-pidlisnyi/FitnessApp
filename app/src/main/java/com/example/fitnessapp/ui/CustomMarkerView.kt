package com.example.fitnessapp.ui

import android.content.Context
import android.view.LayoutInflater
import com.example.fitnessapp.databinding.MarkerViewBinding
import com.example.fitnessapp.db.Run
import com.example.fitnessapp.util.TrackingUtility
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.marker_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    val runs: List<Run>,
    context: Context,
    layoutId: Int
) : MarkerView(context, layoutId) {

    // TODO recheck
    private val binding: MarkerViewBinding = MarkerViewBinding.inflate(LayoutInflater.from(context))

//    override fun refreshContent(e: Entry?, highlight: Highlight?) {
//        if (e == null) {
//            return
//        }
//        val curRunId = e.x.toInt()
//        val run = runs[curRunId]
//        binding.run = run
//
//    }


    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if (e == null) {
            return
        }
        val curRunId = e.x.toInt()
        val run = runs[curRunId]

        val calendar = Calendar.getInstance().apply {
            timeInMillis = run.timestamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        tvDate.text = dateFormat.format(calendar.time)

        "${run.avgSpeedInKMH}km/h".also {
            tvAvgSpeed.text = it
        }
        "${run.distanceInMeters / 1000f}km".also {
            tvDistance.text = it
        }
        tvDuration.text =
            TrackingUtility.getFormattedStopWatchTime(
                run.timeInMillis
            )
        "${run.caloriesBurned}kcal".also {
            tvCaloriesBurned.text = it
        }
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2f, -height.toFloat())
    }
}