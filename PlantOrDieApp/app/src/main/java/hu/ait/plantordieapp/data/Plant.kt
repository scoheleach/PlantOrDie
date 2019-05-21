package hu.ait.plantordieapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.FileDescriptor
import java.io.Serializable

@Entity(tableName = "plant")
data class Plant(
    @PrimaryKey(autoGenerate = true) var plantId : Long?,
    @ColumnInfo(name = "plantNickname") var nickname: String,
    @ColumnInfo(name = "plant") var plant: String,
    @ColumnInfo(name = "plantText") var description: String,
    //@ColumnInfo(name = "daysOwned") var daysOwned: String,
    @ColumnInfo(name = "wateredToday") var wateredToday: Boolean
    ) : Serializable