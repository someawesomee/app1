package ru.afilonov.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import ru.afilonov.app1.R
import ru.afilonov.app1.activities.MainActivity

class OnboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.button_continue)
        val image: ImageView = view.findViewById(R.id.logo)

        val imageId = this.resources.getIdentifier(
            "logo_start",
            "drawable",
            requireContext().packageName
        )

        image.setImageResource(imageId)

        button.setOnClickListener {
            (activity as? MainActivity)?.navigateToSignInFragment()
        }
    }



}