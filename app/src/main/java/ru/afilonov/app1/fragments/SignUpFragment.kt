package ru.afilonov.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.afilonov.app1.R
import ru.afilonov.app1.activities.MainActivity
import ru.afilonov.app1.db.DbHelper
import ru.afilonov.app1.models.User

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userLogin: EditText = view.findViewById(R.id.user_login)
        val userEmail: EditText = view.findViewById(R.id.user_email)
        val userPass: EditText = view.findViewById(R.id.user_pass)
        val button: Button = view.findViewById(R.id.button_reg)
        val doBack: TextView = view.findViewById(R.id.back_sign_in)

        doBack.setOnClickListener {
            (activity as? MainActivity)?.openSignInFragment()
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if (login == "" || email == "" || pass == "")
                Toast.makeText(
                    requireContext(),
                    "Все поля должны быть заполнены",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                val user = User(login, email, pass)
                val db = DbHelper(requireContext(), null)
                db.addUser(user)
                Toast.makeText(
                    requireContext(),
                    "Новый пользователь: $login зарегистрирован",
                    Toast.LENGTH_SHORT
                ).show()

                (activity as? MainActivity)?.openSignInFragment()

                userLogin.text.clear()
                userEmail.text.clear()
                userPass.text.clear()
            }

        }
    }
}