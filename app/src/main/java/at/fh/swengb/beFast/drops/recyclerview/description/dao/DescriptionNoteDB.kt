package at.fh.swengb.beFast.drops.recyclerview.description.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *
 * The DescriptionNoteDB creates the database with Room's databaseBuilder
 * and connects to it. It uses the provided database schema in DescriptionNote
 * and the DescriptionNoteDAO to call functions for data access.
 *
 */

@Database(entities = [DescriptionNote::class], version = 1)
abstract class DescriptionNoteDB : RoomDatabase() {
    abstract val descriptionNoteDao: DescriptionNoteDAO

    companion object {
        private var INSTANCE: DescriptionNoteDB? = null
        fun getDatabase(context: Context): DescriptionNoteDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }
        private fun buildDatabase(context: Context): DescriptionNoteDB {
            return Room.databaseBuilder(
                context,
                DescriptionNoteDB::class.java, "description-note-db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
}