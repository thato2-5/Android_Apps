package com.example.doforms03

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.doforms03.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Place your activity binding code here
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // activity_second.xml elements here:
        val mainActivityBtn = binding.mainActivity
        val secondActivityBtn = binding.secondActivity
        val thirdActivityBtn = binding.thirdActivity

        mainActivityBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("Message", "Hello from Second Activity")
            startActivity(intent)
        }

        secondActivityBtn.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("Message", "Hello from Second Activity")
            startActivity(intent)
        }

        thirdActivityBtn.setOnClickListener{
            val intent = Intent(this, ThirdActivity::class.java)

            intent.putExtra("Message", "Hello from Second Activity")
            startActivity(intent)
        }
    }
}