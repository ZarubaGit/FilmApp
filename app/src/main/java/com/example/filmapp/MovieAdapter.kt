package com.example.filmapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(): RecyclerView.Adapter<MovideHolder>() {

    var movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovideHolder = MovideHolder(parent)


    override fun getItemCount(): Int  = movies.size


    override fun onBindViewHolder(holder: MovideHolder, position: Int) {
        holder.bind(movies.get(position))
    }

}