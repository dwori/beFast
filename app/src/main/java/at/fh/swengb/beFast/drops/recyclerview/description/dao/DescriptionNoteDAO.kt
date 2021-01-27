package at.fh.swengb.beFast.drops.recyclerview.description.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DescriptionNoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(descriptionNote: DescriptionNote)

    @Query("SELECT * FROM DescriptionNote where id = :id")
    fun findDropBySameID(id: String): DescriptionNote

    @Query("SELECT * FROM DescriptionNote where id = :id")
    fun selectWithLiveData(id: String?): LiveData<DescriptionNote?>
}