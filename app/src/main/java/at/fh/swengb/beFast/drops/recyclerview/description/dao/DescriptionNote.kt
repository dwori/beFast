package at.fh.swengb.beFast.drops.recyclerview.description.dao

import androidx.room.*

@Entity
class DescriptionNote (@PrimaryKey val id: String, val dropName: String, val text: String)