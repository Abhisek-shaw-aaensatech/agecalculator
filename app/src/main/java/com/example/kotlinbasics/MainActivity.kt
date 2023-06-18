package com.example.kotlinbasics

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    private var tvSelectedDateView: TextView? = null
    private var tvageInMinutesView: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        OnClickTime()
        val button : Button=findViewById(R.id.button)
        tvSelectedDateView = findViewById(R.id.tvSelectedDate)
        tvageInMinutesView = findViewById(R.id.tvageInMinutes)

        button.setOnClickListener{view->
            clickbutton()
        }
    }
    fun clickbutton() {
        var myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
             { view, Selectedyear, Selectedmonth, Selecteddayofmonth ->
                Toast.makeText(this, "year was $Selectedyear, month was ${Selectedmonth+1}"+
                        ", day of month was ${Selecteddayofmonth}",
                    Toast.LENGTH_LONG).show()
                var SelectedDate ="$Selecteddayofmonth/${Selectedmonth+1}/$Selectedyear"
                 val sdf= SimpleDateFormat("dd/MM/yyyy")
                val theDate = sdf.parse(SelectedDate)
                 val SelectedDateInMinutes= theDate.time/60000
                 val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                 val currentDateInMinutes = currentDate.time/60000

                 val differenceInMinutes= currentDateInMinutes-SelectedDateInMinutes
                 tvageInMinutesView?.text =differenceInMinutes.toString()
                 if (differenceInMinutes>=0) {
                     var hours = differenceInMinutes / 60
                     var days = hours / 24
                     var years = days / 365
                     var r = days / 30.5
                     var months: Int = r.roundToInt()
                     var month = months - (years * 12)
                     var yourage = "$years year/${month} months/$days days"
                     tvSelectedDateView?.setText(yourage)

                 }
                 else {
                     var yourage = "Select valid date"
                     tvSelectedDateView?.setText(yourage)

                 }

             },
            year,month,day
        ).show()
    }
    private fun OnClickTime() {
        val textView = findViewById<TextView>(R.id.textViews)
        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        timePicker.setOnTimeChangedListener { _, hour, minute -> var hour = hour
            // AM_PM decider logic

            if (textView != null) {
                val hour = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute
                // display format of time
                val msg = "Time is: $hour : $min"
                textView.text = msg
                textView.visibility = ViewGroup.VISIBLE
            }
        }

    }
}