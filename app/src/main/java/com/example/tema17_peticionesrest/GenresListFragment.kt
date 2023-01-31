package com.example.tema17_peticionesrest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GenresListFragment : Fragment() {

    //private var loader: View? = null
    private var swiperefresh: SwipeRefreshLayout? = null
    private lateinit var rvGeneros: RecyclerView

    //Como variable de clase
    private var adapter: GenresAdapter? = null
    val TAG = "MainActivity "
    var data: ArrayList<GenresResponse.Genre> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //(activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.agents)

        rvGeneros = view.findViewById<RecyclerView>(R.id.rvGeneros)
        swiperefresh = view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh)

        //  loader era una ProgressionBar que hemos eliminado.
        //loader = findViewById<View>(R.id.loader)


        //  Mostrar como cuadricula
        val mLayoutManager = GridLayoutManager(context, 2)
        rvGeneros.layoutManager = mLayoutManager



        //  Creamos el adapter y lo vinculamos con el recycler
        adapter = GenresAdapter(data){ genre ->
            activity?.let{
                val fragment = MoviesByGenreFragment()

                fragment.arguments = Bundle()
                fragment.arguments?.putSerializable("genre", genre)
/*
                fragment.arguments = Bundle().apply {
                    putString("nombre", genre.name)
                }

*/

                Log.i("He pinchado sobre la celda", genre.name)
                it.supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, fragment).commit()
            }

        }
        rvGeneros.adapter = adapter


        ApiRest.initService()

        //  Aquí hago la petición al servicio.
        getGenres()

        getMovies()

        swiperefresh?.setOnClickListener{
            getGenres()

            getMovies()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generes_list, container, false)
    }



    private fun getMovies(){

        val call = ApiRest.service.getMovies("genero pulsado por el usuario")



    }

    private fun getGenres() {
        val call = ApiRest.service.getGenres()
        call.enqueue(object : Callback<GenresResponse> {
            override fun onResponse(
                call: Call<GenresResponse>,
                response: Response<GenresResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    data.clear()
                    data.addAll(body.genres)
                    adapter?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: "Error")
                }

                swiperefresh?.isRefreshing = false
                //loader?.isVisible = false
            }

            override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
                swiperefresh?.isRefreshing = false
                //loader?.isVisible = false
            }


        })
    }

}