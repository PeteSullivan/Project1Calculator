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


        var displayText = "0"
        var readyToCompute = false
        var lastSign = 'r'
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


        fun numButton(num: Int) {
            if (displayText == "0" || lastSign == 'r') {
                displayText = num.toString()
                display.setText(displayText)
                if (lastSign == 'r') lastSign = 'n'
            }
            else if (displayText == "") {
                readyToCompute = true
                displayText = num.toString()
                display.setText(displayText)
            }
            else if (displayText.length < 8) {
                displayText += num.toString()
                display.setText(displayText)
            }
        }

        fun compute() {
            var temp = displayText.toDouble()

            if (lastSign == '+') storage += temp
            else if (lastSign == '-') storage -= temp
            else if (lastSign == '/') storage /= temp
            else if (lastSign == '*') storage *= temp


            displayText = storage.toString().substring(0, minOf(storage.toString().length, 8))
            display.setText(displayText)
            storage = temp
            readyToCompute = false
        }

        fun functionButton(sign: Char) {
            if (readyToCompute) compute()
            if (displayText != "") storage = displayText.toDouble()
            displayText = ""
            lastSign = sign
        }


        //button functions
        button1.setOnClickListener { numButton(1) }
        button2.setOnClickListener { numButton(2) }
        button3.setOnClickListener { numButton(3) }
        button4.setOnClickListener { numButton(4) }
        button5.setOnClickListener { numButton(5) }
        button6.setOnClickListener { numButton(6) }
        button7.setOnClickListener { numButton(7) }
        button8.setOnClickListener { numButton(8) }
        button9.setOnClickListener { numButton(9) }
        button0.setOnClickListener { numButton(0) }


        buttonadd.setOnClickListener {functionButton('+')}
//        buttonsubtract.setOnClickListener {functionButton('-')}
//        buttondivide.setOnClickListener {functionButton('/')}
//        buttonmultiply.setOnClickListener {functionButton('*')}

        buttonc.setOnClickListener {
            displayText = "0"
            display.setText(displayText)
            lastSign = 'r'
            readyToCompute = false
        }

        buttonrun.setOnClickListener {
            if (readyToCompute) {
                compute()
                lastSign = 'r'
            }
        }


    }



}