package com.mtsthor.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var screen: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screen = findViewById(R.id.screen)
    }

    fun onDigit(view: View){
        screen?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        screen?.text = ""

    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            screen?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        screen?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                screen?.append((view as Button).text)
            }
        }

    }

    fun onEqual(view: View){
        if (lastNumeric){
            var screenValue = screen?.text.toString()
            var prefix = ""
            try {
                if(screenValue.startsWith("-")){
                    prefix = "-"
                    screenValue = screenValue.substring(1)
                }
                if(screenValue.contains("-")) {
                    val splitValue = screenValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    screen?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if(screenValue.contains("+")) {
                    val splitValue = screenValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    screen?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if(screenValue.contains("/")) {
                    val splitValue = screenValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    screen?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }else if(screenValue.contains("*")) {
                    val splitValue = screenValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    screen?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String {
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length -2)

        }
        return value
    }

    private fun isOperatorAdded(value : String) :Boolean{
        return if(value.startsWith("-")) {
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}