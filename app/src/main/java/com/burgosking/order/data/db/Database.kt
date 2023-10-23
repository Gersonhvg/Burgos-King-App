package com.burgosking.order.data.db

import com.burgosking.order.data.models.Menu
import com.burgosking.order.data.models.Order
import com.burgosking.order.data.models.User

object database {

    var session:Int=0

    val menuList = mutableListOf(
        Menu(1, "1 Pollo", "https://recetascocinaperuana.com/wp-content/uploads/2020/05/pollo-a-la-brasa.jpg", 60.00),
        Menu(2, "1/2 Pollo", "https://elcomercio.pe/resizer/FXq5t1BQrfZpGjRKKNu6p1fmsnE=/580x330/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/KHNXT4CV6NCJTHWTRFREIWTNUA.jpg", 36.00),
        Menu(3, "1/4 Pollo", "https://polleriaslagranja.com/wp-content/uploads/2022/10/La-Granja-Real-Food-Chicken-1.4-de-Pollo-a-la-Brasa.png", 12.00),
        Menu(4, " 1 Hamburguesa", "https://d31npzejelj8v1.cloudfront.net/media/recipemanager/recipe/1687289278_clasica-bem.jpg", 8.00),
        Menu(5, "1 Chaufa", "https://www.paulinacocina.net/wp-content/uploads/2021/12/arroz-chaufa-peruano-receta-800x534.jpg", 5.00),
    )

    val cart = mutableListOf<Menu>(
    )

    val orderList = mutableListOf<Order>(
    )

    val userList = mutableListOf<User>(
        User("68643679", "Christopher", "Carrasco", "915493652", "12/05/2001", "Masculino","carrasco@gmail.com", "123"),
        User("25686754", "Gerson", "Vasquez", "99564326", "12/05/2001", "Masculino","gerson@gmail.com", "123")
    )
}