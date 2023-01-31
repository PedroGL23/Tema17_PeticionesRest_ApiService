package com.example.tema17_peticionesrest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MoviesAdapter(private val data: ArrayList<MoviesResponse.Result>, val onClick:(MoviesResponse.Result)->Unit) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
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
        val card = itemView.findViewById<CardView>(R.id.moviePoster)

        fun bind(item: MoviesResponse.Result) {
            movieTitle.text = item.title
            val cardRoute = item.posterPath

            card.setOnClickListener {
                //Log.v("Pulso sobre", item.id.toString())
                onClick(item)
            }
        }

    }
}
