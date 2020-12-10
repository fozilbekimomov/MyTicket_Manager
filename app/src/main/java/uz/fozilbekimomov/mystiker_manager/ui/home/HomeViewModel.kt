package uz.fozilbekimomov.mystiker_manager.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.fozilbekimomov.mystiker_manager.core.db.UserDB
import uz.fozilbekimomov.mystiker_manager.core.db.UserDao
import uz.fozilbekimomov.mystiker_manager.core.models.LocationModelJ
import uz.fozilbekimomov.mystiker_manager.core.models.UserData
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataJ
import uz.fozilbekimomov.mystiker_manager.core.utils.DATA_KEY
import uz.fozilbekimomov.mystiker_manager.core.utils.TAG


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var db: FirebaseFirestore? = null

    private var userDao: UserDao? = null

    init {
        db = FirebaseFirestore.getInstance()
        userDao = UserDB.getDatabase()?.userDao()
    }

    private val _homeUserList = MutableLiveData<ArrayList<UserDataJ>>()
    val homeUserList: LiveData<ArrayList<UserDataJ>> get() = _homeUserList
    fun loadData() {
        _stateLoading.postValue(true)
        viewModelScope.launch {
            withContext(Dispatchers.Main) {

                db!!.collection(DATA_KEY)
                    .get().addOnCompleteListener { task ->

                        val data = ArrayList<UserDataJ>()
                        for (document in task.result) {
                            Log.d(TAG, document.id + " => " + document.data)

                            val userData = document.toObject(UserDataJ::class.java)
                            userData.id=document.id
                            data.add(
                                userData
                            )
                        }
                        loadSetData(data)
                        _homeUserList.postValue(data)

                        UserDB.databaseWriteExecutor.execute {
                            userDao?.insertAll(data)
                        }

                        _stateLoading.postValue(false)
                    }
            }
        }
    }

    private val _stateLoading = MutableLiveData<Boolean>()
    val stateLoading: LiveData<Boolean> get() = _stateLoading

    private val _usersName = MutableLiveData<HashSet<String>>()
    val usersName: LiveData<HashSet<String>> get() = _usersName
    fun loadSetData(data: ArrayList<UserDataJ>) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {

                val set = HashSet<String>()

                for (d in data) {
                    d.userName?.let { set.add(it) }
                }
                _usersName.postValue(set)

            }
        }
    }

}