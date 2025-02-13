package ru.messenger.app1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.messenger.app1.R

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val userLogin: EditText = findViewById(R.id.user_login_auth)
        val userPass: EditText = findViewById(R.id.user_pass_auth)
        val button: Button = findViewById(R.id.button_auth)
        val doReg: TextView = findViewById(R.id.do_reg)

        doReg.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if (login == "" || pass == "")
                Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
            else {
                val db = DbHelper(this, null)
                val isAuth = db.existUser(login, pass)

                if (isAuth)  {
                    Toast.makeText(this, "Пользователь: $login авторизован", Toast.LENGTH_SHORT).show()
                    userLogin.text.clear()
                    userPass.text.clear()

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this, "Пользователь: $login не авторизован", Toast.LENGTH_SHORT).show()
            }

        }
    }
}