package at.fh.swengb.beFast.drops.recyclerview.description.dao

import androidx.room.*

/**
 * The DescriptionNote class defines the schema of the database table.
 *
 * @Entity annotates the class to be used within a database
 * @property id is the id of the note and used as a primary key for referencing
 * @property dropName is the name of the drop
 * @property text contains the personal note one can save
 */

@Entity
class DescriptionNote (@PrimaryKey val id: String, val dropName: String, val text: String)