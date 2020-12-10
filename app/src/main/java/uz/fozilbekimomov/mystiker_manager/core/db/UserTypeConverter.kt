package uz.fozilbekimomov.mystiker_manager.core.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.fozilbekimomov.mystiker_manager.core.models.LocationModel
import uz.fozilbekimomov.mystiker_manager.core.models.LocationModelJ
import uz.fozilbekimomov.mystiker_manager.core.models.UserData
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataJ
import java.lang.reflect.Type


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class UserTypeConverter {
       @TypeConverter
       fun stringToMeasurements(json: String):LocationModelJ{
           val gson = Gson()
           val type: Type = object : TypeToken<LocationModelJ>() {}.type
           return gson.fromJson<LocationModelJ>(json, type)
       }

       @TypeConverter
       fun measurementsToString(list: LocationModelJ): String {
           val gson = Gson()
           val type: Type = object : TypeToken<LocationModelJ>() {}.type
           return gson.toJson(list, type)
       }
       @TypeConverter
       fun stringToData(json: String):List<UserDataJ> {
           val gson = Gson()
           val type: Type = object : TypeToken<List<UserDataJ>>() {}.type
           return gson.fromJson(json, type)
       }

       @TypeConverter
       fun measurementsToString(list: List<UserDataJ>): String {
           val gson = Gson()
           val type: Type = object : TypeToken<List<UserDataJ>>() {}.type
           return gson.toJson(list, type)
       }

}