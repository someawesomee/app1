package ru.messenger.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.messenger.app1.databinding.FragmentSignUpBinding
import ru.messenger.app1.models.User

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backSignIn.setOnClickListener {

            findNavController().navigate(
                ru.messenger.app1.R.id.action_signUpFragment_to_signInFragment
            )
        }

        binding.buttonReg.setOnClickListener {
            val login = binding.userLogin.text.toString().trim()
            val email = binding.userEmail.text.toString().trim()
            val pass = binding.userPass.text.toString().trim()

            if (login.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Все поля должны быть заполнены",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val user = User(login, email, pass)

                Toast.makeText(
                    requireContext(),
                    "Новый пользователь: $login зарегистрирован",
                    Toast.LENGTH_SHORT
                ).show()


                val action = SignUpFragmentDirections
                    .actionSignUpFragmentToSignInFragment(user)
                findNavController().navigate(action)


                binding.userLogin.text.clear()
                binding.userEmail.text.clear()
                binding.userPass.text.clear()
            }
        }
    }
}
