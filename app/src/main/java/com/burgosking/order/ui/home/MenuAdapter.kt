package com.burgosking.order.ui.home

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.burgosking.order.R
import com.burgosking.order.data.models.MenuData
import com.burgosking.order.data.db.database
import com.squareup.picasso.Picasso

class MenuAdapter(private val menuList: List<MenuData>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.menuImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.menuNameTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.menuPriceTextView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_menu_item, parent, false)
        return MenuViewHolder(view)
    }
    override fun getItemCount() = menuList.size
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentItem = menuList[position]
        Picasso.get().load(currentItem.photo).into(holder.imageView)
        holder.nameTextView.text = currentItem.description
        holder.priceTextView.text = "S/. ${currentItem.price}"

        holder.itemView.setOnClickListener {
            // Aquí puedes mostrar el AlertDialog
            val context = holder.itemView.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Agregar al carrito")
            builder.setMessage("¿Deseas agregar ${currentItem.description} al carrito?")
            builder.setPositiveButton("Sí") { dialog, which ->
                Toast.makeText(context, "${currentItem.description} agregado al carrito", Toast.LENGTH_SHORT).show()
                database.cart.add(currentItem)
            }
            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}