package com.example.apbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://acharyaprashant.org/api/v2/legacy/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // for applying grid layout in recyclerview
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }


        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {

                if (response.isSuccessful) {
                    val responseBody = response.body()

                    responseBody?.let {
                        val adapter = MyAdapter(this@MainActivity, it)
                        recyclerView.adapter = adapter
                    }

                } else {
                    Log.d("MainActivity", "Response not successful: ${response.code()}")
                }

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("Main activity", "onFailure: " + t.message)
            }
        })
    }
}