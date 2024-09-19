package edu.tcu.hienminhdau.ageinminutes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dateBtn: Button = findViewById(R.id.button)
        dateBtn.setOnClickListener{handleDatePicker()}
    }

    private fun handleDatePicker(){
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DATE)
        val currentDateInMs = calendar.timeInMillis
        val  currentDateInMin = currentDateInMs / 60000

        val dialog = DatePickerDialog(this, {
            _, selectedYear, selectedMonth, selectedDay ->
            val selectedDateTv: TextView = findViewById(R.id.selectedDateTv)
            val selectedText = "${selectedMonth + 1}/$selectedDay/$selectedYear"
            selectedDateTv.text = selectedText

            calendar.set(selectedYear, selectedMonth, selectedDay)
            val setDateInMs = calendar.timeInMillis
            val setDateInMin = setDateInMs / 60000

            val minutesTv: TextView = findViewById(R.id.minutesTv)
            minutesTv.text = "${currentDateInMin - setDateInMin}"
        }, currentYear, currentMonth, currentDay)
        dialog.datePicker.maxDate = currentDateInMs
        dialog.show()
    }
}