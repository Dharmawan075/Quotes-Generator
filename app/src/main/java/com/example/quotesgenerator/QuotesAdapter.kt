package com.example.quotesgenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.quotes.view.*

class QuotesAdapter(private val list: ArrayList<QuotesData>): RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {
    inner class QuotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(quotesData: QuotesData) {
            with(itemView) {
                val text: String = "${quotesData.q}\n" +
                    "~ ${quotesData.a}\n "

                quotesTextView.text = text
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quotes, parent, false)
        return QuotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
