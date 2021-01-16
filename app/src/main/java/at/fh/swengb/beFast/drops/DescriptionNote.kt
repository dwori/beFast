package at.fh.swengb.beFast.drops

import androidx.room.*

@Entity
class DescriptionNote (@PrimaryKey val id: String, val dropName: String, val text: String)