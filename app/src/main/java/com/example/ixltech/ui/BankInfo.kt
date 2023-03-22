package com.example.ixltech.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ixltech.databinding.ActivityBankInfoBinding
import com.example.ixltech.ui.db.MyAppDatabase
import com.example.ixltech.ui.db.MyRepository
import com.example.ixltech.ui.model.User
import com.example.ixltech.ui.viewmodel.MyViewModel


class BankInfo : AppCompatActivity() {

    private lateinit var binding: ActivityBankInfoBinding
    lateinit var spinner: Spinner
    lateinit var arrayAdapter:ArrayAdapter<String>
    val REQUEST_IMAGE_CAPTURE = 1
    var capturedBitmap: Bitmap? = null
  lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//         data from EmployeeInfo Activity

        val first_name=intent.getStringExtra("first_name")
        val last_name=intent.getStringExtra("last_name")
        val mobile_no=intent.getStringExtra("mobile_no")
        val gender=intent.getStringExtra("gender")
        val date_brth=intent.getStringExtra("dob")

        val emp_no=intent.getStringExtra("empNo")
        val emp_name=intent.getStringExtra("emp_name")
        val designation=intent.getStringExtra("designation")
        val accounts=intent.getStringExtra("accounts")
        val work_expereince=intent.getStringExtra("workexperience")

        val branchspinner = binding.branchSpinner

        val submitButton = binding.submit



//        val bundle = intent.extras
//                val emp_no=bundle?.getString("empNo")
//        val emp_name=bundle?.getString("emp_name")
//        val designation=bundle?.getString("designation")
//        val accounts=bundle?.getString("accounts")
//        val work_expereince=bundle?.getString("workexperience")

        Log.e("fname",first_name.toString())
        Log.e("lname",last_name.toString())
        Log.e("mobile_no",mobile_no.toString())
        Log.e("gender",gender.toString())
        Log.e("dob",date_brth.toString())
        Log.e("emp_no",emp_no.toString())
        Log.e("emp_name",emp_name.toString())
        Log.e("designation",designation.toString())
        Log.e("accounts",accounts.toString())
        Log.e("work_exp",work_expereince.toString())

        binding.btnCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }

        val branches = arrayOf("Pune", "Mumbai", "Chennai", "Kerala")
        spinner= binding.branchSpinner
        arrayAdapter = ArrayAdapter (this, android.R.layout.simple_spinner_item, branches)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        branchspinner.adapter = arrayAdapter

//        val adapter = ArrayAdapter.createFromResource(this, R.array.Accounts, android.R.layout.simple_spinner_item)
//
//        binding.accountsSpinner.adapter = adapter

        binding.branchSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        val userDao = MyAppDatabase.getDatabase(applicationContext).userDao()
        val userRepository = MyRepository(userDao)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MyViewModel::class.java)


        viewModel.getAllUser.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
//                noteRVAdapter.updateList(it)
            }
        })

        submitButton.setOnClickListener {
//
//            val bundle = Bundle()
//            bundle.putString("empNo", binding.empNo.text.toString())
//            bundle.putString("emp_name", binding.empName.text.toString())
//            bundle.putString("designation", binding.designation.text.toString())
//            bundle.putString("accounts", binding.accountsSpinner.selectedItem.toString())
//            bundle.putString("accounts", binding.workExperienceSpinner.selectedItem.toString())
            var selectedItem = branchspinner.selectedItem as String

            val bank_name = binding.bankName.text.toString()
            val branches = selectedItem
            val account_number = binding.accountNumber.text.toString()
            val ifsc_code = binding.ifscCode.text.toString()


            viewModel.insertUser(
                User(0,first_name!!,last_name!!,mobile_no!!,gender!!,date_brth!!,
            emp_no!!,emp_name!!,designation!!,accounts!!,work_expereince!!,capturedBitmap!!,bank_name,branches,account_number,ifsc_code
                )
            )


//            val intent = Intent(this, BankInfo::class.java)
//            intent.putExtras(bundle)
//            intent.putExtra("empNo", binding.empNo.text.toString())
//            intent.putExtra("emp_name", binding.empName.text.toString())
//            intent.putExtra("designation", binding.designation.text.toString())
//            intent.putExtra("accounts", selectedItem)
//            intent.putExtra("workexperience", selectedWorkExp)
//            startActivity(intent)
        }

    }



    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            capturedBitmap = imageBitmap
            binding.ivProfile.setImageBitmap(imageBitmap)


        }
    }
}