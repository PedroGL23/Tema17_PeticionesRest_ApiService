package com.example.tema17_peticionesrest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


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

        val selectedMovieTitle = view.findViewById<TextView>(R.id.prueba)
        selectedMovieTitle.text = selectedMovie?.title




    }


}