package com.suitmedia.adhytianara.evelist.data

import com.google.gson.Gson
import com.suitmedia.adhytianara.evelist.data.api.RetrofitInstance
import com.suitmedia.adhytianara.evelist.utils.JsonHelper
import org.json.JSONException
import org.json.JSONObject

class DataSource private constructor(private val jsonHelper: JsonHelper) {
    companion object {
        @Volatile
        private var instance: DataSource? = null

        fun getInstance(helper: JsonHelper): DataSource =
            instance ?: synchronized(this) {
                instance ?: DataSource(helper).apply { instance = this }
            }
    }

    fun getEventList(): ArrayList<Event> {
        val list = arrayListOf<Event>()
        try {
            val obj = JSONObject(jsonHelper.loadJSONFromAsset("event_list.json"))
            val eventArray = obj.getJSONArray("data")
            for (i in 0 until eventArray.length()) {
                val eventDetail = eventArray.getJSONObject(i)

                val event = Gson().fromJson(eventDetail.toString(), Event::class.java)

                list.add(event)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    suspend fun getGuestList(): List<Guest> {
        return RetrofitInstance.api.getGuestList()
    }
}