package com.fedmog1lnkv.guessnumber

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fedmog1lnkv.guessnumber.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    companion object {
        const val ARG_MIN_RANGE = "MIN_RANGE"
        const val ARG_MAX_RANGE = "MAX_RANGE"
    }

    private lateinit var binding: ActivityGameBinding
    private var minRange: Int = 0
    private var maxRange: Int = 100
    private var guessedNumber: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            minRange = extras.getInt(ARG_MIN_RANGE, 0)
            maxRange = extras.getInt(ARG_MAX_RANGE, 100)
        }

        binding.yesButton.setOnClickListener {
            handleUserResponse(true)
        }

        binding.noButton.setOnClickListener {
            handleUserResponse(false)
        }

        guessNumber()
    }

    private fun guessNumber() {
        guessedNumber = minRange + (maxRange - minRange) / 2
        binding.guessedNumberTextView.text = guessedNumber.toString()
    }

    private fun handleUserResponse(isYes: Boolean) {
        if (isYes) {
            minRange = guessedNumber
        } else {
            maxRange = guessedNumber
        }

        if (maxRange - minRange <= 2) {
            binding.instructionTextView.text = getString(R.string.your_number)
            minRange++
            binding.guessedNumberTextView.text = minRange.toString()
            setupWinButtons()
        } else {
            guessNumber()
        }
    }

    private fun setupWinButtons() {
        minRange++

        binding.yesButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.win), Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.noButton.setOnClickListener {
            binding.guessedNumberTextView.text = (minRange++).toString()
            if (minRange - 2 == maxRange) {
                Toast.makeText(
                    this,
                    getString(R.string.error_in_game),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
}

