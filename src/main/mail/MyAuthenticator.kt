package main.mail

import javax.mail.Authenticator
import javax.mail.PasswordAuthentication

class MyAuthenticator internal constructor(private val user: String, private val password: String) : Authenticator() {

    public override fun getPasswordAuthentication(): PasswordAuthentication {
        val user = this.user
        val password = this.password
        return PasswordAuthentication(user, password)
    }
}
