package com.anenigmatic.apogee19.screens.menu.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.anenigmatic.apogee19.screens.menu.data.retrofit.OrderComfirmation
import com.anenigmatic.apogee19.screens.menu.data.room.CartItem
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import com.anenigmatic.apogee19.screens.menu.data.room.StallItem
import com.anenigmatic.apogee19.screens.menu.data.room.StallsAndMenuDataBase
import com.example.manish.apogeewallet.screens.menu.data.retrofit.CartOrder
import com.example.manish.apogeewallet.screens.menu.data.retrofit.StallAndMenu
import com.example.manish.apogeewallet.screens.menu.data.retrofit.StallsAndMenuApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuRepositoryImpl(context: Context) : MenuRepository {

    var database = Room.databaseBuilder(context , StallsAndMenuDataBase::class.java , "wallet-database.db")
        .allowMainThreadQueries().fallbackToDestructiveMigration().build()
    var stallDao = database.stallDao()
    var stallItemDao = database.stallItemDao()
    var cartItemDao = database.cartItemDao()
    var retrofit = Retrofit.Builder().baseUrl("http://139.59.64.214/wallet/").addConverterFactory(
        GsonConverterFactory.create()).build()
    var apiService = retrofit.create(StallsAndMenuApi::class.java)

    override fun getStalls(): LiveData<List<Stall>> {
        return stallDao.getAll()
    }

    override fun getMenu(stall: Stall): LiveData<List<StallItem>> {
        return getMenu(stall.stallId)
    }

    override fun getMenu(stallId: Int): LiveData<List<StallItem>> {
        // Log.d("Test" , "Database Data = ${stallItemDao.getStallItemsList(stallId)}")
        return stallItemDao.getStallItems(stallId)
    }

    override fun refreshStallAndMenu() {
        var call = apiService.getStallsAndMenu()
        call.enqueue(object : Callback<List<StallAndMenu>> {

            override fun onResponse(call: Call<List<StallAndMenu>>, response: Response<List<StallAndMenu>>) {

                if(!response.isSuccessful){
                    Log.d("Menu call response code", response.code().toString())
                    return
                }

                stallDao.deleteAll()
                stallItemDao.deleteAll()

                var responseBody = response.body()
                var stallList : ArrayList<Stall> = ArrayList(responseBody!!.size)
                var menuList : ArrayList<StallItem> = ArrayList(responseBody!!.size)
                Log.d("Test" , "Size of response = $responseBody")
                responseBody!!.forEach {
                    stallList.add(Stall(it.stallId , it.name , it.description , it.isClosed))
                    it.menu!!.forEach { stallItem ->
                        menuList.add(StallItem(stallItem.itemId , stallItem.stallId, stallItem.name, stallItem.price , stallItem.isAvailable))
                    }
                }

                Log.d("Test" , "Pass variable = $stallList")
                Log.d("Test" , "Pass2 variable = $menuList")

                stallDao.insertAll(stallList)
                stallItemDao.insertAll(menuList)

            }
            override fun onFailure(call: Call<List<StallAndMenu>>, t: Throwable) {
                Log.d("Menu call response" , "" + t.message.toString())
            }

        })
    }

    override fun addItemToCart(stallItem: StallItem, quantity: Int) {
        var cartItem = CartItem(stallItem.itemId, stallItem.stallId, stallItem.name, stallItem.price, quantity)
        addItemToCart(cartItem)
    }

    override fun addItemToCart(newCartItem: CartItem) {
        //TODO: Make it more efficient
        //checks of the current item already exists in the cart and updates it if so
        var cartItemList = cartItemDao.getCart()
        if( cartItemList.any { it.itemId == newCartItem.itemId }) {
            var cartItem = cartItemList.find { it.itemId == newCartItem.itemId }
            cartItemDao.changeQuantity(cartItem!!.itemId, cartItem!!.quantity + newCartItem.quantity)
            return
        }

        cartItemDao.insertCartItem(newCartItem)

    }

    override fun getCartItems(): LiveData<List<CartItem>> {
        return cartItemDao.getAll()
    }

    override fun placeOrder() {
//        var cartItemList = cartItemDao.getCart()
//        var requestBody = JSONObject()
//        requestBody.put("orderdict", JSONObject())
//
//        for(cartItem in cartItemList) {
//            var stallId = cartItem.stallId.toString()
//            if(!requestBody.has(stallId))
//                requestBody.getJSONObject("orderdict").put(stallId,JSONObject())
//            var itemId = cartItem.itemId.toString()
//            requestBody.getJSONObject("orderdict").getJSONObject(stallId).put(itemId, cartItem.quantity)
//        }

        var cartItemList = cartItemDao.getCart()
        var orderItems = HashMap<String,HashMap<String,Int>>()
        var cartItemStallId = ""
        for (cartItem in cartItemList) {
            cartItemStallId = cartItem.stallId.toString()
            if (!orderItems.containsKey(cartItemStallId)) {
                orderItems.put(cartItemStallId,HashMap())
            }
            orderItems.get(cartItemStallId)!!.put(cartItem.itemId.toString(),cartItem.quantity)
        }

        val cartOrder = CartOrder(orderItems)

        var responseCode = -1
        var call = apiService.placeOrder(cartOrder)
        call.enqueue(object : Callback<OrderComfirmation> {

            override fun onResponse(call: Call<OrderComfirmation>, response: Response<OrderComfirmation>) {
                Log.d("Test",call.request().body().toString())
                responseCode = response.code()
                if (responseCode == 200)
                {
                    cartItemDao.deleteAll()
                    Log.d("Test" , "Order Placed Sucessfully")
                }
                else
                    Log.d("Test" , "Order Placed UnSucessfully $responseCode")
            }

            override fun onFailure(call: Call<OrderComfirmation>, t: Throwable) {
                Log.d("Test" , "Order Placed UnSucessfully $t")
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

    /*fun try_getMenu(stall : Int) : List<StallItem>
    {
        return stallItemDao.getStallItemsList(stall)
    }*/

    override fun deleteCartItem(item: CartItem) {
        cartItemDao.delete(item)
    }


}