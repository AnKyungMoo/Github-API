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

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewAdapter = RecyclerViewAdapter(this)
        recycler_view.adapter = recyclerViewAdapter

        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.setHasFixedSize(true)

        getUserInfo("AnKyoungMoo")
        getUserRepo("AnkyoungMoo")
    }

    private fun getUserInfo(name: String) {
        subscription = ServiceModule.restAPI().userInfo(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.d("result", result.login)
                    recyclerViewAdapter.addItem(TitleModel(result.avatar_url, result.login, 0))
                },
                { err ->
                    Log.e("Error User",err.toString())
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

                    result.forEach{
                        recyclerViewAdapter.addItem(RecyclerViewModel(it.name, it.description, it.stargazers_count, 2))
                    }


                },
                { err ->
                    Log.e("Error ㅠㅠ",err.toString())
                }
            )
    }
}
