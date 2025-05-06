package com.example.doforms03

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.doforms03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // activity_main.xml elements here:
        val mainActivityBtn = binding.plantInspectionButton
        val secondActivityBtn = binding.technicalInstallationButton
        val thirdActivityBtn = binding.exhaustInspectionButton

        val customerName = binding.customerName

        // main_activity.xml floating action button here:
        secondActivityBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("Message", "Hello from Main Activity")
            startActivity(intent)
        }

        // main_activity.xml floating action button here:
        thirdActivityBtn.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)

            intent.putExtra("Message", "Hello from Main Activity")
            startActivity(intent)
        }

        // main_activity.xml floating action button here:
        mainActivityBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("Message", "Hello from Main Activity")
            startActivity(intent)
        }
    }
}