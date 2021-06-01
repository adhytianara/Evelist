package com.suitmedia.adhytianara.evelist.ui.guest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suitmedia.adhytianara.evelist.data.Guest
import com.suitmedia.adhytianara.evelist.repository.Repository
import kotlinx.coroutines.launch

class GuestViewModel(private val mRepository: Repository) : ViewModel() {
    val guestList = MutableLiveData<List<Guest>>()

    fun getGuestList() {
        viewModelScope.launch {
            guestList.value = mRepository.getGuestList()
        }
    }

    fun getGuestMessage(birthdate: String): String {
        val arrBirthdate = birthdate.split("-")
        val date = arrBirthdate[2].toInt()
        var message = "feature phone"
        if (date % 2 == 0 && date % 3 == 0) {
            message = "iOS"
        } else if (date % 2 == 0) {
            message = "blackberry"
        } else if (date % 3 == 0) {
            message = "android"
        }
        return message
    }
}

