package com.burgosking.order.ui.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burgosking.order.data.models.MenuData
import com.burgosking.order.databinding.OrdersItemMenuBinding

class MenuItemsOrdersAdapter: RecyclerView.Adapter<MenuItemsOrdersAdapter.MenuItemViewHolder>() {
    private var menuItems: List<MenuData> = listOf()
    fun submitList(menuItems: List<MenuData>) {
        this.menuItems = menuItems
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val binding = OrdersItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.bind(menuItem)
    }
    override fun getItemCount(): Int = menuItems.size
    inner class MenuItemViewHolder(private val binding: OrdersItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menuItem: MenuData) {
            binding.itemDescription.text = menuItem.description
            binding.itemPrice.text = "S/. ${menuItem.price}"
        }
    }
}