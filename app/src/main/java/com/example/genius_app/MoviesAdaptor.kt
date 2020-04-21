package com.example.genius_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_movie.view.*


class MoviesAdaptor(
    var movies: List<Movie>, val itemClickHandler:(Int, List<Movie>)->Unit) : RecyclerView.Adapter<MoviesAdaptor.MovieViewHolder>() {



    class MovieViewHolder(val view: View ) : RecyclerView.ViewHolder(view)
    {


        fun onBind(text: String) {
            view.albumid.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieView = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_movie,
            parent,
            false


        )
        val movieViewHolder = MovieViewHolder(movieView )

        movieView.setOnClickListener {
         itemClickHandler.invoke(movieViewHolder.adapterPosition , movies)
        }
        return movieViewHolder
    }

    fun setData(itemList: List<Movie>) {
        movies = itemList
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movies[position]


        holder.view.albumid.text = movie.albumId.toString()
        holder.view.titlename.text = movie.title

        Glide.with(holder.view.context)
            .load(movie.url)
            .into(holder.view.imageurl)
    }


    override fun getItemCount(): Int {
        return movies.size
    }
    interface ListenerInterface {
        fun onClickEvent(position: Int)
        fun onFavoriteClick(position: Int)
    }
}