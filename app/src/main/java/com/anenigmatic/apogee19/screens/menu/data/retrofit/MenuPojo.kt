package com.anenigmatic.apogee19.screens.menu.data.retrofit

import com.google.gson.annotations.SerializedName

class MenuPojo
{
    var vendor_id : Int = 0
    @SerializedName("name")
    var item_name : String = ""
    var price : Int = 0
    var id : Int = 0
}