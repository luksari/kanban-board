package com.mvvm.kanban_board.data.db.Converte

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mvvm.kanban_board.data.db.entity.Category
import com.mvvm.kanban_board.data.db.entity.History
import com.mvvm.kanban_board.data.db.entity.User
import java.util.*




class Converters {

        private var gson = Gson()

        @TypeConverter
        fun stringToUserList(data: String?): List<User> {
            if (data == null) {
                return Collections.emptyList()
            }

            val listType = object : TypeToken<List<User>>() {
            }.type

            return gson.fromJson(data, listType)
        }

        @TypeConverter
        fun userListToString(someObjects: List<User>): String {
            return gson.toJson(someObjects)
        }


    @TypeConverter
    fun stringToCategoryList(data: String?): List<Category> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object :  TypeToken<List<Category>>() {
        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun categoryListToString(someObjects: List<Category>): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToHistoryList(data: String?): List<History> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object :  TypeToken<List<History>>() {
        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun historyListToString(someObjects: List<History>): String {
        return gson.toJson(someObjects)
    }

}