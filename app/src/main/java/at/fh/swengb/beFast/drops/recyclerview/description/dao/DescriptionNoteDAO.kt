package at.fh.swengb.beFast.drops.recyclerview.description.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This interface is the Data Access Object of DescriptionNote and defines
 * read/write operations for database.
 *
 * It inserts (@Insert) descriptionNotes into the database and finds
 * corresponding decriptionNotes (@Query) by using their PrimaryKey id.
 *
 */


@Dao
interface DescriptionNoteDAO {

    /**
     * The onConflict setting OnConflictStrategy.REPLACE means that,
     * if we insert a descriptionNote with an id that already exists in the database the existing one will be replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(descriptionNote: DescriptionNote)

    @Query("SELECT * FROM DescriptionNote where id = :id")
    fun selectWithLiveData(id: String?): LiveData<DescriptionNote?>
}