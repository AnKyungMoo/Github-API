package com.km.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = arrayListOf(
            TitleModel("image", "name"),
            RecyclerViewModel("name1", "description1", 5),
            RecyclerViewModel("name2", "description3", 1)
        )

        val recyclerViewAdapter = RecyclerViewAdapter(this, list)
        recycler_view.adapter = recyclerViewAdapter

        // scroll type: horizontal or vertical
        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
    }
}
