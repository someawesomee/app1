package ru.afilonov.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.afilonov.app1.R
import ru.afilonov.app1.databinding.FragmentOnboardBinding

class OnboardFragment : Fragment() {

    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image: ImageView = view.findViewById(R.id.logo)

        val imageId = this.resources.getIdentifier(
            "logo_start",
            "drawable",
            requireContext().packageName
        )

        image.setImageResource(imageId)

        binding.buttonContinue.setOnClickListener {
            findNavController().navigate(R.id.action_onboardFragment_to_signInFragment)
        }
    }
}