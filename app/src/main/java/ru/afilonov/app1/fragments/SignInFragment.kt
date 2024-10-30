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

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userLogin: EditText = view.findViewById(R.id.user_login_auth)
        val userPass: EditText = view.findViewById(R.id.user_pass_auth)
        val button: Button = view.findViewById(R.id.button_auth)
        val doReg: TextView = view.findViewById(R.id.do_reg)

        doReg.setOnClickListener {
            (activity as? MainActivity)?.openSignUpFragment()
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if (login == "" || pass == "")
                Toast.makeText(
                    requireContext(),
                    "Все поля должны быть заполнены",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                val db = DbHelper(requireContext(), null)
                val isAuth = db.existUser(login, pass)

                if (isAuth) {
                    Toast.makeText(
                        requireContext(),
                        "Пользователь: $login авторизован",
                        Toast.LENGTH_SHORT
                    ).show()
                    userLogin.text.clear()
                    userPass.text.clear()

                    (activity as? MainActivity)?.openHomeFragment()
                } else
                    Toast.makeText(
                        requireContext(),
                        "Пользователь: $login не авторизован",
                        Toast.LENGTH_SHORT
                    ).show()
            }

        }


    }
}