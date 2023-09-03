package com.example.project1calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        print("hello world!")

        // get reference to button
        val button0 = findViewById(R.id.button0) as Button
        val display = findViewById(R.id.display) as TextView
        // set on-click listener
        button0.setOnClickListener {
            // your code to perform when the user clicks on the button
            display.setText("0")
        }


    }


}