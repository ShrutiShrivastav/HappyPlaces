package com.happyplaces.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.happyplaces.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private val PASSWORD_PATTERN = Pattern.compile(
        "^" +  //"(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +  //at least 1 lower case letter
                "(?=.*[A-Z])" +  //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{6,}" +  //at least 8 characters
                "$"
    )
    var isEmailValid = false
    var isPasswordValid:kotlin.Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       val signUp = findViewById<TextView>(R.id.signup)
        val login = findViewById<Button>(R.id.btnLogin)

        signUp.setOnClickListener(View.OnClickListener { // redirect to SignUpActivity
            val intent = Intent(applicationContext, SignUp::class.java)
            startActivity(intent)

        })

        login.setOnClickListener {
          SetValidation()
        }
    }

    fun SetValidation() {
        val passwordInput: String = edtPassword.getText().toString().trim { it <= ' ' }

       /* // Check for a valid name.
        if (edtUserName.getText().toString().isEmpty()) {
            edtUserName.setError(resources.getString(R.string.name_error))
            isNameValid = false
        } else if (etName.getText().toString().length < 4) {
            etName.setError(resources.getString(R.string.error_invalid_name))
        } else {
            isNameValid = true
        }*/

        // Check for a valid email address.
        isEmailValid = if (edtUserName.getText().toString().isEmpty()) {
            edtUserName.setError(resources.getString(R.string.email_error))
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edtUserName.getText().toString()).matches()) {
            edtUserName.setError(resources.getString(R.string.error_invalid_email))
            false
        } else {
            true
        }

        // Check for a valid phone number.
      /*  if (etMobile.getText().toString().isEmpty()) {
            etMobile.setError(resources.getString(R.string.phone_error))
            isMobileValid = false
        } else {
            isMobileValid = true
        }*/

        // Check for a valid password.
        isPasswordValid = if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError(resources.getString(R.string.password_error))
            false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput)
                .matches()
        ) {
            edtPassword.setError(resources.getString(R.string.error_invalid_password))
            false
        } else {
            true
        }
        if ( isEmailValid && isPasswordValid) {
            Toast.makeText(applicationContext, "Successfully Logged In", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}