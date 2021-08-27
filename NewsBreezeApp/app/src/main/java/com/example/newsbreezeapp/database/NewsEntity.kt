package  com.example.newsbreezeapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class NewsEntity(
    val title: String? = null,
    val author: String? = null,
    val name:String? =null,
    val publishedAt:String?=null,
    val urlToImage: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int=0
}