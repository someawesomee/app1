package ru.messenger.app1.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.messenger.app1.R
import ru.messenger.app1.fragments.HomeFragment
import ru.messenger.app1.fragments.OnboardFragment
import ru.messenger.app1.fragments.SignInFragment
import ru.messenger.app1.fragments.SignUpFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            replaceFragment(OnboardFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun navigateToSignInFragment() {
        replaceFragment(SignInFragment())
    }

    fun navigateToHomeFragment() {
        replaceFragment(HomeFragment())
    }

    fun navigateToSignUpFragment() {
        replaceFragment(SignUpFragment())
    }
}