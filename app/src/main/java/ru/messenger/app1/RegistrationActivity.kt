package ru.messenger.app1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.messenger.app1.R

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPass: EditText = findViewById(R.id.user_pass)
        val button: Button = findViewById(R.id.button_reg)
        val doBack: TextView = findViewById(R.id.back_sign_in)

        doBack.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if (login == "" || email == "" || pass == "")
                Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
            else {
                val user  = User(login, email, pass)
                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Новый пользователь: $login зарегистрирован", Toast.LENGTH_SHORT).show()

                userLogin.text.clear()
                userEmail.text.clear()
                userPass.text.clear()            }

        }
    }
}