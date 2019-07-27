package dev.nxonxon.exampleview.authenticate

import android.arch.lifecycle.ViewModel

class AuthenticateViewModel : ViewModel() {

    var username: String = "admin@gmail.com"

    fun afterUsernameChange(charSequence: CharSequence) {
        username = charSequence.toString()
    }
}