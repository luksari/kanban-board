package com.mvvm.kanban_board.helpers

import org.mindrot.jbcrypt.BCrypt
import java.security.MessageDigest

class Hasher {

    fun hash(): String {
        val bytes = this.toString().toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })

    }
}