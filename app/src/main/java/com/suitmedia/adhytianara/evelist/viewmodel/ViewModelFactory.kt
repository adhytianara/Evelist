package com.suitmedia.adhytianara.evelist.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suitmedia.adhytianara.evelist.di.Injection
import com.suitmedia.adhytianara.evelist.repository.Repository
import com.suitmedia.adhytianara.evelist.ui.event.EventListMapsViewModel
import com.suitmedia.adhytianara.evelist.ui.event.EventListViewModel
import com.suitmedia.adhytianara.evelist.ui.guest.GuestViewModel

class ViewModelFactory private constructor(private val mRepository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(EventListViewModel::class.java) -> {
                EventListViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(EventListMapsViewModel::class.java) -> {
                EventListMapsViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(GuestViewModel::class.java) -> {
                GuestViewModel(mRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}