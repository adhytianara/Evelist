package com.suitmedia.adhytianara.evelist.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private lateinit var name: String

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return this.name
    }
}