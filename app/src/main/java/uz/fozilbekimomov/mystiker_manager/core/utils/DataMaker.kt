package uz.fozilbekimomov.mystiker_manager.core.utils

import uz.fozilbekimomov.mystiker_manager.core.models.UserDataCount
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataJ


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/11/20
 * @project MyStiker_Manager
 */


class DataMaker {
    private val data = ArrayList<UserDataCount>()

    fun add(userDataJ: UserDataJ, count: Int) {
        var isAdded = false
        if (data.size == 0) {
            data.add(UserDataCount(userDataJ.userName, count))
        } else if (data.size > 0) {
            for (u in data) {
                if (u.userName.equals(userDataJ.userName, true)) {
                    isAdded = true
                    return
                }
            }
            if (!isAdded) {
                data.add(UserDataCount(userDataJ.userName, count))
            }
        }

    }
    fun getData(): ArrayList<UserDataCount> = data
}