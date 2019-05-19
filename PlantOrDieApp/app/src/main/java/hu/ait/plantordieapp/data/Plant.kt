package hu.ait.plantordieapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "plant")
data class Plant(
    @PrimaryKey(autoGenerate = true) var plantId : Long?,
    @ColumnInfo(name = "createDate") var createDate: String,
    @ColumnInfo(name = "done") var done: Boolean,
    @ColumnInfo(name = "plantText") var plantText: String
    ) : Serializable