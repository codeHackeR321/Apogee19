package com.anenigmatic.apogee19.screens.menu.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.anenigmatic.apogee19.screens.menu.core.StallsViewModel
import com.anenigmatic.apogee19.screens.menu.data.retrofit.StallsApi
import com.anenigmatic.apogee19.screens.menu.data.retrofit.VendorsPojo
import com.anenigmatic.apogee19.screens.menu.data.room.Stall
import com.anenigmatic.apogee19.screens.menu.data.room.StallItem
import com.anenigmatic.apogee19.screens.menu.data.room.StallsAndMenuDataBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StallsRepositoryImpl : StallsRepository
{
    lateinit var currentcontext :Context
    lateinit var database : StallsAndMenuDataBase

    @Volatile
    private var sSoleInstance: StallsRepositoryImpl? = null

    private fun SingletonClass() {

        //Prevent form the reflection api.
        if (sSoleInstance != null) {
            throw RuntimeException("Use getInstance() method to get the single instance of this class.")
        }
    }

    fun getInstance(): StallsRepositoryImpl {
        //Double check locking pattern
        if (sSoleInstance == null) { //Check for the first time

            synchronized(StallsRepositoryImpl::class.java) {
                //Check for the second time.
                //if there is no instance available... create new one
                if (sSoleInstance == null) sSoleInstance = StallsRepositoryImpl()
            }
        }

        return sSoleInstance!!
    }

    /**Gets the new data from the server and informs the view holder about it*/
    override fun getStalls(context : Context , viewModel: StallsViewModel) {
        currentcontext = context
        database = Room.databaseBuilder(currentcontext , StallsAndMenuDataBase::class.java , "wallet-database.db").allowMainThreadQueries().build()
        val retrofit = Retrofit.Builder().baseUrl("http://139.59.64.214/wallet/").addConverterFactory(
            GsonConverterFactory.create()).build()
        val apiSrevice = retrofit.create(StallsApi::class.java)
        var call = apiSrevice.getVendorDetails()
        call.enqueue(object : Callback<List<VendorsPojo>> {

            override fun onResponse(call: Call<List<VendorsPojo>>, response: Response<List<VendorsPojo>>) {
                database.stallDao().deleteAll()
                database.stallItemDao().deleteAll()
                var responseBody = response.body()
                var StallList : ArrayList<Stall> = ArrayList(responseBody!!.size)
                var menuList : ArrayList<StallItem> = ArrayList(responseBody!!.size)
                Log.d("Test" , "Size of response = ${responseBody}")
                responseBody!!.forEach {
                    StallList.add(Stall(it.id , it.vendor_name , it.stall_description , false))
                    it.menu!!.forEach {menu ->
                        menuList.add(StallItem(menu.id , menu.vendor_id , menu.item_name , menu.price))
                    }
                }
                //StallsViewModel().stallList.value = StallList
                // currentVendorsList.value = StallList
                /*Log.d("Test" , "Pass variable = ${currentVendorsList.value}")
                Log.d("Test" , "Pass2 variable = ${currentMenuList.value}")*/
                Log.d("Test" , "Pass variable = ${StallList}")
                Log.d("Test" , "Pass2 variable = ${menuList}")

                database.stallDao().insertAll(StallList)
                database.stallItemDao().insertAll(menuList)

                viewModel.onStallDataAddedToLocalDatabase(StallList)
                //fragment.newVendorsAdded(StallList)
            }
            override fun onFailure(call: Call<List<VendorsPojo>>, t: Throwable) {
                Log.d("Test" , "Failed to call endpoint ${t}")
            }

        })
    }

    /**Retervies the cached data from room*/
    override fun getCachedData(context : Context , viewModel: StallsViewModel) {
        database = Room.databaseBuilder(context , StallsAndMenuDataBase::class.java , "wallet-database.db").allowMainThreadQueries().build()
        var list = database.stallDao().getAll()
        if (list.size!=0)
           viewModel.onStallDataAddedToLocalDatabase(list)
    }

    /**Retrieves the corresponding menu from room*/
    override fun getMenu(stall_id : Int) : List<StallItem> {
        return database.stallItemDao().getStallItems(stall_id)
    }

    override fun addItemToCart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun placeOrder() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}