package ru.messenger.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.messenger.app1.R
import ru.messenger.app1.databinding.FragmentSignUpBinding
import ru.messenger.app1.db.DbHelper
import ru.messenger.app1.models.User

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding ?: throw Exception()

    private lateinit var userLogin: EditText
    private lateinit var userEmail: EditText
    private lateinit var userPass: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userLogin = view.findViewById(R.id.user_login)
        userEmail = view.findViewById(R.id.user_email)
        userPass = view.findViewById(R.id.user_pass)

        binding.backSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.buttonReg.setOnClickListener {
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

                val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment(user)
                findNavController().navigate(action)

                userLogin.text.clear()
                userEmail.text.clear()
                userPass.text.clear()
            }
        }
    }
}