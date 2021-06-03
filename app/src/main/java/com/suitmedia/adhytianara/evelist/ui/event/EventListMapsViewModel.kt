package com.suitmedia.adhytianara.evelist.ui.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suitmedia.adhytianara.evelist.data.Event
import com.suitmedia.adhytianara.evelist.repository.Repository

class EventListMapsViewModel(private val mRepository: Repository) : ViewModel() {
    val eventList = MutableLiveData<List<Event>>()

    fun getEventList() {
        eventList.value = mRepository.getEventList()
    }
}

