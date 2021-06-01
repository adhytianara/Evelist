package com.suitmedia.adhytianara.evelist.di

import android.content.Context
import com.suitmedia.adhytianara.evelist.data.DataSource
import com.suitmedia.adhytianara.evelist.repository.Repository
import com.suitmedia.adhytianara.evelist.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): Repository {
        val dataSource = DataSource.getInstance(JsonHelper(context))
        return Repository.getInstance(dataSource)
    }
}