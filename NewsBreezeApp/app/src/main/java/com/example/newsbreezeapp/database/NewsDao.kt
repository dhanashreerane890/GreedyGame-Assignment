package  com.example.newsbreezeapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun  addNewsDetails(newsEntity: NewsEntity)

    @Query("SELECT * FROM details order by id DESC")
    fun getAllNewsDetails(): LiveData<List<NewsEntity>>

    @Delete
    fun deleteNewsDetails(newsEntity: NewsEntity)
}