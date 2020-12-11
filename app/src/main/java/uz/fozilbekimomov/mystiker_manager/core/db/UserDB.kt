package uz.fozilbekimomov.mystiker_manager.core.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.fozilbekimomov.mystiker_manager.core.models.UserData
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataJ
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */
@TypeConverters(UserTypeConverter::class)
@Database(entities = [UserDataJ::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {


        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)


        @Volatile
        private var INSTANCE: UserDB? = null
        fun init(application: Application) {
            if (INSTANCE == null) {
                synchronized(UserDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.inMemoryDatabaseBuilder(
                           application,
                            UserDB::class.java
                        ).allowMainThreadQueries().build()
                    }
                }
            }
        }

        fun getDatabase():UserDB?{
            return INSTANCE
        }

    }

}