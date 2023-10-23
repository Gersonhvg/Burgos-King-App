package com.burgosking.order.ui.auth

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.burgosking.order.data.db.database
import com.burgosking.order.data.models.User
import com.burgosking.order.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLinkLogin
            .setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.birthEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedDay.padZero()}/${(selectedMonth + 1).padZero()}/$selectedYear"
                binding.birthEditText.text = selectedDate
            }, year, month, day)

            datePickerDialog.show()
        }


        binding.btnSignIn.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString().trim()
            val lastName = binding.lastNameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val dni = binding.dniEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val birthDate = binding.birthEditText.text.toString().trim()
            val gender = binding.genderSpinner.selectedItem.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || dni.isEmpty() || phone.isEmpty() || birthDate.isEmpty()) {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newUser = User(dni, firstName, lastName, phone, birthDate, gender, email, password)
            database.userList.add(newUser)
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun Int.padZero(): String {
        return if (this < 10) "0$this" else this.toString()
    }
}