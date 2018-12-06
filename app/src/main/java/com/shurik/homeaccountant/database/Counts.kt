package com.shurik.homeaccountant.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity (tableName = "counts")
class Counts (@PrimaryKey (autoGenerate = true) var id : Int?,
              @ColumnInfo (name = "lightNow") var lightNow : Int,
              @ColumnInfo (name = "lightWas") var lightWas : Int,
              @ColumnInfo (name = "lightDiff") var lightDiff : Int,
              @ColumnInfo (name = "lightPrice") var lightPrice : Double,
              @ColumnInfo (name = "waterNow") var waterNow : Int,
              @ColumnInfo (name = "waterWas") var waterWas : Int,
              @ColumnInfo (name = "waterDiff") var waterDiff : Int,
              @ColumnInfo (name = "waterPrice") var waterPrice : Double,
              @ColumnInfo (name = "gasNow") var gasNow : Int,
              @ColumnInfo (name = "gasWas") var gasWas : Int,
              @ColumnInfo (name = "gasDiff") var gasDiff : Int,
              @ColumnInfo (name = "gasPrice") var gasPrice : Double,
              @ColumnInfo (name = "aptPrice") var aptPrice : Double,
              @ColumnInfo (name = "total") var total : Double,
              @ColumnInfo (name = "date") var date : String)

{
    constructor():this (null,0,0,0,0.0,0,
            0,0,0.0,0,0,0,0.0,0.0,0.0,"")
}