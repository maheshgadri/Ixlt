package com.example.ixltech.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.ixltech.R
import com.example.ixltech.databinding.ActivityEmployeeInfoBinding
import com.example.ixltech.databinding.ActivityMainBinding

class EmployeeInfo : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeInfoBinding

     lateinit var spinner: Spinner
    lateinit var arrayAdapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val accountspinner = binding.accountsSpinner
        val workexperiencepsinner=binding.workExperienceSpinner
        val submitButton = binding.submit
        // data from PersonalInfo Activity
        val first_name=intent.getStringExtra("first_name")
        val last_name=intent.getStringExtra("last_name")
        val mobile_no=intent.getStringExtra("mobile_no")
        val gender=intent.getStringExtra("gender")
        val date_brth=intent.getStringExtra("dob")

        Log.e("fname",first_name.toString())
        Log.e("lname",last_name.toString())
        Log.e("mobile_no",mobile_no.toString())
        Log.e("gender",gender.toString())
        Log.e("dob",date_brth.toString())


        val accounts = arrayOf("Saving", "CC", "OD", "RR")
        spinner= binding.accountsSpinner
         arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, accounts)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        accountspinner.adapter = arrayAdapter

//        val adapter = ArrayAdapter.createFromResource(this, R.array.Accounts, android.R.layout.simple_spinner_item)
//
//        binding.accountsSpinner.adapter = adapter

        binding.accountsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
//                val intent = Intent(this@EmployeeInfo, BankInfo::class.java)
//                intent.putExtra("selectedItem", selectedItem)
//                startActivity(intent)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }


        val workexp = arrayOf("0-1", "1-3", "3-5", "5-8")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, workexp)
        workexperiencepsinner.adapter = arrayAdapter
//        val adapter = ArrayAdapter.createFromResource(this, R.array.Accounts, android.R.layout.simple_spinner_item)
//
//        binding.workExperienceSpinner.adapter = adapter

        binding.workExperienceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var selectedWorkExp = parent?.getItemAtPosition(position).toString()
//                val intent = Intent(this@EmployeeInfo, BankInfo::class.java)
//                intent.putExtra("selectedItem", selectedWorkExp)
//                startActivity(intent)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        submitButton.setOnClickListener {

            var selectedItem = accountspinner.selectedItem as String
            var selectedWorkExp=workexperiencepsinner.selectedItem as String


            val intent = Intent(this, BankInfo::class.java)

            intent.putExtra("empNo", binding.empNo.text.toString())
            intent.putExtra("emp_name", binding.empName.text.toString())
            intent.putExtra("designation", binding.designation.text.toString())
            intent.putExtra("accounts", selectedItem)
            intent.putExtra("workexperience", selectedWorkExp)
            intent.putExtra("first_name", first_name)
            intent.putExtra("last_name", last_name)
            intent.putExtra("mobile_no", mobile_no)
            intent.putExtra("gender", gender)
            intent.putExtra("dob",date_brth)
            startActivity(intent)

            Toast.makeText(this,"Data Send to Bank Info Screen", Toast.LENGTH_LONG).show()
        }
    }







}