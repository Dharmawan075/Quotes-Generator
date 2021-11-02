package com.example.quotesgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    val list = ArrayList<QuotesData>()
    lateinit var generateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showQuotes()
    }

    private fun showQuotes() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        Client.instance.getQuotes("quotes").enqueue(object : Callback<ArrayList<QuotesData>> {
            override fun onResponse(
                call: Call<ArrayList<QuotesData>>,
                response: Response<ArrayList<QuotesData>>
            ) {
                response.body()?.let { list.addAll(it) }

                val showQuotesAdapter = QuotesAdapter(list)
                recyclerView.adapter = showQuotesAdapter
            }

            override fun onFailure(call: Call<ArrayList<QuotesData>>, t: Throwable) {

            }

        })
    }

    fun refreshActivity(view: View) {
        finish()
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}