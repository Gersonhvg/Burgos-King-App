package com.burgosking.order.ui.myOrder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.burgosking.order.R
import com.burgosking.order.data.models.Menu

class MyOrderAdapter(public val orderList: List<Menu>, private val activity: MyOrderActivity): RecyclerView.Adapter<MyOrderAdapter.OrderViewHolder>() {
    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.orderNameTextView)
        val totalTextView: TextView = itemView.findViewById(R.id.orderTotalTextView)
        val deleteButton: View = itemView.findViewById(R.id.deleteItem)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_order_item, parent, false)
        return OrderViewHolder(view)
    }
    override fun getItemCount() = orderList.size
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentItem = orderList[position]
        holder.nameTextView.text = "${currentItem.description}"
        holder.totalTextView.text = "$${currentItem.price}"

        holder.deleteButton.setOnClickListener {
            val currentItem = orderList[position]
            activity.removeFromCart(currentItem)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, orderList.size)
            activity.updateTotal()
        }
    }
}