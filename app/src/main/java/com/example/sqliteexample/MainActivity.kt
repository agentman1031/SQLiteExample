package com.example.sqliteexample

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteexample.data.CustomerAdapter
import com.example.sqliteexample.data.DataManager
import com.example.sqliteexample.models.Customer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var dataManager: DataManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataManager = DataManager(this, null, null,1)

        viewCustomers()

        fab.setOnClickListener{
            val i = Intent(this, AddCustomerActivity::class.java)
            startActivity(i)
        }

    }

    private fun viewCustomers() {
        val customerslist :ArrayList<Customer> = dataManager.getCustomer(this)
        val adapter = CustomerAdapter(this,customerslist)
        val rv : RecyclerView = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    override fun onResume() {
        viewCustomers()
        super.onResume()
    }

}