package com.burgosking.order.ui.orders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.burgosking.order.R
import com.burgosking.order.data.db.database
import com.burgosking.order.databinding.ActivityLoginBinding
import com.burgosking.order.databinding.ActivityOrdersBinding
import com.burgosking.order.ui.auth.AccountSettingsActivity
import com.burgosking.order.ui.home.HomeActivity
import com.burgosking.order.ui.myOrder.MyOrderActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrdersActivity : AppCompatActivity() {
    private lateinit var binding:ActivityOrdersBinding
    private lateinit var ordersAdapter: OrdersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentDateString = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        val todaysOrders = database.orderList.filter { it.date == currentDateString }

        if (todaysOrders.isEmpty()) {
            binding.noOrdersText.visibility = View.VISIBLE
        } else {
            binding.noOrdersText.visibility = View.GONE
        }

        ordersAdapter = OrdersAdapter(todaysOrders)

        // RecyclerView
        binding.ordersRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.ordersRecyclerview.adapter = ordersAdapter
        binding.ordersRecyclerview.setHasFixedSize(true) // Optimización

        binding.navigationHome.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.navigationOrders.setOnClickListener{
            val intent = Intent(this, OrdersActivity::class.java)
            startActivity(intent)
        }

        binding.navigationPerfil.setOnClickListener{
            val intent = Intent(this, AccountSettingsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}