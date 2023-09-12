package com.example.project1calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TestLooperManager
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    var displayText = "" //text displayed
    var lastSign = 'r' //stores the last operation clicked
    var storage = 0.0 //stores first number for calculations


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
        val buttonsin = findViewById<Button>(R.id.buttonsin)
        val buttoncos = findViewById<Button>(R.id.buttoncos)
        val buttontan = findViewById<Button>(R.id.buttontan)
        val buttonlog = findViewById<Button>(R.id.buttonlog)
        val buttonln = findViewById<Button>(R.id.buttonln)


        fun numButton(num: Int) {
            //general function for digit buttons
            Log.d("MainActivity", num.toString())
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
            //base function for +,-,/,* buttons
            Log.d("MainActivity", sign.toString())

            if (lastSign != 'r' && displayText != "") compute()
            if (displayText != "") storage = displayText.toDouble()
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

        buttonadd.setOnClickListener { functionButton('+') }
        buttonsubtract.setOnClickListener { functionButton('-') }
        buttondivide.setOnClickListener { functionButton('/') }
        buttonmultiply.setOnClickListener { functionButton('*') }
        buttonrun.setOnClickListener { functionButton('r') }

        buttonsign.setOnClickListener {
            //changes sign when applicable
            Log.d("MainActivity", "+/-")
            if (displayText == "" && storage != 0.0 && lastSign == 'r') {
                storage *= -1
                display.text = storage.toString()
                display.text = display.text.substring(0, minOf(display.text.length, 8))
            }
            else if (displayText != "0" && displayText != "") {
                if (displayText[0] == '-') displayText = displayText.substring(1, displayText.length)
                else if (displayText.length < 8) displayText = "-$displayText"
                display.text = displayText
            }
        }
        buttonpercent.setOnClickListener {
            //divides by 100 when applicable
            Log.d("MainActivity", "%")
            if (displayText == "" && storage != 0.0 && lastSign == 'r') {
                storage = storage / 100
                display.text = storage.toString()
                display.text = display.text.substring(0, minOf(display.text.length, 8))
            }
            else if (displayText != "0" && displayText != "") {
                displayText = (displayText.toDouble() / 100).toString()
                displayText = displayText.substring(0, minOf(displayText.length, 8))
                display.text = displayText
            }

        }
        buttonsin?.setOnClickListener {
            //sin when applicable
            Log.d("MainActivity", "sin")
            if (displayText == "" && storage != 0.0 && lastSign == 'r') {
                storage = kotlin.math.sin(storage)
                display.text = storage.toString()
                display.text = display.text.substring(0, minOf(display.text.length, 8))

            }
            else if (displayText != "") {
                displayText = (kotlin.math.sin(displayText.toDouble())).toString()
                displayText = displayText.substring(0, minOf(displayText.length, 8))
                display.text = displayText
            }
        }
        buttoncos?.setOnClickListener {
            //cos when applicable
            Log.d("MainActivity", "cos")

            if (displayText == "" && storage != 0.0 && lastSign == 'r') {
                storage = kotlin.math.cos(storage)
                display.text = storage.toString()
                display.text = display.text.substring(0, minOf(display.text.length, 8))
            }
            else if (displayText != "") {
                displayText = (kotlin.math.cos(displayText.toDouble())).toString()
                displayText = displayText.substring(0, minOf(displayText.length, 8))
                display.text = displayText
            }
        }
        buttontan?.setOnClickListener {
            //tan when applicable
            Log.d("MainActivity", "tan")
            if (displayText == "" && storage != 0.0 && lastSign == 'r') {
                storage = kotlin.math.tan(storage)
                display.text = storage.toString()
                display.text = display.text.substring(0, minOf(display.text.length, 8))

            }
            else if (displayText != "") {
                displayText = (kotlin.math.tan(displayText.toDouble())).toString()
                displayText = displayText.substring(0, minOf(displayText.length, 8))
                display.text = displayText
            }
        }
        buttonlog?.setOnClickListener {
            //log10 when applicable
            Log.d("MainActivity", "log10")

            if (displayText == "" && storage != 0.0 && lastSign == 'r') {
                storage = kotlin.math.log10(storage)
                display.text = storage.toString()
                display.text = display.text.substring(0, minOf(display.text.length, 8))
                displayText = display.text.toString()


            }
            else if (displayText != "") {
                displayText = (kotlin.math.log10(displayText.toDouble())).toString()
                displayText = displayText.substring(0, minOf(displayText.length, 8))
                display.text = displayText
            }
        }
        buttonln?.setOnClickListener {
            //ln when applicable
            Log.d("MainActivity", "ln")

            if (displayText == "" && storage != 0.0 && lastSign == 'r') {
                storage = kotlin.math.ln(storage)
                display.text = storage.toString()
                display.text = display.text.substring(0, minOf(display.text.length, 8))
                displayText = display.text.toString()

            }
            else if (displayText != "") {
                displayText = (kotlin.math.ln(displayText.toDouble())).toString()
                displayText = displayText.substring(0, minOf(displayText.length, 8))
                display.text = displayText
            }
        }
        buttondecimal.setOnClickListener {
            //adds decimal when applicable
            Log.d("MainActivity", ".")

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
            Log.d("MainActivity", "C")

            displayText = ""
            display.text = "0"
            storage = 0.0
            lastSign = 'r'
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //saves progress
        outState.putString("displayText", displayText)
        outState.putChar("lastSign", lastSign)
        outState.putDouble("storage", storage)
        outState.putCharSequence("currentDisplay", findViewById<TextView>(R.id.display).text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore progress
        displayText = savedInstanceState.getString("displayText", "0")
        lastSign = savedInstanceState.getChar("lastSign", 'r')
        storage = savedInstanceState.getDouble("storage")
        findViewById<TextView>(R.id.display).text = savedInstanceState.getCharSequence("currentDisplay")

    }
}