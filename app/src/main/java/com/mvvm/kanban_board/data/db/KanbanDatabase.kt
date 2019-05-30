package com.mvvm.kanban_board.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mvvm.kanban_board.data.db.Converte.Converters
import com.mvvm.kanban_board.data.db.entity.Board
import com.mvvm.kanban_board.data.db.entity.User

@Database(
    entities = [User::class, Board::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class KanbanDatabase : RoomDatabase(){
    abstract fun kanbanDao(): KanbanDao

    companion object {
        @Volatile private var instance: KanbanDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(
            LOCK
        ) {
            instance
                ?: buildDatabase(context).also { instance = it }
        }

         fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                KanbanDatabase::class.java, "kanban.db")
                .build()
    }
}