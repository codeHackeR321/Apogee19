package com.anenigmatic.apogee19.screens.menu.data.retrofit

import com.google.gson.annotations.SerializedName

class VendorsPojo
{
    var id : Int = 0
    @SerializedName("name")
    var vendor_name : String = ""
    @SerializedName("description")
    var stall_description : String = ""
    var menu : List<MenuPojo>? = null
    // var closed : Boolean = false
}