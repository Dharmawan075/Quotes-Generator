package com.example.quotesgenerator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val list = ArrayList<QuotesData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showQuotes()
    }

    private fun showQuotes() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        Client.instance.getQuotes("quotes").enqueue(object : Callback<ArrayList<QuotesData>> {
            override fun onResponse(call: Call<ArrayList<QuotesData>>, response: Response<ArrayList<QuotesData>>) {
                response.body()?.let { list.addAll(it) }

                val showQuotesAdapter = QuotesAdapter(list)
                recyclerView.adapter = showQuotesAdapter
            }

            override fun onFailure(call: Call<ArrayList<QuotesData>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun refreshActivity(view: View) {
        finish()
        overridePendingTransition(0, 0)
        startActivity(getIntent());
        overridePendingTransition(0, 0)
    }
}