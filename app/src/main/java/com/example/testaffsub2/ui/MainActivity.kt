package com.example.testaffsub2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testaffsub2.R
import com.example.testaffsub2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.lifecycleOwner = this
        binding.root
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}