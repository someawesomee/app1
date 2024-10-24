package ru.afilonov.app1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OnboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)

        val button: Button = findViewById(R.id.button_continue)
        val image: ImageView = findViewById(R.id.logo)

        val imageId = this.resources.getIdentifier(
            "logo_start",
            "drawable",
            packageName
        )

        image.setImageResource(imageId)

        button.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}