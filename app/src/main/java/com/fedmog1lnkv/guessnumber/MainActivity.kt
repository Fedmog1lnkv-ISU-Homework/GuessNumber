package com.fedmog1lnkv.guessnumber

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fedmog1lnkv.guessnumber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startGameButton.setOnClickListener {
            val minRange = binding.minRangeEditText.text.toString().toIntOrNull()
            val maxRange = binding.maxRangeEditText.text.toString().toIntOrNull()

            if (minRange != null && maxRange != null && minRange < maxRange) {
                val intent = Intent(this, GameActivity::class.java)

                intent.putExtra(GameActivity.ARG_MIN_RANGE, minRange)
                intent.putExtra(GameActivity.ARG_MAX_RANGE, maxRange)

                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.incorrect_input),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
