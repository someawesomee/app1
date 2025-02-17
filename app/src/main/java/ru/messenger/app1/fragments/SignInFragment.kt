package ru.messenger.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.messenger.app1.R
import ru.messenger.app1.databinding.FragmentSignInBinding
import ru.messenger.app1.db.DbHelper

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val args: SignInFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // userLogin = view.findViewById(R.id.user_login_auth)
        // userPass = view.findViewById(R.id.user_pass_auth)


        val user = args.user
        user?.let {
            binding.userLoginAuth.setText(it.login)
            binding.userPassAuth.setText(it.pass)
        }

        binding.doReg.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.buttonAuth.setOnClickListener {
            val login = binding.userLoginAuth.text.toString().trim()
            val pass = binding.userPassAuth.text.toString().trim()

            if (login.isEmpty() || pass.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Все поля должны быть заполнены",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val db = DbHelper(requireContext(), null)
                val isAuth = db.existUser(login, pass)

                if (isAuth) {
                    Toast.makeText(
                        requireContext(),
                        "Пользователь: $login авторизован",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.userLoginAuth.text.clear()
                    binding.userPassAuth.text.clear()

                    findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Пользователь: $login не авторизован",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
