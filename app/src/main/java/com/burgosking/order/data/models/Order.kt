package com.burgosking.order.data.models

data class Order(
    var id: Int,
    var userId: Int,
    var date: String,
    val menus: MutableList<Menu> = mutableListOf()
)
