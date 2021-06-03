package com.suitmedia.adhytianara.evelist.ui.home

import androidx.lifecycle.ViewModel
import java.util.*

class HomeViewModel : ViewModel() {
    private lateinit var name: String

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return this.name
    }

    fun nameIsPalindrome(name: String): String {
        return if (isPalindrome(name)) {
            "isPalindrome"
        } else {
            "not palindrome"
        }
    }

    private fun isPalindrome(name: String): Boolean {
        val cleanedName = name.replace("\\s".toRegex(), "").toLowerCase(Locale.ROOT)
        val reversedName = cleanedName.reversed()
        return (cleanedName == reversedName)
    }
}
