package com.example.filmapp


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MovideHolder(view: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(view.context)
    .inflate(R.layout.holder_film,view, false)){

    var image: ImageView = itemView.findViewById(R.id.image)
    var title: TextView = itemView.findViewById(R.id.title)
    var description: TextView = itemView.findViewById(R.id.description)

    fun bind(movie: Movie){
        Glide.with(itemView)
            .load(movie.image)
            .into(image)

        title.text = movie.title
        description.text = movie.description
    }

}