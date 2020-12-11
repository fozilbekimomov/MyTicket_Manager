package uz.fozilbekimomov.mystiker_manager.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataJ


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userdata: UserDataJ)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userdata: List<UserDataJ>)

    @Query("DELETE FROM user_data")
    fun deleteAll()

    @Query("SELECT * FROM user_data ORDER BY addedTime ASC")
    fun getAllData(): List<UserDataJ>

    @Query("SELECT * FROM user_data WHERE userName=:userName")
    fun getAllByName(userName: String): List<UserDataJ>

    @Query("SELECT COUNT(id) FROM user_data WHERE userName =:userName")
    fun getCount(userName: String): Int
}