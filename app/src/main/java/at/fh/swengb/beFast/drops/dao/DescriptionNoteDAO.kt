package at.fh.swengb.beFast.drops.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import at.fh.swengb.beFast.drops.DescriptionNote

@Dao
interface DescriptionNoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(descriptionNote: DescriptionNote)

    @Query("SELECT * FROM DescriptionNote where id = :id")
    fun findDropBySameID(id: String): DescriptionNote

    @Query("SELECT * FROM DescriptionNote where id = :id")
    fun selectWithLiveData(id: String?): LiveData<DescriptionNote?>
}