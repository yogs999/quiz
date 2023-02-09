package com.example.quizapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsetsAnimation
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.quizapp.databinding.ActivityMainBinding
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var rightanswer: String? = null
    private var rightanswerCount = 0
    private var qouizecount = 1
    private var totalquestion = 5

    private val quize = mutableListOf(
        mutableListOf(
            "Who was the chief guest on the occasion of the 74th Republic day in India?",
            "Abdel Fattah El-Sisi, Egypt",
            "Jair Messias Bolsonaro, Brazil",
            "Emmanuel Macron, France",
            "Cyril Ramaphosa, South Africa"
        ),
        mutableListOf(
            "Android is -",
            "an operating system",
            "a web browser",
            "a web server",
            "None of the above"
        ),
        mutableListOf(
            "Under which of the following Android is licensed?",
            "Apache/MIT",
            "OSS",
            "Sourceforge",
            "None of the above"
        ),
        mutableListOf(
            "For which of the following Android is mainly developed?",
            "Mobile devices",
            "Desktops",
            "Laptops",
            "Servers"
        ),
        mutableListOf(
            "APK stands for -",
            "Android Package Kit",
            "Android Phone Kit",
            "Android Page Kit",
            "None of the above"
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quize.shuffle()
        showNextquiz()
    }

    private fun showNextquiz() {

        val questions = quize[0]

        binding.counter.text = qouizecount.toString()

        binding.question.text = questions[0]
        rightanswer = questions[1]

        questions.removeAt(0)
        questions.shuffle()

        binding.btn1.text = questions[0]
        binding.btn2.text = questions[1]
        binding.btn3.text = questions[2]
        binding.btn4.text = questions[3]

        quize.removeAt(0)


    }

    fun checkAnswer(view: View) {

        val answerbtn: Button = findViewById(view.id)
        val curractans = answerbtn.text.toString()

        if (curractans == rightanswer) {
            binding.animation.setAnimation(R.raw.correct)
            binding.animation.playAnimation()
            rightanswerCount++

        } else {
            binding.animation.setAnimation(R.raw.wrong)
            binding.animation.playAnimation()
        }
        AlertDialog.Builder(this).setTitle(curractans).setMessage("correct answer is $rightanswer")
            .setPositiveButton("NEXT") { DialogInterface, i ->
                binding.animation.pauseAnimation()
                binding.animation.setImageDrawable(null)
                checkQuizCount()
            }.setCancelable(false)
            .show()

    }

    fun checkQuizCount() {


        if (qouizecount == totalquestion) {

            val intent = Intent(this, WinActivity::class.java)
            intent.putExtra("totalscore", rightanswerCount)
            startActivity(intent)

        } else {

            qouizecount++
            showNextquiz()
        }
    }

}