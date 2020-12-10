package uz.fozilbekimomov.mystiker_manager.core.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import uz.fozilbekimomov.mystiker_manager.core.db.UserTypeConverter;

/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/11/20
 * @project MyStiker_Manager
 */


@TypeConverters(UserTypeConverter.class)
@Entity(tableName = "user_data")
public class UserDataJ {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    public String id="";
    public String userName;
    public String phoneNumber;
    public String imageUrl;
    public long addedTime;
    @TypeConverters(UserTypeConverter.class)
    public LocationModelJ location;

    @Ignore
    public UserDataJ() {
    }

    public UserDataJ(String userName, String phoneNumber, String imageUrl, long addedTime, LocationModelJ location) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.addedTime = addedTime;
        this.location = location;
    }

    @Override
    public String toString() {
        return "UserDataJ{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", addedTime=" + addedTime +
                ", location=" + location +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(long addedTime) {
        this.addedTime = addedTime;
    }

    public LocationModelJ getLocation() {
        return location;
    }

    public void setLocation(LocationModelJ location) {
        this.location = location;
    }
}
