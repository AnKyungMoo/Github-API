package com.km.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var subscription: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = arrayListOf(
            TitleModel("image", "name", 0),
            RecyclerViewModel("name1", "description1", 5, 1),
            RecyclerViewModel("name2", "description3", 1, 1)
        )

        val recyclerViewAdapter = RecyclerViewAdapter(this, list)
        recycler_view.adapter = recyclerViewAdapter

        // scroll type: horizontal or vertical
        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager

        getUserInfo("AnkyoungMoo")
        getUserRepo("AnkyoungMoo")
    }

    private fun getUserInfo(name: String) {

        subscription = ServiceModule.restAPI().userInfo(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.d("result", result.name)
                },
                { err ->
                    Log.d("Error",err.toString())
                }
            )
    }

    private fun getUserRepo(name: String) {
        subscription = ServiceModule.restAPI().userRepo(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.d("result", result[0].name)
                },
                { err ->
                    Log.d("Error",err.toString())
                }
            )
    }
}
