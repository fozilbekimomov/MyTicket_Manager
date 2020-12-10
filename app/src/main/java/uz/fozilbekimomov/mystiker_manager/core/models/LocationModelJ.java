package uz.fozilbekimomov.mystiker_manager.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

import java.io.Serializable;


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/11/20
 * @project MyStiker_Manager
 */

public class LocationModelJ implements Serializable {
    public double longitude;
    public double latitude;

    @Ignore
    public LocationModelJ() {
    }

    public LocationModelJ(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "LocationModelJ{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
