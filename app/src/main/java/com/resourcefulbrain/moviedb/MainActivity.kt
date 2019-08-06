package com.resourcefulbrain.moviedb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MovieList.layoutManager = LinearLayoutManager(this)
        fetchJSON()
    }
    fun fetchJSON() {
        val url = "https://rss.itunes.apple.com/api/v1/us/movies/top-movies/all/100/explicit.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println("\n\n" + body + "\n\n")
                val gson = GsonBuilder().create()
                val feed = gson.fromJson(body, Empty::class.java)
                 runOnUiThread {
                    MovieList.adapter = ListAdaptor(feed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }

}
//JSON Parsing classes
class Empty(val feed: Feed)
class Feed(val results: List<Results>)
class Results(val name: String, val artworkUrl100: String)