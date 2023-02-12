package com.example.tema17_peticionesrest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesByGenreFragment : Fragment() {

    private lateinit var adapter: MoviesAdapter
    var data: ArrayList<MoviesGenresResponse.Result> = ArrayList()
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


        val pelicula =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {

                arguments?.getSerializable("genre", GenresResponse.Genre::class.java)

            } else {

                arguments?.getSerializable("genre") as? GenresResponse.Genre

            }

        (activity as? AppCompatActivity)?.supportActionBar?.title = pelicula?.name



        rvPeliculas = view.findViewById(R.id.rvMovies)
        val mLayoutManager = GridLayoutManager(context, 2)
        rvPeliculas.layoutManager = mLayoutManager
        adapter = MoviesAdapter(data){ selectedMovie ->

            activity?.let{

                val fragment = SelectedMovieFragment()
                fragment.arguments = Bundle()
                fragment.arguments?.putSerializable("selected movie", selectedMovie)

                it.supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, fragment).commit()
            }

        }
        //  Vinculamos el recycler con el adapter.
        rvPeliculas.adapter = adapter

        ApiRest.initService()
        getMovies(pelicula)

        }

    private fun getMovies(film: GenresResponse.Genre?) {
        val call = ApiRest.service.getMovies(with_genres = film?.id.toString())
        call.enqueue(object : Callback<MoviesGenresResponse> {
            override fun onResponse(call: Call<MoviesGenresResponse>, response: Response<MoviesGenresResponse>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(ContentValues.TAG, body.toString())
                    data.clear()
                    data.addAll(body.results)
                    // Imprimir aqui el listado con logs
                    adapter?.notifyDataSetChanged()
                } else {
                    Log.e(ContentValues.TAG, response.errorBody()?.string()?:"Error")
                }
            }
            override fun onFailure(call: Call<MoviesGenresResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, t.message.toString())
            }
        })
    }





    }

