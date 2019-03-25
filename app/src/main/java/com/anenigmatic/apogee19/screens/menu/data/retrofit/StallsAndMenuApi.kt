package com.example.manish.apogeewallet.screens.menu.data.retrofit

import com.anenigmatic.apogee19.User
import com.anenigmatic.apogee19.screens.menu.data.retrofit.Order
import com.anenigmatic.apogee19.screens.menu.data.retrofit.OrderComfirmation
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface StallsAndMenuApi {

    @Headers("X-Wallet-Token: samp1e_token")
    @GET("vendors")
    fun getStallsAndMenu() : Call<List<StallAndMenu>>

    @Headers("X-Wallet-Token: samp1e_token", "Authorization: " + User.jwt, "Content-Type: application/json")
    @POST("orders")
    fun placeOrder(@Body body: CartOrder) : Call<OrderComfirmation>

    @Headers("X-Wallet-Token: samp1e_token", "Authorization: " + User.jwt, "Content-Type: application/json")
    @GET("orders")
    fun getPastOrders() : Call<List<Order>>
}