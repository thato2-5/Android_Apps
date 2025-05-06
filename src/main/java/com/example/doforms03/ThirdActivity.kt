package com.example.doforms03

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.doforms03.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // place other bindings here to be used as reference:
        val binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // activity_third.xml elements here:
        val mainActivityBtn = binding.inspectionFloatingActionButton
        val secondActivityBtn = binding.technicalInstallationFloatingActionButton
        val thirdActivityBtn = binding.exhaustInspectionFloatingActionButton

        mainActivityBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("Message", "Hello from Third Activity")
            startActivity(intent)
        }

        secondActivityBtn.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("Message", "Hello from Third Activity")
            startActivity(intent)
        }

        thirdActivityBtn.setOnClickListener{
            val intent = Intent(this, ThirdActivity::class.java)

            intent.putExtra("Message", "Hello from Third Activity")
            startActivity(intent)
        }
    }
}