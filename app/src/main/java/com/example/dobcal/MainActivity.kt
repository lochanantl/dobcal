package com.example.dobcal

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
   private var a : TextView?=null
    private var x : TextView?=null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonvariable : Button = findViewById(R.id.btnDatePicker)
        a = findViewById(R.id.tvselecteddate)
        x = findViewById(R.id.tvageinminutes)
        buttonvariable.setOnClickListener {
            clickDatePicker() }
    }
    private fun clickDatePicker()
    {
        val mycalandar = Calendar.getInstance()
        val year = mycalandar.get(Calendar.YEAR)
        val month = mycalandar.get(Calendar.MONTH)
        val day = mycalandar.get(Calendar.DAY_OF_MONTH)
        val z =DatePickerDialog(this,DatePickerDialog.OnDateSetListener {_,selectedyear, selectedmonth, selectedDayofmonth ->
            Toast.makeText(this, "Year Was $selectedyear,Month Was ${selectedmonth+1},Day Of Month $selectedDayofmonth", Toast.LENGTH_LONG).show()

            val b = "$selectedDayofmonth/${selectedmonth+1}/$selectedyear"
            a?.setText(b)
            val c = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val d = c.parse(b)
            d?.let {
                val selecteddateinminutes =d.time/60000
                val currentDate = c.parse(c.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time/60000
                    val differenceInMinutes = currentDateInMinutes-selecteddateinminutes
                    x?.text= differenceInMinutes.toString()
                }


            }





        },

            year,
            month,
            day
        )
        z.datePicker.maxDate= System.currentTimeMillis()-86400000
        z.show()
    }
}
