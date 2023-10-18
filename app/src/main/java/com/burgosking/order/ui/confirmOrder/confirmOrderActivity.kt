package com.burgosking.order.ui.confirmOrder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.burgosking.order.R
import com.burgosking.order.data.db.database
import com.burgosking.order.databinding.ActivityConfirmOrderBinding
import com.burgosking.order.databinding.ActivityHomeBinding
import com.burgosking.order.ui.home.HomeActivity

class confirmOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnHome
            .setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
                finish()
        }

        val lastOrder = database.orderList.lastOrNull()
        if (lastOrder != null) {
            binding.textOrderNumber.text = "Order Number #${lastOrder.id}"
        } else {
            binding.textOrderNumber.text = "Order Number #0000"
        }
    }
}