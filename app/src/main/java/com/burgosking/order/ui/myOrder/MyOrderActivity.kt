package com.burgosking.order.ui.myOrder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgosking.order.R
import com.burgosking.order.data.models.Menu
import com.burgosking.order.data.db.database
import com.burgosking.order.data.db.database.cart
import com.burgosking.order.data.db.database.orderList
import com.burgosking.order.data.db.database.session
import com.burgosking.order.data.models.Order
import com.burgosking.order.databinding.ActivityMyOrderBinding
import com.burgosking.order.ui.confirmOrder.confirmOrderActivity
import com.burgosking.order.ui.home.HomeActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MyOrderActivity : AppCompatActivity() {

    val mycart = database.cart

    private lateinit var binding: ActivityMyOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.my_order_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyOrderAdapter(mycart, this)
        updateTotal()

        binding.returnHomeView.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.btnMakeOrder.setOnClickListener {
            val currentDateString = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            val nextOrderId = if (database.orderList.isEmpty()) 1 else (database.orderList.last().id + 1)

            val newOrderCart = deepCopyMenuList(mycart)
            val newOrder = Order(nextOrderId, session, currentDateString, newOrderCart)

            orderList.add(newOrder)

            cart.clear()

            val intent = Intent(this, confirmOrderActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun calculateTotal(): Double {
        return mycart.sumOf { it.price }
    }

    fun removeFromCart(item: Menu) {
        mycart.remove(item)
        updateTotal()
    }

    fun updateTotal() {
        val total = calculateTotal()
        binding.tvSubtotal.text = "S/. ${String.format("%.2f", total)}"
        binding.tvTotal.text = "S/. ${String.format("%.2f", total)}"
    }

    fun deepCopyMenuList(original: MutableList<Menu>): MutableList<Menu> {
        return original.map { it.copy() }.toMutableList()
    }
}
