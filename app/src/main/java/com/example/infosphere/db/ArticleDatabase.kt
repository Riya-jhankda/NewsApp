package com.example.infosphere.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.infosphere.models.Article

@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile                       //changes to the instance variable should be immediately visible to all threads
        private var instance: ArticleDatabase?= null
        private val LOCK = Any()        //This variable will be used for synchronization when accessing the instance variable.

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){  //This allows the companion object to be called like a
            instance ?: createDatabase(context).also{ instance = it}             //function using the syntax ArticleDatabase(context)
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}