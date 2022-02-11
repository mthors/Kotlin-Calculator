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