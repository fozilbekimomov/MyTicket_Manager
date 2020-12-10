package uz.fozilbekimomov.mystiker_manager.core.app

import android.app.Application
import androidx.multidex.MultiDex
import uz.fozilbekimomov.mystiker_manager.core.db.UserDB


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        UserDB.init(this)
    }
}