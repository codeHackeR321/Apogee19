package com.anenigmatic.apogee19.screens.menu.data.retrofit

import com.anenigmatic.apogee19.User
import com.example.manish.apogeewallet.screens.menu.data.retrofit.StallAndMenu
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface StallsAndMenuApi {

    @Headers("X-Wallet-Token: ec123dac-339b-41ba-bca4-d3cab464083d")
    @GET("wallet/vendors")
    fun getStallsAndMenu() : Call<List<StallAndMenu>>

    @Headers("X-Wallet-Token: ec123dac-339b-41ba-bca4-d3cab464083d", "Authorization: " + User.jwt, "Content-Type: application/json")
    @POST("wallet/orders")
    fun placeOrder(@Body body: CartOrder) : Call<OrderComfirmation>

    @Headers("X-Wallet-Token: ec123dac-339b-41ba-bca4-d3cab464083d", "Authorization: " + User.jwt, "Content-Type: application/json")
    @GET("wallet/orders")
    fun getPastOrders() : Call<List<OrderShell>>

    @Headers("X-Wallet-Token: ec123dac-339b-41ba-bca4-d3cab464083d", "Authorization: " + User.jwt, "Content-Type: application/json")
    @POST("wallet/orders/make_otp_seen")
    fun requestOtpSeen(@Body body: RequestBody) : Call<Unit>

    @GET("kind-store/items")
    fun getKindStoreItems(): Call<Map<String,KindStoreItem>>
}