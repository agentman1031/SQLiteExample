package com.example.sqliteexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqliteexample.models.Customer
import kotlinx.android.synthetic.main.activity_add_customer.*

class AddCustomerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        btnSave.setOnClickListener{
            if (editCustomerName.text.isEmpty()){
                Toast.makeText(this, "Enter Customer Name", Toast.LENGTH_SHORT).show()
                editCustomerName.requestFocus()
            } else {
                val customer = Customer()
                customer.customerName = editCustomerName.text.toString()
                if (editMaxCredit.text.isEmpty())
                    customer.maxCredit = 0.0 else
                    customer.maxCredit = editMaxCredit.text.toString().toDouble()
                MainActivity.dataManager.addCustomer(this, customer)
                clearEdits()
                editCustomerName.requestFocus()
            }

        }

        btnCancle.setOnClickListener{
            clearEdits()
            finish()
        }
    }

    fun clearEdits(){
        editCustomerName.text.clear()
        editMaxCredit.text.clear()
    }
}