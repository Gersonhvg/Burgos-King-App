package com.burgosking.order.data.models

data class OrderData(
    var id: Int,
    var userId: Int,
    var date: String,
    val menus: MutableList<MenuData> = mutableListOf()
)
