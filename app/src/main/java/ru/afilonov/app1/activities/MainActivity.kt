package ru.afilonov.app1.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.appcompat.widget.Toolbar
import ru.afilonov.app1.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    fun navigateToSignIn() {
        navController.navigate(R.id.action_onboardFragment_to_signInFragment)
    }

    fun navigateToSignInFromSingUp() {
        navController.navigate(R.id.action_signUpFragment_to_signInFragment)
    }

    fun navigateToHome() {
        navController.navigate(R.id.action_signInFragment_to_homeFragment)
    }

    fun navigateToSignUp() {
        navController.navigate(R.id.action_signInFragment_to_signUpFragment)
    }
}