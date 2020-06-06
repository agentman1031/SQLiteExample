package com.example.sqliteexample.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteexample.R
import com.example.sqliteexample.models.Customer
import kotlinx.android.synthetic.main.lo_customers.view.*

class CustomerAdapter(mCtx : Context, val customers : ArrayList<Customer>) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    val mCtx = mCtx

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val txtCustomerName = itemView.txtCustomerName
        val txtMaxCredit = itemView.txtMaxCredit
        val btnUpdate = itemView.btnUpdate
        val btnDelete = itemView.btnDelete
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): CustomerAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.lo_customers,p0,false) //todo p0 to parent?
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return customers.size

    }

    override fun onBindViewHolder(p0: CustomerAdapter.ViewHolder, p1: Int) { //todo p1 to position? p0 to holder?
        val customer : Customer = customers[p1]
        p0.txtCustomerName.text = customer.customerName
        p0.txtMaxCredit.text = customer.maxCredit.toString()

    }

}