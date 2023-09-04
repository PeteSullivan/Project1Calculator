package com.example.project1calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.widget.Button
import android.widget.TextView
import kotlin.math.roundToInt
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var displayText = "" //text displayed
        var lastSign = 'r' //stores the last operation clicked
        var storage = 0.0 //stores first number for calculations


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
            //general function for digit buttons
            if (displayText == "" || displayText == "0") {
                displayText = num.toString()
                display.text = displayText
            } else if (displayText.length < 8) {
                displayText += num.toString()
                display.text = displayText
            }
        }

        fun compute() {
            //general function for running operations
            val temp = displayText.toDouble()

            when (lastSign) {
                '+' -> storage += temp
                '-' -> storage -= temp
                '/' -> storage /= temp
                '*' -> storage *= temp
            }

            displayText = storage.toString().substring(0, minOf(storage.toString().length, 8))
            display.setText(displayText)
            storage = temp
        }

        fun functionButton(sign: Char) {
            //base funtion for +,-,/,* buttons
            if (lastSign != 'r' && displayText != "") compute()
            if (displayText != "")  storage = displayText.toDouble()
            displayText = ""
            lastSign = sign
        }

        //add button functions
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
        buttonsubtract.setOnClickListener {functionButton('-')}
        buttondivide.setOnClickListener {functionButton('/')}
        buttonmultiply.setOnClickListener {functionButton('*')}
        buttonrun.setOnClickListener {functionButton('r') }

        buttonsign.setOnClickListener {
            //changes sign when applicable
            if (displayText != "0" && displayText != "") {
                if (displayText[0] == '-') displayText = displayText.substring(1,displayText.length)
                else if (displayText.length < 8) displayText = "-$displayText"
                display.text = displayText
            }
        }
        buttonpercent.setOnClickListener {
            //divides by 100 when applicable
            if (displayText != "0" && displayText != "") {
                displayText = (displayText.toDouble() / 100).toString()
                displayText = displayText.substring(0, minOf(displayText.length, 8))
                display.text = displayText
            }
        }
       buttondecimal.setOnClickListener {
           //adds decimal when applicable
           if (displayText != "" && !displayText.contains('.') && displayText.length < 8) {
               displayText += "."
               display.text = displayText
           } else if (displayText == "") {
               displayText = "0."
               display.text = displayText
           }
       }
        buttonc.setOnClickListener {
            //resets everything
            displayText = ""
            display.text = "0"
            storage = 0.0
            lastSign = 'r'
        }

    }

}