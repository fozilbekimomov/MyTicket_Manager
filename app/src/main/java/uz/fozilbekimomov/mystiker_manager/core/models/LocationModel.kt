package uz.fozilbekimomov.mystiker_manager.core.models

import androidx.room.TypeConverters
import uz.fozilbekimomov.mystiker_manager.core.db.UserTypeConverter

/**
 * Created by [Fozilbek Imomov](mailto: fozilbekimomov@gmail.com)
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class LocationModel {
    var longitude = 0.0
    var latitude = 0.0

    constructor() {}
    constructor(longitude: Double, latitude: Double) {
        this.longitude = longitude
        this.latitude = latitude
    }

    override fun toString(): String {
        return "LocationModelJ{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}'
    }
}