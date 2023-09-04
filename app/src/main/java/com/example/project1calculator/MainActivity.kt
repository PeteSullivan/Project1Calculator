package com.example.project1calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.widget.Button
import android.widget.TextView
import kotlin.math.sign

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var displayText = ""
        var isLastPressedSign = false
        var lastSign = '='
        var storage = 0.0


        // get reference to buttons
        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val buttonc = findViewById<Button>(R.id.buttonc)
        val buttonadd = findViewById<Button>(R.id.buttonadd)
        val buttondivide = findViewById<Button>(R.id.buttondivide)
        val buttonmultiply = findViewById<Button>(R.id.buttonmultiply)
        val buttonsubtract = findViewById<Button>(R.id.buttonsubtract)
        val buttondecimal = findViewById<Button>(R.id.buttondecimal)
        val buttonpercent = findViewById<Button>(R.id.buttonpercent)
        val buttonrun = findViewById<Button>(R.id.buttonrun)
        val buttonsign = findViewById<Button>(R.id.buttonsign)

        val display = findViewById<TextView>(R.id.display)

        //button functions
        button1.setOnClickListener {
            if (isLastPressedSign) {
                isLastPressedSign = false
                displayText = ""
            }
            if (displayText.length < 8) {
                displayText += "1"
                display.setText(displayText)
            }
        }

        buttonc.setOnClickListener {
            displayText = ""
            display.setText(displayText)
            lastSign = '='
            isLastPressedSign = false
        }
        buttonadd.setOnClickListener {
            if (isLastPressedSign && lastSign == '=') lastSign = '+'
            else if (isLastPressedSign) {
                var temp = displayText.toDouble()

                if (lastSign == '+') storage = storage + temp
                else if (lastSign == '-') storage = storage - temp
                else if (lastSign == '/') storage = storage / temp
                else if (lastSign == '*') storage = storage * temp


                displayText = storage.toString().substring(0, minOf(storage.toString().length, 8))
                display.setText(displayText)
                storage = temp
            }
            else {
                storage = displayText.toDouble()
                isLastPressedSign = true
                lastSign = '+'
            }
        }
        buttonrun.setOnClickListener {
            if (!isLastPressedSign && lastSign != '=') {
                var temp = displayText.toDouble()

                if (lastSign == '+') storage = storage + temp
                else if (lastSign == '-') storage = storage - temp
                else if (lastSign == '/') storage = storage / temp
                else if (lastSign == '*') storage = storage * temp


                displayText = storage.toString().substring(0, minOf(storage.toString().length, 8))
                display.setText(displayText)
                storage = temp
                isLastPressedSign = true

            }

        }



    }


}