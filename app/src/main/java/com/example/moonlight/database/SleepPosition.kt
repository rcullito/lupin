package com.example.moonlight.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_position_table")
data class SleepPosition(

  @PrimaryKey(autoGenerate = true)
  var sleepPositionId: Long = 0L,

  @ColumnInfo(name = "sleep_position_timestamp")
  val sleepPositionTime: Long = System.currentTimeMillis(),

  @ColumnInfo(name = "pitch")
  var pitch: Float,

  @ColumnInfo(name = "roll")
  var roll: Float

)