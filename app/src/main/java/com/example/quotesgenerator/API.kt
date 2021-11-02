package com.example.quotesgenerator

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface API {
    @GET
    fun getQuotes(@Url url: String): Call<ArrayList<QuotesData>>
}
