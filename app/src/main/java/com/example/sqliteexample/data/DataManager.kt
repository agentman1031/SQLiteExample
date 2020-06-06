package com.example.sqliteexample.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.sqliteexample.models.Customer
import java.lang.Exception

class DataManager(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

    companion object {
        /*Next, we have a private const strings for
        each row/table that we need to refer to just
        inside this class*/


        private const val DATABASE_NAME = "MyData.db"
        private const val DATABASE_VERSION = 1
        val CUSTOMERS_TABLE_NAME = "Customers"
        val COLUMN_CUSTOMERID = "Customerid"
        val COLUMN_CUSTOMERNAME = "Customername"
        val COLUMN_MAXCREDIT = "maxcredit"
    }

    // Is created when application is ran the first time
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CUSTOMERS_TABLE :String = ("CREATE TABLE $CUSTOMERS_TABLE_NAME (" +
                "$COLUMN_CUSTOMERID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CUSTOMERNAME TEXT," +
                "$COLUMN_MAXCREDIT DOUBLE DEFAULT 0)")
        db?.execSQL(CREATE_CUSTOMERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getCustomer(mCtx : Context) : ArrayList<Customer> {
        val qry = "Select * From $CUSTOMERS_TABLE_NAME"
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customer>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No Records Found,", Toast.LENGTH_SHORT).show() else {
            while (cursor.moveToNext()) {
                val customer = Customer()
                customer.customerID = cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMERID))
                customer.customerName = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERNAME))
                customer.maxCredit = cursor.getDouble(cursor.getColumnIndex(COLUMN_MAXCREDIT))
                customers.add(customer)
            }
            Toast.makeText(mCtx, "${cursor.count.toString()} Records Found", Toast.LENGTH_SHORT)
                .show()
        }
        cursor.close()
        db.close()
        return customers
    }

    fun addCustomer(mCtx: Context, customer: Customer){
        val values = ContentValues()
        values.put(COLUMN_CUSTOMERNAME, customer.customerName)
        values.put(COLUMN_MAXCREDIT, customer.maxCredit)
        val db: SQLiteDatabase = this.readableDatabase
        try{
            db.insert(CUSTOMERS_TABLE_NAME, null, values)
            Toast.makeText(mCtx, "Customer Added", Toast.LENGTH_SHORT).show()
        } catch (e: Exception){
            Toast.makeText(mCtx,e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

}