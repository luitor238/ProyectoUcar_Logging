package com.example.proyectoucar_logging

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private lateinit var Email :EditText
    private lateinit var Password :EditText
    private lateinit var btnLogin :Button
    private lateinit var btnForgotPassword :Button
    private lateinit var btnSingIn :Button

    private lateinit var email :String
    private lateinit var username :String
    private lateinit var password :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Email = findViewById(R.id.editTextEmail)
        Password = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnForgotPassword = findViewById(R.id.btnForgotPassword)
        btnSingIn = findViewById(R.id.btnSingIn)

        //ACTION BUTON LOGIN
        btnLogin.setOnClickListener {

            var email = Email.text.toString()
            var password = Password.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Email.setError("Invalid email")
                Email.isFocusable = true
            }else if (Password.length()<8){
                Password.setError("Minimum 8 characters")
                Password.isFocusable = true
            }else{
                LogInUser(email,password)
            }
        }

        //ACTION BUTTON SING IN
        btnSingIn.setOnClickListener {
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
        }
    }

    //LOG IN FUCTION
    private fun LogInUser (email: String,password: String ){
        //CREAR FUNCION PARA LOGEARSE
    }

}