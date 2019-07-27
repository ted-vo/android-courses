package dev.nxonxon.exampleview.network

import android.util.Log
import dev.nxonxon.exampleview.instagram.Newfeed
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager() {

    private val _apiServices: ApiHelper by lazy {
        getHelper().create(ApiHelper::class.java)
    }

    companion object {

        private var sInstance: Retrofit? = null

        fun getHelper(): Retrofit {
            if (sInstance == null) {
                sInstance = Retrofit.Builder()
                    .baseUrl("http://www.mocky.io/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return sInstance!!
        }
    }

    // middleware
    private fun <T : Any> buildRequest(call: Call<T>): Single<T> {
        return Single.create {
            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    Log.d("ERROR", "Loi")
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    it.onSuccess(response.body()!!)
                }
            })
        }
    }

    fun getListNewFeed(): Single<List<Newfeed>> {
        return buildRequest(_apiServices.getListNewFeed())
    }

    fun postNewFeed(loginRequest: RequestLogin) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}