package com.happyplaces.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.happyplaces.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern

class SignUp : AppCompatActivity() {

    private val PASSWORD_PATTERN = Pattern.compile(
        "^" +  //"(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +  //at least 1 lower case letter
                "(?=.*[A-Z])" +  //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{8,}" +  //at least 8 characters
                "$"
    )
    var isEmailValid :Boolean = false
    var  isMobileValid :Boolean = false
    var isNameValid :Boolean = false
    var isPasswordValid:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

       // val tvLogin = findViewById<TextView>(R.id.tv_login)
        val btSignUp = findViewById<Button>(R.id.btn_signup)

        //listener for button
       btSignUp.setOnClickListener(View.OnClickListener { SetValidation() })
       /* //listener for textview
        tvLogin.setOnClickListener(View.OnClickListener { // redirect to LoginActivity
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)

        })*/
    }


    fun SetValidation() {
        val passwordInput: String = et_password.getText().toString().trim { it <= ' ' }

         // Check for a valid name.
         if (et_name.getText().toString().isEmpty()) {
             et_name.setError(resources.getString(R.string.name_error))
             isNameValid = false
         } else if (et_name.getText().toString().length < 4) {
             et_name.setError(resources.getString(R.string.error_invalid_name))
         } else {
             isNameValid = true
         }

        // Check for a valid email address.
        isEmailValid = if (et_email.getText().toString().isEmpty()) {
            et_email.setError(resources.getString(R.string.email_error))
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
            et_email.setError(resources.getString(R.string.error_invalid_email))
            false
        } else {
            true
        }


        // Check for a valid phone number.
        isMobileValid = if (et_mobile.getText().toString().isEmpty()) {
            et_mobile.setError(resources.getString(R.string.phone_error))
            false
        } else if (et_mobile.text.toString().length<10) {
            et_mobile.setError(resources.getString(R.string.invalid_phone))
            false
        } else {
            true
        }


        // Check for a valid password.
        isPasswordValid = if (et_password.getText().toString().isEmpty()) {
            et_password.setError(resources.getString(R.string.password_error))
            false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput)
                .matches()
        ) {
            et_password.setError(resources.getString(R.string.error_invalid_password))
            false
        } else {
            true
        }
        if ( isNameValid && isEmailValid && isMobileValid && isPasswordValid) {
            Toast.makeText(applicationContext, "Successfully SignedUp", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}