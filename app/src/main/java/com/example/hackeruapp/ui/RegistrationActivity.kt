package com.example.hackeruapp.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.hackeruapp.R

class RegistrationActivity : AppCompatActivity() {
    var isLoginFragment = true
    val userName = "a@a.com"
    val password = "1234"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        displayLoginFragment()
        setTextViewClickListener()
    }

    override fun onStart() {
        super.onStart()
        sharedPreferences = getSharedPreferences(R.string.app_name.toString(), MODE_PRIVATE)
        calculateLastLogin()
    }

    private fun calculateLastLogin() {
        val lastLogin = sharedPreferences.getLong("LAST_LOGIN", -1)
        if (lastLogin != -1L && System.currentTimeMillis() - lastLogin < 36000) {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTextViewClickListener() {
        findViewById<TextView>(R.id.login_signup_tv).setOnClickListener {
            if (isLoginFragment) {
                displaySignUpFragment()
            } else {
                displayLoginFragment()
            }
        }
    }

    fun displaySignUpFragment() {
        isLoginFragment = false
        findViewById<TextView>(R.id.login_signup_tv).text = "Already a member? Click here to Login"
        val signupFragment = SignUpFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, signupFragment)
            .commit()
    }

    fun displayLoginFragment() {
        isLoginFragment = true
        findViewById<TextView>(R.id.login_signup_tv).text = "Not a member Click here to sign up"
        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, loginFragment)
            .commit()
    }


    fun onStartClick(view: View) {
        if (isUserLegit()) {
//            goInApp()
        } else {
            Toast.makeText(this, "Halo halo you are not legit", Toast.LENGTH_SHORT).show()
        }
    }


    fun goInApp(userName: String) {
        val editor = sharedPreferences.edit()
        editor.putLong("LAST_LOGIN", System.currentTimeMillis()).apply()
        editor.putString("LAST_NAME", userName).apply()
        val intent = Intent(this, NotesActivity::class.java)
        startActivity(intent)
    }

    private fun isUserLegit(): Boolean {
//        return findViewById<EditText>(R.id.email_login_tv).text.toString() == userName &&
//                findViewById<EditText>(R.id.password_login_tv).text.toString() == password
        return true
    }
}


