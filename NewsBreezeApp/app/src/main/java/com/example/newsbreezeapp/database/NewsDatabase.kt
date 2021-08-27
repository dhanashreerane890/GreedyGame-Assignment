package  com.example.newsbreezeapp


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [NewsEntity::class],version = 2,exportSchema = false)
abstract class NewsDatabase():RoomDatabase() {
    abstract fun getNewsDao():NewsDao
    companion object{
        private var INSTANCE:NewsDatabase?=null
        fun getDatabase(context: Context):NewsDatabase{
            if(INSTANCE==null){
                val builder: Builder<NewsDatabase> = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "details_database"
                )
                builder.fallbackToDestructiveMigration()
                return builder.build()
                return INSTANCE!!
            }else{
                return INSTANCE!!
            }
        }
    }
}