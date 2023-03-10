package com.example.tema17_peticionesrest

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelectedMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

           val selectedMovie =   if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {

            arguments?.getSerializable("selected movie", MoviesGenresResponse.Result::class.java)

        } else {

            arguments?.getSerializable("selected movie") as? MoviesGenresResponse.Result

        }

        Log.i("MENSAJEEEEEEEEEE", (selectedMovie!!.original_title))
        Log.i("MENSAJEEEEEEEEEE", (selectedMovie!!.poster_path))
        Log.i("LO COGEEEE???", (selectedMovie?.release_date.toString()))
        (activity as? AppCompatActivity)?.supportActionBar?.title = selectedMovie?.original_title


        val selectedMovieTitle = view.findViewById<TextView>(R.id.selectedMovieTitle)
        selectedMovieTitle.text = selectedMovie?.original_title

        val selectedMoviePoster = view.findViewById<ImageView>(R.id.selectedMoviePoster)
        val fullUrlMoviePoster = ApiRest.URL_IMAGES + selectedMovie.poster_path
        Log.i("LO COGEEEE???", (fullUrlMoviePoster))
        Picasso.get().load(fullUrlMoviePoster).into(selectedMoviePoster)



        val selectedMovieOverview = view.findViewById<TextView>(R.id.selectedMovieOverview)
        selectedMovieOverview.text = selectedMovie?.overview

        val selectedMovieDate = view.findViewById<TextView>(R.id.selectedMovieDate)
        selectedMovieDate.text = selectedMovie?.release_date



    }

}