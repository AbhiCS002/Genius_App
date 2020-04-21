package com.example.genius_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_movie.view.*

class MovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        var intent = intent
        val position = intent.getIntExtra("position", 0)
        var url = intent.getStringExtra("url")
        val albumid = intent.getStringExtra("albumid")
        val titles = intent.getStringExtra("title")

        val albumTextView= findViewById<TextView>(R.id.albumid)
        albumTextView.text = albumid.toString()
//        val urlimageview = findViewById<ImageView>(R.id.imageurl)
//        val titleTextView = findViewById<TextView>(R.id.titlename)
//
//        albumTextView.text = albumid.toString()
//        // urlimageview.imageurl = url.toString()
//        titleTextView.text = titles.toString()

    }
}
