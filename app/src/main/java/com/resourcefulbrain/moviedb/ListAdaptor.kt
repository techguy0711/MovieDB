package com.resourcefulbrain.moviedb

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_list_row.view.*

class ListAdaptor(val feeed: Empty): RecyclerView.Adapter<CustomViewHolder>()  {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.activity_movie_list_row, p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        //return 1
        return feeed.feed.results.count()
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        val results =  feeed.feed.results.get(p1)
        p0.view.Movie_Title.text = results.name
        Picasso.get().load(results.artworkUrl100).into(p0.view.Poster_Image)
    }

}
class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    
}