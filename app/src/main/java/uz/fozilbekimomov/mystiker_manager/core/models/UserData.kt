package uz.fozilbekimomov.mystiker_manager.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import uz.fozilbekimomov.mystiker_manager.core.db.UserTypeConverter
import java.io.Serializable

/**
 * Created by [Fozilbek Imomov](mailto: fozilbekimomov@gmail.com)
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */
//@TypeConverters(UserTypeConverter::class)
//@Entity(tableName = "user_data")
class UserData : Serializable {
    var userName: String? = null
    var phoneNumber: String? = null
    var imageUrl: String? = null
    var addedTime: Long? = null
    var location: LocationModel? = null

    @PrimaryKey(autoGenerate = true)
    var userId: Long? = null

    constructor()
    constructor(
        userName: String?,
        phoneNumber: String?,
        imageUrl: String?,
        addedTime: Long?,
        location: LocationModel?
    ) {
        this.userName = userName
        this.phoneNumber = phoneNumber
        this.imageUrl = imageUrl
        this.addedTime = addedTime
        this.location = location
    }

    override fun toString(): String {
        return "UserDataJ{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", addedTime=" + addedTime +
                ", location=" + location +
                '}'
    }
}