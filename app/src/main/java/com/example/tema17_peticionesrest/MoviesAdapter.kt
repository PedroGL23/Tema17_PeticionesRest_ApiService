package com.example.tema17_peticionesrest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MoviesAdapter(private val data: ArrayList<MoviesGenresResponse.Result>, private val onMovieClick: (MoviesGenresResponse.Result) -> Unit) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieTitle = itemView.findViewById<TextView>(R.id.movieTitle)
        val card = itemView.findViewById<CardView>(R.id.cardMovies)
        val moviePoster = itemView.findViewById<ImageView>(R.id.moviePoster)

        fun bind(item: MoviesGenresResponse.Result) {
            movieTitle.text = item.title
            val urlPoster = ApiRest.URL_IMAGES + item.poster_path
            Picasso.get().load(urlPoster).into(moviePoster)
            itemView.setOnClickListener{
                onMovieClick(item)

            }



        }

    }
}
