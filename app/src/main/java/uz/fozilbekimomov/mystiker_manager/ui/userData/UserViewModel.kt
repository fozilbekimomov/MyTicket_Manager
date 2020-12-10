package uz.fozilbekimomov.mystiker_manager.ui.userData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.fozilbekimomov.mystiker_manager.core.db.UserDB
import uz.fozilbekimomov.mystiker_manager.core.db.UserDao
import uz.fozilbekimomov.mystiker_manager.core.models.UserData
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataJ


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class UserViewModel(application: Application) : AndroidViewModel(application) {


    private var userDao: UserDao? = null

    init {
        userDao = UserDB.getDatabase()?.userDao()
    }

    private val _dataList = MutableLiveData<List<UserDataJ>>()
    val dataList: LiveData<List<UserDataJ>> get() = _dataList
    fun loadData(userName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {

                UserDB.databaseWriteExecutor.execute {

                    _dataList.postValue(userDao?.getAllByName(userName))

                }

            }
        }
    }


}