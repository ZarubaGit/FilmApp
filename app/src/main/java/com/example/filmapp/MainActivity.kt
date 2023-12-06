package com.example.filmapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val urlAdress = "https://imdb-api.com"

private val retrofit = Retrofit.Builder()
    .baseUrl(urlAdress)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private val imbdService = retrofit.create(MovieApi::class.java)

private lateinit var searchButton: Button
private lateinit var queryInput: EditText
private lateinit var movieList: RecyclerView
private lateinit var placeHolderMessage: TextView

private val movies = mutableListOf<Movie>()

private val adapter = MovieAdapter()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        placeHolderMessage = findViewById(R.id.placeholderMessage)
        movieList = findViewById(R.id.movieList)
        searchButton = findViewById(R.id.searchButton)
        queryInput = findViewById(R.id.queryInput)

        adapter.movies = movies
        movieList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        movieList.adapter = adapter

        searchButton.setOnClickListener{
            if(queryInput.text.isNotEmpty()){
                imbdService.findMovie(queryInput.text.toString()).enqueue(object :Callback<MovieResponse>{
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if(response.code() == 200){
                            movies.clear()
                            if(response.body()?.results?.isNotEmpty() == true){
                                movies.addAll(response.body()?.results!!)
                                adapter.notifyDataSetChanged()
                            }
                            if(movies.isEmpty()){
                                showMessage(getString(R.string.nothing_found), "")
                            }else {
                                showMessage("", "")
                            }
                        } else {
                            showMessage(getString(R.string.something_went_wrong), response.code()
                                .toString())
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        showMessage(getString(R.string.something_went_wrong), t.message
                            .toString())
                    }

                })
            }
        }

    }

    private fun showMessage(text: String, additionalMessage: String){
        if(text.isNotEmpty()){
            placeHolderMessage.visibility = View.VISIBLE
            movies.clear()
            adapter.notifyDataSetChanged()
            placeHolderMessage.text= text
            if(additionalMessage.isNotEmpty()){
                Toast.makeText(applicationContext, additionalMessage, Toast.LENGTH_LONG).show()
            }
        } else {
            placeHolderMessage.visibility = View.GONE
        }
    }
}
