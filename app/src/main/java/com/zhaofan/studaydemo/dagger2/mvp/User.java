package com.zhaofan.studaydemo.dagger2.mvp;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.ParameterizedType;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/8
 * description:
 */
public class User implements Parcelable {
    public String username;
    public String password;
    public int id;


    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeInt(id);
    }

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
        id = in.readInt();
    }
}
