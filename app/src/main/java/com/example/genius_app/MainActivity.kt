package com.example.genius_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(){


    private var editsearch: SearchView? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshLayout.setOnRefreshListener {
            fetchMovies()
        }
        fetchMovies()
    }


    private fun fetchMovies() {
        val database = Firebase.database
        val myRef = database.getReference("Album Details" +
                "" +
                "")


        refreshLayout.isRefreshing = true
        MoviesApi().getMovies().enqueue(object : Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                refreshLayout.isRefreshing = false
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                refreshLayout.isRefreshing = false
                val movies = response.body()

                print(movies?.size)
                movies?.let {
                    myRef.setValue(movies)
                    showMovies(it)
                }
            }


        })

    }



    private fun showMovies(movies: List<Movie>): List<Movie> {
        recycler_view.layoutManager = LinearLayoutManager(this)
       recycler_view.adapter = MoviesAdaptor(movies, this@MainActivity::onItemClickHandler)
        return movies
    }



    private fun onItemClickHandler(position:Int, movies :List<Movie>){


        Log.d("***","$position");
        val intent = Intent(this,MovieDetails::class.java)
        intent.putExtra("position" , position)
        intent.putExtra("albumid" , movies[position].albumId)
        intent.putExtra("albumid" , movies[position].title)
        intent.putExtra("url" , movies[position].url)

        
        startActivity(intent)

        //here you can start a new intent to open a new activity on click of item

    }
    interface ItemClickListner {
        fun onClick(itemId: String?)
    }
}




