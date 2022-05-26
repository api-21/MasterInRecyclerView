package com.xpresscure.recyclerview


sealed class DataFactor{
    data class ItemData(val isActive : Int =0, val title:String, val subTitle:String) : DataFactor()
}

