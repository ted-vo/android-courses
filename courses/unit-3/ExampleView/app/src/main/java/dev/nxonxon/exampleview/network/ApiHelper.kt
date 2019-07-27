package dev.nxonxon.exampleview.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import dev.nxonxon.exampleview.instagram.Newfeed
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiHelper {

    @GET("5d3274c033000044cb7ba643")
    fun getListNewFeed(): Call<List<Newfeed>>

    @POST("5d3274c033000044cb7ba643")
    fun postNewFeed(@Body loginRequest: RequestLogin)
}

data class RequestLogin(
    @Expose
    @SerializedName("username")
    var username: String? = null,

    @Expose
    @SerializedName("password")
    var password: String? = null
)