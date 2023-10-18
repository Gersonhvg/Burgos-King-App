package com.burgosking.order.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.burgosking.order.R
import com.burgosking.order.data.db.database
import com.burgosking.order.databinding.ActivityAccountSettingsBinding
import com.burgosking.order.databinding.ActivityLoginBinding
import com.burgosking.order.ui.home.HomeActivity
import com.burgosking.order.ui.myOrder.MyOrderActivity
import com.burgosking.order.ui.orders.OrdersActivity

class AccountSettingsActivity : AppCompatActivity() {
    val sesionUser = database.session

    private lateinit var binding:ActivityAccountSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnHome.setOnClickListener{
            val intent =Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.logout.setOnClickListener{
            val intent =Intent(this, loginActivity::class.java)
            startActivity(intent)
            database.session=0
            finish()
        }

        binding.firstNameEditText.setText(database.userList[sesionUser].firstname)
        binding.firstNameEditText.isEnabled=false

        binding.lastNameEditText.setText(database.userList[sesionUser].lastname)
        binding.lastNameEditText.isEnabled=false

        binding.emailEditText.setText(database.userList[sesionUser].email)
        binding.emailEditText.isEnabled=false

        binding.passwordEditText.setText(database.userList[sesionUser].password)
        binding.passwordEditText.isEnabled=false

        binding.phoneEditText.setText(database.userList[sesionUser].phone)
        binding.phoneEditText.isEnabled=false

        binding.btnSave.setOnClickListener {
            showComingSoonDialog()
        }
    }
    private fun showComingSoonDialog() {
        AlertDialog.Builder(this)
            .setTitle("Información")
            .setMessage("Próximamente podrás editar tus datos.")
            .setPositiveButton("Entendido") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}