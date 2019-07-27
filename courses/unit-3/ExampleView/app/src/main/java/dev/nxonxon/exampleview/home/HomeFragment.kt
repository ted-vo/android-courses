package dev.nxonxon.exampleview.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.nxonxon.exampleview.AssetLoader
import dev.nxonxon.exampleview.instagram.Newfeed
import dev.nxonxon.exampleview.instagram.NewfeedAdapter
import dev.nxonxon.exampleview.network.ApiFactory
import dev.nxonxon.exampleview.network.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    val newfeedAdapter: NewfeedAdapter by lazy {
        NewfeedAdapter(context!!, mutableListOf())
    }
    val apiFactory by lazy {
        ApiFactory({ result ->
            newfeedAdapter.updateDataSetChange(getNewFeedsItems(result))
            Toast.makeText(context, "Load data successfully", Toast.LENGTH_LONG).show()
        }, {
            Toast.makeText(context, "Error! Load data failure", Toast.LENGTH_LONG).show()
        })
    }
    val apiManager by lazy { ApiManager() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(dev.nxonxon.exampleview.R.layout.fragment_home, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvNewFeeds.run {
            layoutManager = LinearLayoutManager(context)
            adapter = newfeedAdapter
        }
//        val url = "http://www.mocky.io/v2/5d3274c033000044cb7ba643"
//
//        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
//        axpiFactory.execute(url)

        apiManager.getListNewFeed()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ items ->
                newfeedAdapter.updateDataSetChange(items)
                Toast.makeText(context, "Lay du lieu thanh cong", Toast.LENGTH_LONG).show()
            }, {
                Toast.makeText(context, "Co loi xay ra", Toast.LENGTH_LONG).show()
            })
    }

    private fun getNewFeedsItems(json: String? = null): List<Newfeed> {
        val jsonData = json ?: AssetLoader.getAsset(context, "newfeeds.json")
        val type = object : TypeToken<List<Newfeed>>() {}.type
        return Gson().fromJson(jsonData, type)
    }

}