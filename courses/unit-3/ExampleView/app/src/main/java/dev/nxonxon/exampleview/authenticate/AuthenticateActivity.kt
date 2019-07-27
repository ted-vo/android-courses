package dev.nxonxon.exampleview.authenticate

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import dev.nxonxon.exampleview.R
import dev.nxonxon.exampleview.authenticate.fragments.ForgotPasswordFragment
import dev.nxonxon.exampleview.authenticate.fragments.LoginFragment

class AuthenticateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticate)
        ViewModelProviders
            .of(this)
            .get(AuthenticateViewModel::class.java)

        showFragment(LoginFragment())
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frMainContainer, fragment)
            .commit()
    }

    fun gotoForgotPassword(view: View) {
        showFragment(ForgotPasswordFragment())
    }
}