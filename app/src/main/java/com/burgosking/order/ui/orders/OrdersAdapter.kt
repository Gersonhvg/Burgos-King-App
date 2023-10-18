package com.burgosking.order.ui.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgosking.order.R
import com.burgosking.order.data.models.OrderData
import com.burgosking.order.databinding.OrdersItemBinding

class OrdersAdapter(private val orders: List<OrderData>) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrdersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }
    override fun getItemCount(): Int = orders.size
    inner class OrderViewHolder(private val binding: OrdersItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val menuItemsAdapter = MenuItemsOrdersAdapter()
        init {
            binding.menuItemsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            binding.menuItemsRecyclerView.adapter = menuItemsAdapter
        }
        fun bind(order: OrderData) {
            binding.orderNumber.text = "#${order.id}"
            menuItemsAdapter.submitList(order.menus)
            val totalAmount = order.menus.sumOf { it.price }
            binding.totalAmountOrder.text = "S/. ${String.format("%.2f", totalAmount)}"
        }
    }
}