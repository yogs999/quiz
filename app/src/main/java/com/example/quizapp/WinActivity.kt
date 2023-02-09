package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityWinBinding

class WinActivity : AppCompatActivity() {

       private lateinit var binding: ActivityWinBinding

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            binding= ActivityWinBinding.inflate(layoutInflater)
             setContentView(binding.root)



        val data=intent.getIntExtra("totalscore",0)
          binding.win.text="YOUR SCORE IS $data OUT OF 5"

           binding.retake.setOnClickListener {
               val intent= Intent(this,MainActivity::class.java)
               startActivity(intent)
           }


    }
}