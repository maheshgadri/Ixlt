package com.example.ixltech.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.ixltech.R
import com.example.ixltech.databinding.ActivityPersonalInfoBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


class PersonalInfo : AppCompatActivity(){

    lateinit var binding: ActivityPersonalInfoBinding

    lateinit var   date:TextView
    lateinit var   first_name:TextView
    lateinit var  last_name:TextView
    lateinit var  phone_no:TextView
    lateinit var   date_of_birth:TextView





    private lateinit var gender_group: RadioGroup
//    private var gender_button: RadioButton? = null
    private val select_date: Button? = null

    private var c: Calendar? = null
    private var dpd: DatePickerDialog? = null

    var filter = InputFilter { source, start, end, dest, dstart, dend ->
        for (i in start until end) {
            if (!Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]*").matcher(
                    source[i].toString()
                ).matches()
            ) {
                return@InputFilter ""
            }
        }
        null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        gender_group = findViewById(R.id.genderGroup)



        binding.selectDob.setOnClickListener {

            val dateOfBirthEditText = binding.dateOfBirth// assuming you have ViewBinding set up
            val calendar = Calendar.getInstance()

            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Update EditText with selected date
                val selectedDate = "${dayOfMonth}/${monthOfYear + 1}/${year}"
                dateOfBirthEditText.setText(selectedDate)
            }

            val datePickerDialog = DatePickerDialog(this, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

// Show the dialog
            datePickerDialog.show()
        }


        binding.dateOfBirth.setOnClickListener {

            c = Calendar.getInstance()
            val day: Int = c!!.get(Calendar.DAY_OF_MONTH)
            val month: Int = c!!.get(Calendar.MONTH)
            val year: Int = c!!.get(Calendar.YEAR)

            dpd = DatePickerDialog(this@PersonalInfo,
                { view, year, month, dayOfMonth -> date?.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year) },
                day,
                month,
                year
            )
            dpd!!.show()
        }

        binding.submitBtn.setOnClickListener {
            val selected_gender = gender_group!!.checkedRadioButtonId
           val  gender_button = findViewById<RadioButton>(selected_gender)
            val selectedData = gender_button.text.toString()
            val intent = Intent(this, EmployeeInfo::class.java)
            intent.putExtra("first_name", binding.firstName.text.toString())
            intent.putExtra("last_name", binding.lastName.text.toString())
            intent.putExtra("mobile_no", binding.mobileNumber.text.toString())
            intent.putExtra("gender", selectedData)
            intent.putExtra("dob",binding.dateOfBirth.text.toString())
            startActivity(intent)
                // add more form data as needed...
            Toast.makeText(this,"Data Send to Employee Info Screen",Toast.LENGTH_LONG).show()
        }


    }




}


