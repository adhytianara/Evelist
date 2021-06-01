package com.suitmedia.adhytianara.evelist.repository

import com.suitmedia.adhytianara.evelist.data.DataSource
import com.suitmedia.adhytianara.evelist.data.Event

class Repository(private val dataSource: DataSource) {

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(dataSource: DataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(dataSource).apply { instance = this }
            }
    }

    fun getEventList(): ArrayList<Event> {
        return dataSource.getEventList()
    }
}