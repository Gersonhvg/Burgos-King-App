package com.burgosking.order.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.burgosking.order.data.db.database
import com.burgosking.order.data.db.database.cart
import com.burgosking.order.databinding.ActivityHomeBinding
import com.burgosking.order.ui.auth.AccountSettingsActivity
import com.burgosking.order.ui.myOrder.MyOrderActivity
import com.burgosking.order.ui.orders.OrdersActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    val menus = database.menuList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView
        val recyclerView = binding.recyclerView
        recyclerView.adapter = MenuAdapter(menus)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        binding.navigationHome.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.navigationOrders.setOnClickListener{
            val intent =Intent(this, OrdersActivity::class.java)
            startActivity(intent)
        }

        binding.navigationPerfil.setOnClickListener{
            val intent =Intent(this, AccountSettingsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.cartIcon.setOnClickListener{
            if (cart.isEmpty()) {
                Toast.makeText(this, "No hay ítems agregados al carrito", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MyOrderActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}