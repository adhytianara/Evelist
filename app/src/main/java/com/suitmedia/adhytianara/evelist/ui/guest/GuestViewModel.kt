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
            val guestListRes = mRepository.getGuestList()
            setGuestMonthIsPrime(guestListRes)
            guestList.value = guestListRes
        }
    }

    private fun setGuestMonthIsPrime(guestListRes: List<Guest>) {
        guestListRes.forEach { guest ->
            guest.monthIsPrime = getMonthIsPrime(guest.birthdate)
        }
    }

    private fun getMonthIsPrime(birthdate: String): Boolean {
        val arrBirthdate = birthdate.split("-")
        val month = arrBirthdate[1].toInt()
        for (i in 2..month / 2) {
            if (month % i == 0) {
                return false
            }
        }
        return true
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

