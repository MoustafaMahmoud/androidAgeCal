package com.mostafa.agecal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectDate.setOnClickListener { view ->
            clickDatepicker( view )
        }
    }

    fun clickDatepicker(view:View){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        var dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
            view, year, month, dayOfMonth ->
            var selectedDate = "$year/${month+1}/$dayOfMonth"
            tvSelectedDate.setText(selectedDate)
            Toast.makeText(this, "$selectedDate", Toast.LENGTH_LONG).show()

            var sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            var theDate =sdf.parse(selectedDate)
            var selectedDateInMinutes= theDate!!.time / 60000

            var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            var currentDateMinutes = currentDate!!.time / 60000

            var diffInMuintes = currentDateMinutes - selectedDateInMinutes

            tvSelectedDateInMinutes.setText(diffInMuintes.toString())



        }, year, month, day)

        dpd.datePicker.setMaxDate(Date().time - 8640000000);
        dpd.show()


    }
}
