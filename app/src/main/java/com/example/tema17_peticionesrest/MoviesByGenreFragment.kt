package com.example.tema17_peticionesrest

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class MoviesByGenreFragment : Fragment() {

    private var adapter: MoviesAdapter? = null
    var data: ArrayList<MoviesResponse.Result> = ArrayList()
    val TAG = "MainActivity"
    private var loader: View? = null
    private lateinit var rvPeliculas: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_by_genre, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        val nombre = arguments?.getString("nombre")
        Log.i("GenresListFragment está enviando.......", nombre.toString())
        view.findViewById<TextView>(R.id.prueba).text = nombre
        */

/*
        val parametroEnviado =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {

                arguments?.getSerializable("genre", GenresResponse.Genre::class.java)

            } else {

                arguments?.getSerializable("genre") as? GenresResponse.Genre

            }

        (activity as? AppCompatActivity)?.supportActionBar?.title = parametroEnviado?.name
*/


        rvPeliculas = view.findViewById<RecyclerView>(R.id.rvMovies)
        val mLayoutManager = GridLayoutManager(context, 3)
        rvPeliculas.layoutManager = mLayoutManager
        //Creamos el adapter y lo vinculamos con el recycler
        adapter = MoviesAdapter(data) { agent->

            activity?.let{


                val fragment = DetailAgentsFragment()
                fragment.arguments = Bundle()
                fragment.arguments?.putSerializable("agent",agent)


                it.supportFragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.container,fragment).commit()
            }

        }
        rvAgentes.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvAgentes)

        ApiRest.initService()
        getGenres()



    }
}
